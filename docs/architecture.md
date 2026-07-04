# CloudMall 架构设计文档

> 版本：v1.0 | 日期：2026-07-04

---

## 一、架构总览：模块化单体

### 1.1 架构图（文字描述）

```
                          ┌─────────────────────────────┐
                          │         Nginx (反向代理)       │
                          │  静态资源 / API 路由 / HTTPS    │
                          └──────────┬──────────────────┘
                                     │
                    ┌────────────────┴────────────────┐
                    │                                 │
          ┌─────────┴──────────┐        ┌────────────┴───────────┐
          │   cloudmall-ui      │        │   cloudmall-boot        │
          │   (Vue 3 SPA)       │        │   (Spring Boot 3)       │
          │   端口: 5173(dev)    │        │   端口: 8080              │
          │   静态文件部署到      │        │                          │
          │   Nginx 目录        │        │   ┌──────────────────┐  │
          └────────────────────┘        │   │  cloudmall-api    │  │
                                        │   │  (Controller 层)   │  │
                                        │   ├──────────────────┤  │
                                        │   │  应用服务层         │  │
                                        │   ├──────────────────┤  │
                                        │   │  领域层            │  │
                                        │   │  (13 个领域模块)    │  │
                                        │   ├──────────────────┤  │
                                        │   │  基础设施层         │  │
                                        │   └──────────────────┘  │
                                        └──────────┬───────────────┘
                                                   │
                    ┌────────────────┬─────────────┼─────────────┬────────────────┐
                    │                │             │             │                │
              ┌─────┴─────┐  ┌──────┴──────┐ ┌───┴──────┐ ┌───┴──────┐  ┌──────┴──────┐
              │   MySQL    │  │    Redis    │ │ RabbitMQ │ │ 文件存储  │  │   External  │
              │   (3306)   │  │   (6379)    │ │ (5672)   │ │ (本地/OSS)│  │  支付网关等  │
              └───────────┘  └─────────────┘ └──────────┘ └──────────┘  └─────────────┘
```

### 1.2 为什么选模块化单体

| 维度 | 模块化单体 | 微服务 | 选择理由 |
|------|-----------|--------|----------|
| 开发效率 | 高 | 低（前期） | 小团队快速迭代 |
| 部署复杂度 | 低（一个 JAR） | 高（多服务协调） | 简化运维 |
| 调试难度 | 低 | 高（分布式链路） | 快速定位问题 |
| 事务管理 | 简单（本地事务） | 复杂（分布式事务） | 数据一致性保障 |
| 代码复用 | 直接引用 | 需额外处理 | 减少重复 |
| 未来演进 | — | — | 模块边界清晰，拆分成本低 |

### 1.3 拆分微服务的信号

当以下条件满足 **任一** 时，该模块可拆分为独立微服务：

1. 该模块需要**独立的发布节奏**（与其他模块变更频率差异大）
2. 该模块需要**独立的扩缩容策略**（如商品浏览与订单处理的 QPS 差异大）
3. 该模块需要**独立的技术栈**（如推荐服务用 Python）
4. 团队规模扩大到**多个小团队并行开发**
5. 该模块的**数据库已成为瓶颈**，需要独立数据库实例

---

## 二、后端分层架构

### 2.1 运行时分层

```
┌──────────────────────────────────────────────────────────┐
│  API 层 (cloudmall-api)                                   │
│  ├── Controller (接收 HTTP 请求，参数校验，调用应用服务)     │
│  ├── DTO / VO (请求/响应对象)                               │
│  └── 统一异常处理 (@RestControllerAdvice)                   │
├──────────────────────────────────────────────────────────┤
│  应用服务层 (cloudmall-domain-xxx/application)             │
│  ├── ApplicationService (用例编排、事务边界)               │
│  ├── Command / Query (CQRS 对象)                          │
│  └── Assembler (DTO ↔ 领域对象转换)                       │
├──────────────────────────────────────────────────────────┤
│  领域层 (cloudmall-domain-xxx/model + service)            │
│  ├── Aggregate / Entity / ValueObject (领域模型)          │
│  ├── DomainService (核心业务规则)                          │
│  ├── Repository Interface (仓储接口)                      │
│  └── DomainEvent (领域事件)                               │
├──────────────────────────────────────────────────────────┤
│  基础设施层 (cloudmall-infrastructure + xxx/infrastructure)│
│  ├── RepositoryImpl (MyBatis Plus 实现)                   │
│  ├── Mapper (数据库映射)                                   │
│  ├── Security (Spring Security + JWT)                    │
│  ├── Cache (Redis 缓存实现)                               │
│  ├── MQ (RabbitMQ 消息发送/消费)                          │
│  └── EventPublisher (领域事件发布实现)                     │
└──────────────────────────────────────────────────────────┘
```

