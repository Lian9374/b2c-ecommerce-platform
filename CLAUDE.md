# CLAUDE.md — B2C 电商平台开发规范

## 一、项目概述

### 1.1 项目名称
**CloudMall** — 企业级 B2C 电商平台

### 1.2 项目目标
从零构建一个生产级 B2C 电商平台，支持：
- 买家浏览商品、加入购物车、下单、支付、查看物流、申请售后
- 卖家管理商品、库存、价格、营销活动、订单履约
- 运营人员管理用户、权限、审计日志、报表
- 系统支持高并发、高可用、可扩展

### 1.3 非功能性目标
- 核心接口响应时间 < 200ms (P99)
- 支持水平扩展
- 数据强一致性场景使用数据库锁，高并发场景使用缓存与最终一致性
- 关键业务零数据丢失

---

## 二、技术栈

### 2.1 后端
| 类别 | 技术 | 版本 |
|------|------|------|
| 语言 | Java | 17 |
| 框架 | Spring Boot | 3.3.x |
| 构建工具 | Maven | 3.9+ |
| ORM | MyBatis Plus | 3.5.x |
| 数据库 | MySQL | 8.0+ |
| 缓存 | Redis | 7.x |
| 消息队列 | RabbitMQ | 3.13.x |
| 数据库迁移 | Flyway | 10.x |
| 安全框架 | Spring Security + JWT | 6.x / 0.12.x |
| API 文档 | SpringDoc OpenAPI | 2.6.x |
| 参数校验 | Jakarta Validation | 3.x |
| 测试 | JUnit 5 + Testcontainers + ArchUnit | 最新稳定版 |
| 工具 | Lombok, MapStruct, Hutool | 最新稳定版 |

### 2.2 前端
| 类别 | 技术 | 版本 |
|------|------|------|
| 框架 | Vue | 3.4+ |
| 构建工具 | Vite | 5.x |
| 语言 | TypeScript | 5.x |
| UI 组件库 | Element Plus | 2.x |
| 状态管理 | Pinia | 2.x |
| 路由 | Vue Router | 4.x |
| HTTP 客户端 | Axios | 1.x |

### 2.3 DevOps
| 类别 | 技术 |
|------|------|
| 容器化 | Docker + Docker Compose |
| 反向代理 | Nginx |
| CI/CD | GitHub Actions |
| 监控 | Spring Boot Actuator + Prometheus（后期） |

---

## 三、目录结构规范

### 3.1 Maven 多模块总览