### 2.2 依赖方向（由外向内依赖）

```
API → Application → Domain ← Infrastructure
                         ↑
                    Common (公共模块，所有层可用)
```

**关键规则：Domain 层不依赖任何框架和基础设施，只依赖 Common 和 Java 标准库。**

### 2.3 模块间应用服务调用模式

```
// 模块 A 的应用服务调用模块 B 的应用服务
// 通过依赖注入 B 的 ApplicationService 接口

@Service
public class OrderApplicationService {
    // 注入其他模块的应用服务（而非直接调 Repository）
    private final UserApplicationService userAppService;     // 用户模块
    private final ProductApplicationService productAppService; // 商品模块
    private final InventoryApplicationService inventoryAppService; // 库存模块
    private final MarketingApplicationService marketingAppService; // 营销模块
}
```

---

## 三、前端分层架构

### 3.1 分层结构

```
┌─────────────────────────────────────────────┐
│  Pages (页面层)                               │
│  路由对应的页面组件，组合各业务组件              │
├─────────────────────────────────────────────┤
│  Components (组件层)                          │
│  可复用的 UI 组件 (商品卡片、购物车项、订单项等)  │
├─────────────────────────────────────────────┤
│  Composables (逻辑层)                         │
│  可复用的组合式函数 (useAuth, useCart, useOrder)│
├─────────────────────────────────────────────┤
│  Stores (状态管理层 — Pinia)                   │
│  全局状态：用户信息、购物车、认证 Token          │
├─────────────────────────────────────────────┤
│  API Layer (请求层)                           │
│  封装 Axios 请求、统一错误处理、Token 注入       │
├─────────────────────────────────────────────┤
│  Utils / Types / Constants (基础层)           │
│  工具函数、TypeScript 类型定义、常量             │
└─────────────────────────────────────────────┘
```

### 3.2 前端路由规划

```
/                           → 首页（商品搜索、推荐）
/login                       → 登录页
/register                    → 注册页
/products/:categoryId        → 商品列表
/products/detail/:id         → 商品详情
/cart                        → 购物车
/order/confirm               → 订单确认页（需登录）
/order/list                  → 订单列表（需登录）
/order/detail/:id            → 订单详情（需登录）
/user/center                 → 个人中心（需登录）
/user/address                → 收货地址管理（需登录）
/after-sale/list             → 售后列表

/admin/*                     → 后台管理（需管理员权限）
/admin/products              → 商品管理
/admin/orders                → 订单管理
/admin/users                 → 用户管理
/admin/marketing             → 营销管理
/admin/system                → 系统管理
```

---

## 四、数据库设计原则

### 4.1 总原则

| 原则 | 说明 |
|------|------|
| **每个领域模块独立 Schema 前缀** | 当前共用一个数据库，通过表名区分（不强制分库） |
| **一律使用 InnoDB** | 支持事务、行级锁、外键 |
| **主键使用自增 BIGINT** | 高性能、有序插入 |
| **必须有审计字段** | `created_at`, `updated_at`, `created_by`, `updated_by` |
| **必须使用 Flyway** | 所有 DDL 通过 Flyway 脚本管理，禁止手动修改 |
| **禁止物理外键** | 不使用数据库外键约束，在应用层维护引用完整性 |
| **逻辑删除** | 使用 `deleted` 字段（0=未删除, 1=已删除），不物理删除 |
| **软删除 + 唯一索引** | 唯一索引包含 `deleted` 字段 |

### 4.2 索引设计

| 规则 | 说明 |
|------|------|
| 每个查询必须有索引覆盖 | 不允许全表扫描 |
| 联合索引遵循最左前缀 | 高区分度字段在前 |
| 唯一索引含 deleted | `UNIQUE KEY uk_email_deleted (email, deleted)` |
| 定期分析慢查询 | 每个迭代结束后 review |

### 4.3 分表预留

当前阶段不进行分库分表。但：

- 订单表按 `order_no` 查询为主，建立哈希索引
- 预留分表键（如 `user_id`），未来按用户 ID 分表
- 日志类表（审计日志）预留按月分区

---

## 五、缓存设计原则

### 5.1 缓存层次

```
┌──────────────────────────────────────┐
│  浏览器缓存 (HTTP Cache-Control)       │  ← 静态资源、商品图片
├──────────────────────────────────────┤
│  Nginx 缓存 (proxy_cache)             │  ← 热点商品 GET 接口
├──────────────────────────────────────┤
│  Redis 缓存                           │  ← 核心业务缓存
│  ├── 数据缓存 (商品、分类、用户信息)      │
│  ├── 会话缓存 (JWT Token 黑名单)        │
│  ├── 分布式锁 (库存扣减)                │
│  └── 计数器 (库存预警、限流)            │
├──────────────────────────────────────┤
│  本地缓存 (Caffeine — 后期引入)         │  ← 极热点数据（配置、字典）│
└──────────────────────────────────────┘
```