```
b2c-ecommerce-platform/
├── pom.xml                          # 父 POM，依赖管理
├── CLAUDE.md                        # 本文件
├── README.md
├── docker/                          # Docker 相关
│   ├── docker-compose.yml           # 本地开发环境
│   ├── docker-compose-prod.yml      # 生产环境
│   ├── nginx/
│   │   └── nginx.conf
│   ├── mysql/
│   │   └── init.sql                 # 初始化数据库
│   └── rabbitmq/
├── sql/                             # 数据库迁移脚本（Flyway 会读 classpath，此为备份）
│   └── V1__init_schema.sql
│
├── cloudmall-common/                # 公共模块
│   ├── pom.xml
│   └── src/main/java/com/cloudmall/common/
│       ├── base/                    # 基础类（BaseEntity, BaseVO 等）
│       ├── constant/                # 常量
│       ├── enums/                   # 枚举
│       ├── exception/               # 统一异常
│       ├── response/                # 统一返回结构
│       ├── util/                    # 工具类
│       └── config/                  # 公共配置
│
├── cloudmall-domain/                # 领域模型聚合（DDD 核心）
│   └── pom.xml                      # 仅包含子模块声明
│
├── cloudmall-domain/cloudmall-domain-user/     # 用户领域
├── cloudmall-domain/cloudmall-domain-product/  # 商品领域
├── cloudmall-domain/cloudmall-domain-inventory/# 库存领域
├── cloudmall-domain/cloudmall-domain-cart/     # 购物车领域
├── cloudmall-domain/cloudmall-domain-price/    # 价格领域
├── cloudmall-domain/cloudmall-domain-marketing/# 营销领域
├── cloudmall-domain/cloudmall-domain-order/    # 订单领域
├── cloudmall-domain/cloudmall-domain-payment/  # 支付领域
├── cloudmall-domain/cloudmall-domain-fulfillment/ # 履约领域
├── cloudmall-domain/cloudmall-domain-aftersale/# 售后领域
├── cloudmall-domain/cloudmall-domain-audit/    # 审计领域
├── cloudmall-domain/cloudmall-domain-notification/ # 通知领域
├── cloudmall-domain/cloudmall-domain-report/   # 报表领域
│
├── cloudmall-infrastructure/        # 基础设施层
│   ├── pom.xml
│   └── src/main/java/com/cloudmall/infra/
│       ├── security/                # Spring Security 配置
│       ├── cache/                   # Redis 配置
│       ├── mq/                      # RabbitMQ 配置
│       ├── persistence/            # MyBatis Plus 配置
│       └── web/                     # Web 配置（CORS, 拦截器等）
│
├── cloudmall-api/                   # API 层（Controller 聚合）
│   ├── pom.xml
│   └── src/main/java/com/cloudmall/api/
│       ├── controller/              # 按领域分包
│       │   ├── user/
│       │   ├── product/
│       │   ├── order/
│       │   └── ...
│       └── dto/                     # 数据传输对象
│
├── cloudmall-boot/                  # 启动模块
│   ├── pom.xml
│   └── src/main/java/com/cloudmall/
│       └── CloudMallApplication.java
│       └── src/main/resources/
│           ├── application.yml
│           ├── application-dev.yml
│           ├── application-prod.yml
│           └── db/migration/        # Flyway 迁移脚本
│
└── cloudmall-ui/                    # 前端项目
    ├── package.json
    ├── vite.config.ts
    ├── tsconfig.json
    └── src/
        ├── api/                     # API 请求层
        ├── assets/                  # 静态资源
        ├── components/              # 通用组件
        ├── composables/             # 组合式函数
        ├── layouts/                 # 布局组件
        ├── pages/                   # 页面（按模块分包）
        ├── router/                  # 路由配置
        ├── stores/                  # Pinia 状态管理
        ├── types/                   # TypeScript 类型定义
        └── utils/                   # 工具函数
```

### 3.2 单个领域模块内部结构

```
cloudmall-domain-user/
├── pom.xml
└── src/
    ├── main/java/com/cloudmall/domain/user/
    │   ├── model/                   # 领域模型
    │   │   ├── entity/              # 实体（聚合根、实体、值对象）
    │   │   │   ├── User.java        # 聚合根
    │   │   │   ├── UserAddress.java # 实体
    │   │   │   └── UserStatus.java  # 值对象（枚举）
    │   │   ├── aggregate/           # 聚合（可选，复杂时使用）
    │   │   └── vo/                  # 值对象
    │   ├── repository/              # 仓储接口（DDD 风格）
    │   │   └── UserRepository.java
    │   ├── service/                 # 领域服务
    │   │   └── UserDomainService.java
    │   ├── event/                   # 领域事件
    │   │   └── UserRegisteredEvent.java
    │   └── exception/               # 领域异常
    │       └── UserNotFoundException.java
    │
    ├── main/java/com/cloudmall/domain/user/application/  # 应用服务
    │   ├── UserApplicationService.java
    │   ├── command/                 # 命令对象
    │   │   └── RegisterUserCommand.java
    │   └── query/                   # 查询对象
    │       └── UserQuery.java
    │
    ├── main/java/com/cloudmall/domain/user/infrastructure/ # 基础设施实现
    │   ├── persistence/             # 仓储实现
    │   │   ├── UserRepositoryImpl.java
    │   │   ├── mapper/              # MyBatis Mapper
    │   │   │   └── UserMapper.java
    │   │   └── converter/           # PO ↔ Domain 转换
    │   │       └── UserConverter.java
    │   └── messaging/               # 消息发送实现
    │       └── UserEventPublisher.java
    │
    └── src/main/resources/
        └── mapper/                  # MyBatis XML（如需要）
            └── UserMapper.xml
```

---

## 四、DDD 分层规范

### 4.1 分层架构

```
┌─────────────────────────────────────────┐
│           API 层 (cloudmall-api)         │  Controller, DTO, 参数校验
├─────────────────────────────────────────┤
│         应用服务层 (application)          │  用例编排, 事务管理, 权限检查
├─────────────────────────────────────────┤
│         领域层 (model/service)           │  核心业务逻辑, 领域事件
├─────────────────────────────────────────┤
│         基础设施层 (infrastructure)       │  仓储实现, 消息, 缓存, 安全
└─────────────────────────────────────────┘
```

### 4.2 层级职责

#### API 层
- **只做**：接收 HTTP 请求、参数校验、调用应用服务、返回统一响应
- **不做**：业务逻辑、数据库操作、领域逻辑
- **代码示例**：
  ```java
  @RestController
  @RequestMapping("/api/v1/users")
  @Tag(name = "用户管理")
  public class UserController {
      private final UserApplicationService userAppService;

      @PostMapping("/register")
      @Operation(summary = "用户注册")
      public Result<UserVO> register(@Valid @RequestBody RegisterUserCommand command) {
          return Result.success(userAppService.register(command));
      }
  }
  ```

#### 应用服务层
- **只做**：用例编排、事务边界控制、调用领域服务/仓储、发送领域事件
- **不做**：核心业务规则（应下沉到领域层）
- **代码示例**：
  ```java
  @Service
  @Transactional
  public class UserApplicationService {
      private final UserRepository userRepository;
      private final UserDomainService userDomainService;
      private final ApplicationEventPublisher eventPublisher;

      public UserVO register(RegisterUserCommand command) {
          // 1. 调用领域服务执行业务规则
          User user = userDomainService.register(command);
          // 2. 持久化
          userRepository.save(user);
          // 3. 发布领域事件
          eventPublisher.publishEvent(new UserRegisteredEvent(user));
          // 4. 返回
          return UserAssembler.toVO(user);
      }
  }
  ```

#### 领域层
- **只做**：核心业务规则、不变性约束、领域计算
- **不做**：数据库操作、HTTP 调用、缓存操作
- **代码示例**：
  ```java
  public class UserDomainService {
      public User register(RegisterUserCommand command) {
          // 业务规则：检查邮箱唯一性
          // 业务规则：密码加密策略
          // 业务规则：生成用户编号
          Password password = Password.of(command.getRawPassword()); // 值对象
          Email email = Email.of(command.getEmail());                // 值对象
          return User.register(email, password, command.getNickname());
      }
  }
  ```

#### 基础设施层
- **只做**：技术实现（数据库、缓存、消息、外部 API）
- **不做**：业务判断、业务规则

### 4.3 依赖规则（ArchUnit 强制）
1. API 层 → 应用服务层 + 公共模块
2. 应用服务层 → 领域层 + 公共模块
3. 领域层 → 公共模块（领域层不依赖任何外部）
4. 基础设施层 → 领域层 + 公共模块（实现领域接口）

**规则：领域层不能依赖基础设施层、API 层。遵循依赖倒置。**

### 4.4 模块间交互方式

| 交互方式 | 使用场景 | 示例 |
|----------|----------|------|
| 应用服务直接调用 | 同步、同一事务 | 下单时查询用户信息 |
| 领域事件（Spring Event） | 异步、最终一致 | 用户注册后发欢迎邮件 |
| RabbitMQ 消息 | 跨模块、异步、高可靠 | 支付成功后通知履约 |
| OpenFeign / REST（未来微服务） | 跨服务同步调用 | 微服务拆分后 |

---

## 五、命名规范

### 5.1 Java 命名

| 类型 | 命名规则 | 示例 |
|------|----------|------|
| 包名 | 全小写，点分隔 | `com.cloudmall.domain.user.model.entity` |
| 类名 | 大驼峰 | `UserApplicationService` |
| 接口名 | 大驼峰，不加 I 前缀 | `UserRepository` |
| 方法名 | 小驼峰 | `findByEmail()` |
| 常量 | 全大写下划线 | `MAX_LOGIN_ATTEMPTS` |
| 变量 | 小驼峰 | `userRepository` |

### 5.2 类命名后缀