### 5.2 缓存策略

| 数据类型 | 策略 | TTL | 更新方式 |
|----------|------|-----|----------|
| 商品基本信息 | Cache-Aside | 30 分钟 | 更新时删除缓存 |
| 商品分类树 | Cache-Aside | 1 小时 | 变更时删除缓存 |
| SKU 库存（读多） | Cache-Aside | 10 秒 | 扣减后删除缓存 |
| SKU 库存（写） | 不缓存 | — | 直接查 DB（库存需实时准确） |
| 用户信息 | Cache-Aside | 30 分钟 | 更新时删除 |
| JWT 黑名单 | 写入型 | Token 过期时间 | 登出时加入黑名单 |
| 验证码 | 写入型 | 5 分钟 | 发送时写入 |
| 优惠券库存 | 不缓存 | — | 直接查 DB（精确计数） |

### 5.3 缓存穿透/击穿/雪崩防护

| 问题 | 方案 |
|------|------|
| 缓存穿透（查不存在的数据） | 布隆过滤器 + 空值缓存（TTL 1 分钟） |
| 缓存击穿（热点 Key 过期） | 互斥锁（Redisson 分布式锁）+ 逻辑过期 |
| 缓存雪崩（大面积过期） | TTL 加随机值（±20%）、多级缓存 |

---

## 六、消息队列设计原则

### 6.1 使用场景

| 场景 | 交换机类型 | 说明 |
|------|-----------|------|
| 订单超时取消 | Delayed Exchange | 下单后 30 分钟延迟消息 |
| 领域事件广播 | Fanout Exchange | 一个事件多个消费者 |
| 异步通知 | Direct Exchange | 短信/邮件/站内信 |
| 异步审计 | Direct Exchange | 操作日志异步写入 |
| 库存同步 | Topic Exchange | 库存变更通知 |

### 6.2 消息可靠性

| 机制 | 说明 |
|------|------|
| 生产者确认 (Publisher Confirm) | 消息到达 Broker 才认为发送成功 |
| 消费者确认 (Manual ACK) | 处理成功才 ACK，失败重试 |
| 幂等消费 | 通过消息 ID / 业务 ID 去重 |
| 死信队列 (DLQ) | 重试 N 次后进入死信队列，人工处理 |
| 消息持久化 | 队列和消息都持久化 |

### 6.3 Exchange / Queue 命名规范

```
交换机：ex.{domain}.{event}
队列：  q.{domain}.{consumer}.{event}

示例：
ex.order.created          → 订单创建 Fanout 交换机
q.order.notification.created → 通知服务的订单创建队列
q.inventory.order.deduct  → 库存扣减队列
```

---

## 七、关键业务一致性方案

### 7.1 库存扣减一致性（不超卖）

```
方案：乐观锁 + 数据库行锁

UPDATE inventory
SET available_stock = available_stock - #{quantity},
    locked_stock = locked_stock + #{quantity},
    version = version + 1
WHERE sku_id = #{skuId}
  AND available_stock >= #{quantity}  -- 库存充足
  AND version = #{version}             -- 乐观锁

影响行数 = 0 → 库存不足或冲突 → 重试或失败

防死锁：按 SKU ID 升序排序后再扣减
```

### 7.2 订单支付一致性

```
方案：回调 + 主动查询 + 幂等

1. 用户发起支付 → 创建支付单 (status=PENDING)
2. 支付平台异步回调 → /api/v1/payment/callback
   ├── 验签（防止伪造回调）
   ├── 幂等校验（通过 paymentNo 去重）
   ├── 更新支付单 status=PAID
   └── 发布 PaymentSucceededEvent
3. 定时任务主动查询 → 每 1 分钟查询待支付订单
   ├── 调用支付平台查询接口
   ├── 已支付 → 同回调处理
   └── 超过 30 分钟 → 标记支付超时
4. 订单状态更新幂等 → 只有 PENDING_PAY 的订单才能变为 PAID
```

### 7.3 订单超时取消一致性

```
方案：RabbitMQ 延迟消息 + 幂等状态机

下单成功 → 发送延迟消息 (delay=30min) → 指定队列

30分钟后消费消息：
1. 查询订单当前状态
2. 如果 status == PENDING_PAY → 取消订单
   ├── 更新订单 status=CANCELLED
   ├── 释放库存（Inventory Context）
   ├── 恢复优惠券（Marketing Context）
   └── 如果已支付 → 发起退款
3. 如果 status != PENDING_PAY → 已处理，直接 ACK（幂等）
```