| 层级 | 后缀 | 示例 |
|------|------|------|
| Controller | `Controller` | `UserController` |
| 应用服务 | `ApplicationService` | `UserApplicationService` |
| 领域服务 | `DomainService` | `UserDomainService` |
| 仓储接口 | `Repository` | `UserRepository` |
| 仓储实现 | `RepositoryImpl` | `UserRepositoryImpl` |
| MyBatis Mapper | `Mapper` | `UserMapper` |
| 实体 | 无后缀（直接用业务名） | `User`, `Order` |
| 值对象 | 无后缀 | `Email`, `Password` |
| 聚合根 | 无后缀 | `Order`（订单是聚合根） |
| DTO | `DTO` / `Command` / `Query` / `VO` | `RegisterUserCommand`, `UserVO` |
| 领域事件 | `Event` | `UserRegisteredEvent` |
| 异常 | `Exception` | `UserNotFoundException` |
| 转换器 | `Converter` / `Assembler` | `UserConverter` |
| 配置类 | `Config` / `Configuration` | `SecurityConfig` |

### 5.3 方法命名规范

| 操作 | 前缀 | 示例 |
|------|------|------|
| 查询单个 | `find` | `findById()`, `findByEmail()` |
| 查询列表 | `find` / `list` | `findAll()`, `listByStatus()` |
| 分页查询 | `page` | `pageByCondition()` |
| 统计 | `count` | `countByStatus()` |
| 保存（新建/更新） | `save` | `save(User user)` |
| 新增 | `add` / `create` | `createOrder()` |
| 更新 | `update` / `modify` | `updateStatus()` |
| 删除 | `remove` / `delete` | `removeById()` |
| 校验 | `validate` / `check` | `checkEmailUnique()` |

### 5.4 数据库命名

| 对象 | 规则 | 示例 |
|------|------|------|
| 数据库名 | `cloudmall` | — |
| 表名 | `小写下划线，单数` | `user`, `order`, `order_item` |
| 主键 | `id` (BIGINT) | `id` |
| 外键 | `表名_id` | `user_id`, `order_id` |
| 审计字段 | `created_at`, `updated_at`, `created_by`, `updated_by` | — |
| 逻辑删除 | `deleted` (TINYINT, 0/1) | `deleted` |
| 索引 | `idx_表名_字段` | `idx_user_email` |
| 唯一约束 | `uk_表名_字段` | `uk_user_email` |

### 5.5 REST API 命名

| 操作 | 方法 | URL | 示例 |
|------|------|-----|------|
| 列表 | GET | `/api/v1/{资源}` | `GET /api/v1/users` |
| 详情 | GET | `/api/v1/{资源}/{id}` | `GET /api/v1/users/1` |
| 新增 | POST | `/api/v1/{资源}` | `POST /api/v1/users` |
| 修改 | PUT | `/api/v1/{资源}/{id}` | `PUT /api/v1/users/1` |
| 删除 | DELETE | `/api/v1/{资源}/{id}` | `DELETE /api/v1/users/1` |
| 动作 | POST | `/api/v1/{资源}/{id}/{动作}` | `POST /api/v1/orders/1/cancel` |

---

## 六、Git 提交规范

### 6.1 分支策略

```
main          # 生产分支（受保护）
├── develop   # 开发分支
│   ├── feature/xxx-xxx   # 功能分支
│   ├── bugfix/xxx-xxx    # 修复分支
│   └── hotfix/xxx-xxx    # 热修复分支
└── release/x.x.x         # 发布分支
```

### 6.2 Commit Message 格式

```
<type>(<scope>): <subject>

[body]

[footer]
```

| type | 说明 |
|------|------|
| `feat` | 新功能 |
| `fix` | Bug 修复 |
| `docs` | 文档变更 |
| `style` | 代码格式（不影响功能） |
| `refactor` | 重构 |
| `perf` | 性能优化 |
| `test` | 测试相关 |
| `chore` | 构建/工具/依赖 |
| `revert` | 回滚 |

**示例：**
```
feat(user): 实现用户注册功能

- 新增 UserController、UserApplicationService、UserDomainService
- 新增邮箱唯一性校验
- 新增 Flyway 迁移脚本 V2__create_user_table.sql

Closes #12
```

### 6.3 提交频次
- 每个可验收步骤至少提交一次
- 一天内多次提交，保持原子性
- 禁止提交无法编译的代码到 develop

---

## 七、测试规范

### 7.1 测试金字塔

```
        ┌──────┐
        │ E2E  │  ← 少量关键流程
       ┌┴──────┴┐
       │ 集成测试 │  ← API 级别，Testcontainers
      ┌┴────────┴┐
      │  单元测试  │  ← 领域逻辑，覆盖率 > 70%
     └───────────┘
```

### 7.2 测试要求

| 测试类型 | 要求 | 工具 |
|----------|------|------|
| 单元测试 | 领域服务 100%，应用服务 80% | JUnit 5, Mockito |
| 集成测试 | 仓储实现、API 接口 | Spring Boot Test, Testcontainers |
| 架构测试 | 包依赖规则 | ArchUnit |
| E2E | 核心流程（下单、支付） | 后期引入 |

### 7.3 测试命名

```
格式: should_{预期行为}_when_{条件}

示例:
- should_throw_exception_when_email_already_exists()
- should_return_user_when_find_by_valid_id()
- should_create_order_successfully_when_stock_is_sufficient()
```

### 7.4 测试文件位置

```
domain 模块:
  src/test/java/.../domain/{领域}/model/entity/  ← 实体单元测试
  src/test/java/.../domain/{领域}/service/       ← 领域服务测试

boot 模块:
  src/test/java/.../arch/                        ← ArchUnit 架构测试
  src/test/java/.../integration/                 ← API 集成测试
```

---

## 八、开发阶段计划（敏捷迭代）

### 阶段 0：项目骨架搭建（当前阶段）
**目标**：项目能启动、能访问 Swagger、能连接数据库

**范围**：
- Maven 多模块项目初始化
- 公共模块（统一返回、统一异常、基础类）
- 基础设施模块（MyBatis Plus、Redis、RabbitMQ 配置）
- 启动模块（Spring Boot 主程序、多环境配置）
- Flyway 初始脚本
- Swagger / OpenAPI 集成
- Docker Compose 开发环境

**产出物**：
- 项目根 POM + 子模块 POM
- `cloudmall-common` 模块（BaseEntity, Result, BusinessException, 枚举等）
- `cloudmall-infrastructure` 模块（各中间件配置）
- `cloudmall-boot` 模块（启动类、application-*.yml）
- Docker Compose（MySQL, Redis, RabbitMQ）
- 一个 HealthController 验证框架可用

**验收标准**：
1. `mvn clean install` 成功
2. 应用启动成功，端口 8080
3. Swagger UI 可访问：`http://localhost:8080/swagger-ui.html`
4. `/api/v1/health` 返回 `{"code":200,"message":"success","data":"OK"}`
5. ArchUnit 测试通过（包依赖规则校验）
6. MySQL、Redis、RabbitMQ 连接正常

---

### 阶段 1：账号与权限中心
**目标**：用户注册、登录、权限管理

**范围**：
- 用户领域模块 `cloudmall-domain-user`
- JWT 认证 + Spring Security 配置
- RBAC 权限模型（用户-角色-权限）
- 用户注册/登录/信息修改
- 管理员用户管理

**核心接口**：
- `POST /api/v1/auth/register` — 注册
- `POST /api/v1/auth/login` — 登录
- `GET /api/v1/users/me` — 当前用户信息
- `GET /api/v1/admin/users` — 管理员用户列表
- `POST /api/v1/admin/roles` — 创建角色
- `POST /api/v1/admin/permissions` — 分配权限

**验收标准**：
1. 注册后密码 BCrypt 加密存储
2. 登录返回 JWT，后续请求携带 JWT
3. 无权限用户访问管理接口返回 403
4. 管理员可创建角色并分配权限
5. 领域服务单元测试覆盖率 > 80%
6. 集成测试：注册 → 登录 → 访问受保护接口
7. Swagger 文档完整

---

### 阶段 2：商品中心
**目标**：商品 CRUD、分类管理、商品搜索