### 7.4 退款库存恢复一致性

```
方案：事件驱动 + 补偿

退款成功 → 发布 RefundProcessedEvent
  → Inventory Context 消费：
    ├── 增加 available_stock
    ├── 记录 StockChangeLog（类型=退款恢复）
    └── 失败 → 重试 → 最终进入死信队列 → 人工介入
```

### 7.5 分布式事务约束

**当前阶段（模块化单体）：使用 Spring 本地事务**，所有模块在同一个数据源下，事务天然 ACID。

**未来微服务拆分后**：核心链路（库存+订单）使用 TCC 或 Saga 模式，非核心链路（通知+审计）使用最终一致性 + 本地消息表。

---

## 八、安全架构

### 8.1 认证流程

```
用户登录 → POST /api/v1/auth/login
  ├── 验证用户名密码 (BCrypt)
  ├── 生成 AccessToken (JWT, 有效期 2h)
  ├── 生成 RefreshToken (JWT, 有效期 7d)
  └── 返回 Token Pair

后续请求 → Authorization: Bearer <AccessToken>
  ├── JWT Filter 解析 Token
  ├── 校验签名、有效期
  ├── 查询黑名单（Redis，登出/封禁的 Token）
  └── 设置 SecurityContext

AccessToken 过期 → POST /api/v1/auth/refresh
  └── 使用 RefreshToken 换取新 AccessToken（RefreshToken Rotation）
```

### 8.2 权限模型

```
RBAC 模型：用户 → 角色 → 权限

权限粒度：
  API 级别 → @PreAuthorize("hasAuthority('product:create')")
  数据级别 → 查询时注入用户过滤条件（后期）

权限编码规范：
  {资源}:{操作}
  示例：
    product:create, product:update, product:delete, product:view
    order:create, order:cancel, order:view
    user:view, user:block
    system:config
```

### 8.3 安全防线层次

```
第一层：Nginx（限流、IP 黑名单、HTTPS）
第二层：Spring Security（认证、授权）
第三层：参数校验（Bean Validation）
第四层：业务校验（领域层业务规则）
第五层：数据库约束（唯一索引防重复）
第六层：审计日志（事后追溯）
```

---

## 九、部署架构

### 9.1 开发环境

```
┌─────────────────────────────────────────────┐
│  Docker Compose                              │
│  ┌─────────┐ ┌───────┐ ┌──────────┐        │
│  │  MySQL  │ │ Redis │ │ RabbitMQ │        │
│  │  :3306  │ │ :6379 │ │ :5672    │        │
│  └─────────┘ └───────┘ └──────────┘        │
│                                              │
│  cloudmall-boot (localhost:8080)             │
│  cloudmall-ui   (localhost:5173)             │
└─────────────────────────────────────────────┘
```

### 9.2 生产环境（未来）

```
┌──────────────────────────────────────────────┐
│  Nginx (HTTPS, 反向代理, 静态资源)              │
├──────────────────────────────────────────────┤
│  cloudmall-boot × 2 (负载均衡, 不同端口)        │
├──────────────────────────────────────────────┤
│  MySQL 主从 / Redis Cluster / RabbitMQ Cluster │
└──────────────────────────────────────────────┘
```

---

## 十、技术决策记录 (ADR)

### ADR-001：模块化单体而非微服务
- **决策**：采用模块化单体架构
- **原因**：早期团队规模小、业务模型尚未稳定、微服务运维成本高
- **代价**：需要严格的模块边界约束（ArchUnit 强制），避免模块耦合
- **推导条件**：当单模块 QPS > 2000 或团队 > 10 人时重新评估

### ADR-002：MyBatis Plus 而非 JPA
- **决策**：ORM 使用 MyBatis Plus
- **原因**：复杂查询灵活性、团队经验、国内生态
- **代价**：需要手写部分 SQL

### ADR-003：RabbitMQ 而非 Kafka
- **决策**：消息队列使用 RabbitMQ
- **原因**：当前场景以业务事件驱动和延迟消息为主，非高吞吐日志场景
- **代价**：吞吐量低于 Kafka，如果未来需要日志采集需引入 Kafka

### ADR-004：逻辑删除而非物理删除
- **决策**：所有业务数据使用逻辑删除
- **原因**：数据可恢复、审计追溯、避免级联删除问题
- **代价**：唯一索引需包含 deleted 字段

### ADR-005：禁止物理外键
- **决策**：不使用数据库外键约束
- **原因**：便于分库分表迁移、减少锁竞争、提高写入性能
- **代价**：应用层需要自己保证引用完整性

---

*本文档为架构设计基线，与 CLAUDE.md 和 domain-model.md 配套使用。*