**范围**：
- 商品领域模块 `cloudmall-domain-product`
- 商品分类（多级）
- 商品属性（规格、参数）
- 商品 SKU
- 商品图片
- 商品上下架

**核心接口**：
- `POST /api/v1/admin/products` — 创建商品
- `PUT /api/v1/admin/products/{id}` — 更新商品
- `GET /api/v1/products` — 商品列表（分页、搜索）
- `GET /api/v1/products/{id}` — 商品详情
- `GET /api/v1/categories` — 分类树
- `POST /api/v1/admin/products/{id}/shelve` — 上架

**验收标准**：
1. 完整商品 CRUD
2. 分类树查询正确
3. SKU 正确生成
4. 分页搜索功能
5. 领域服务单元测试覆盖率 > 80%

---

### 阶段 3：库存中心
**目标**：库存管理、库存变更、库存预警

**范围**：
- 库存领域模块 `cloudmall-domain-inventory`
- 库存初始化
- 入库/出库
- 库存扣减（下单时）
- 库存释放（取消订单时）
- 库存预警
- 库存变更日志

**核心接口**：
- `POST /api/v1/admin/inventory/initialize` — 初始化库存
- `POST /api/v1/admin/inventory/inbound` — 入库
- `POST /api/v1/inventory/deduct` — 扣减库存（内部调用）
- `POST /api/v1/inventory/release` — 释放库存（内部调用）

**验收标准**：
1. 库存扣减并发安全（乐观锁/悲观锁验证）
2. 库存不足时正确拒绝
3. 库存变更日志完整
4. 集成测试：并发扣减不超卖
5. 领域服务单元测试覆盖率 > 80%

---

### 阶段 4：购物车中心
**目标**：购物车 CRUD、价格实时计算

**范围**：
- 购物车领域模块 `cloudmall-domain-cart`
- 添加商品到购物车
- 修改数量
- 删除购物车项
- 购物车价格计算（与价格中心协作）
- 登录前后购物车合并

**核心接口**：
- `GET /api/v1/cart` — 获取购物车
- `POST /api/v1/cart/items` — 添加商品
- `PUT /api/v1/cart/items/{id}` — 修改数量
- `DELETE /api/v1/cart/items/{id}` — 删除项
- `POST /api/v1/cart/merge` — 合并购物车

**验收标准**：
1. 购物车 CRUD 完整
2. 商品数量校验（不超过库存）
3. 总价实时计算正确
4. 登录/登出购物车合并

---

### 阶段 5：价格中心
**目标**：价格策略、多维度定价

**范围**：
- 价格领域模块 `cloudmall-domain-price`
- 原价、售价、会员价
- 阶梯价格
- 价格历史
- 价格变更通知

**验收标准**：
1. 多价格类型支持
2. 价格历史可追溯
3. 价格变更通知

---

### 阶段 6：营销中心
**目标**：优惠券、满减活动

**范围**：
- 营销领域模块 `cloudmall-domain-marketing`
- 优惠券管理（创建、发放、使用）
- 满减活动
- 限时折扣
- 优惠计算规则引擎

**验收标准**：
1. 优惠券完整生命周期
2. 满减活动正确计算
3. 优惠叠加规则正确

---

### 阶段 7：订单中心
**目标**：下单流程、订单状态机

**范围**：
- 订单领域模块 `cloudmall-domain-order`
- 订单创建（聚合多个领域）
- 订单状态机
- 订单查询
- 订单取消
- 订单超时处理

**核心接口**：
- `POST /api/v1/orders` — 创建订单
- `GET /api/v1/orders` — 订单列表
- `GET /api/v1/orders/{id}` — 订单详情
- `POST /api/v1/orders/{id}/cancel` — 取消订单
- `POST /api/v1/orders/{id}/confirm` — 确认收货

**验收标准**：
1. 下单流程完整（库存扣减、价格计算、优惠使用）
2. 订单状态机正确流转
3. 超时订单自动取消（RabbitMQ 延迟消息）
4. 集成测试：完整下单流程

---

### 阶段 8：支付中心
**目标**：支付集成、支付状态同步

**范围**：
- 支付领域模块 `cloudmall-domain-payment`
- 支付单创建
- 支付回调处理
- 支付退款
- 支付记录

**验收标准**：
1. 支付单生成正确
2. 支付回调状态更新正确
3. 退款流程完整

---

### 阶段 9：履约物流中心
**目标**：发货、物流跟踪

**范围**：
- 履约领域模块 `cloudmall-domain-fulfillment`
- 发货单创建
- 物流信息更新
- 签收确认

**验收标准**：
1. 发货流程完整
2. 物流信息可追踪

---

### 阶段 10：售后中心
**目标**：退货退款、售后流程

**验收标准**：
1. 售后申请完整
2. 退款金额正确
3. 售后状态正确流转

---

### 阶段 11：后台管理系统
**目标**：运营后台、数据看板

**验收标准**：
1. 后台管理功能完整
2. 数据看板基本可用

---

### 阶段 12：前端商城
**目标**：面向买家的购物前端

**验收标准**：
1. 商品浏览、搜索
2. 购物车
3. 下单流程
4. 个人中心

---

### 阶段 13：审计与通知中心
**目标**：操作审计、消息通知

### 阶段 14：报表中心
**目标**：经营报表、数据导出

### 阶段 15：性能优化与压测
**目标**：核心接口性能调优

---

## 九、每阶段验收模板

每个阶段结束时，必须完成以下验收清单：

### 代码质量
- [ ] `mvn clean install` 成功（无编译错误）
- [ ] `mvn test` 全部通过
- [ ] 业务代码测试覆盖率 > 目标值
- [ ] ArchUnit 架构测试通过
- [ ] SonarLint / Checkstyle 无严重告警

### 功能完整性
- [ ] 接口与 Swagger 文档一致
- [ ] 所有参数校验完备
- [ ] 统一异常处理覆盖
- [ ] 审计字段正确填充
- [ ] 日志记录完整

### 数据库
- [ ] Flyway 迁移脚本版本正确
- [ ] 索引合理创建
- [ ] 无 N+1 查询问题

### API
- [ ] 所有接口返回统一 Result 结构
- [ ] 分页接口返回统一 PageResult
- [ ] 错误码规范

### 安全性
- [ ] 敏感接口有权限保护
- [ ] SQL 注入防护（MyBatis `#{}` 而非 `${}`）
- [ ] XSS 过滤
- [ ] CORS 配置正确

---

## 十、统一返回结构

```java
// 成功
{
  "code": 200,
  "message": "success",
  "data": { ... },
  "timestamp": 1712345678000
}

// 失败
{
  "code": 40001,
  "message": "邮箱已存在",
  "data": null,
  "timestamp": 1712345678000
}

// 分页
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [...],
    "total": 100,
    "page": 1,
    "size": 20
  },
  "timestamp": 1712345678000
}
```

### 错误码规范

| 范围 | 类别 |
|------|------|
| 40000-40099 | 参数校验错误 |
| 40100-40199 | 认证错误 |
| 40300-40399 | 授权错误 |
| 40400-40499 | 资源不存在 |
| 40900-40999 | 业务冲突（如库存不足） |
| 50000-50099 | 系统内部错误 |

---

## 十一、关键约定

1. **不要把所有逻辑堆在 Controller 或 Service 中** — 严格按 DDD 分层
2. **领域层不能依赖框架** — 领域层只使用 Java 标准库 + 公共工具
3. **每次只做当前阶段的事** — 不要提前实现后续阶段功能
4. **不要生成无法运行的伪代码** — 每个阶段必须有可运行的代码
5. **不要留下 TODO 代替核心逻辑** — TODO 仅可用于优化项
6. **不要绕过测试** — 测试与代码同等重要
7. **保持模块独立性** — 模块间通过接口和事件通信，不直接引用实现类
8. **数据库迁移用 Flyway** — 不允许手动修改数据库结构
9. **所有时间字段用 LocalDateTime** — 不用 Date
10. **所有金额字段用 BigDecimal** — 不用 float/double

---

*本文件作为项目的最高开发准则。所有开发人员（包括 AI 助手）必须严格遵守。*
*最后更新：2026-07-04*
