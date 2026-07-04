# CloudMall 领域模型文档

> 版本：v1.0 | 日期：2026-07-04

---

## 一、限界上下文总览

```
┌──────────────────────────────────────────────────────────────────────┐
│                        CloudMall 限界上下文                           │
│                                                                      │
│  ┌──────────┐  ┌──────────────┐  ┌──────────┐  ┌───────────────┐   │
│  │ 用户上下文 │  │  账号认证上下文 │  │ 商品上下文 │  │  库存上下文    │   │
│  │  (User)   │  │   (Auth)     │  │(Product)  │  │  (Inventory)  │   │
│  └────┬─────┘  └──────┬───────┘  └─────┬─────┘  └───────┬───────┘   │
│       │               │                │                │            │
│  ┌────┴─────┐  ┌──────┴───────┐  ┌─────┴─────┐  ┌───────┴───────┐   │
│  │ 购物车上下文│  │  价格上下文   │  │ 营销上下文  │  │  订单上下文    │   │
│  │  (Cart)   │  │   (Price)    │  │(Marketing) │  │   (Order)     │   │
│  └────┬─────┘  └──────┬───────┘  └─────┬─────┘  └───────┬───────┘   │
│       │               │                │                │            │
│  ┌────┴─────┐  ┌──────┴───────┐  ┌─────┴─────┐  ┌───────┴───────┐   │
│  │ 支付上下文 │  │ 履约物流上下文 │  │ 售后上下文  │  │  审计上下文    │   │
│  │(Payment)  │  │(Fulfillment) │  │(AfterSale) │  │   (Audit)     │   │
│  └──────────┘  └──────────────┘  └───────────┘  └───────────────┘   │
│                                                                      │
│  ┌──────────┐  ┌──────────────┐                                     │
│  │ 通知上下文 │  │  报表上下文   │  ← 支撑域                            │
│  │(Notification)│  (Report)   │                                      │
│  └──────────┘  └──────────────┘                                     │
└──────────────────────────────────────────────────────────────────────┘
```

## 二、限界上下文详细定义

### 2.1 用户上下文 (User Context)

**职责**：管理用户基础信息、收货地址

| 元素 | 类型 | 说明 |
|------|------|------|
| **User** | 聚合根 | 用户基础信息（昵称、手机号、邮箱、头像、状态） |
| UserAddress | 实体 | 收货地址（联系人、电话、省市区、详细地址、是否默认） |
| UserStatus | 值对象 | 用户状态枚举（正常/冻结/注销） |
| Gender | 值对象 | 性别枚举 |
| **UserRegisteredEvent** | 领域事件 | 用户注册成功 |
| UserProfileUpdatedEvent | 领域事件 | 用户信息更新 |
| UserAddressAddedEvent | 领域事件 | 新增收货地址 |

**聚合根：User**
```
User (聚合根)
├── id: Long
├── nickname: String
├── email: Email (值对象)
├── phone: PhoneNumber (值对象)
├── avatar: String
├── status: UserStatus
├── gender: Gender
├── createdAt: LocalDateTime
├── updatedAt: LocalDateTime
└── addresses: List<UserAddress> (实体集合，可独立操作)
```

### 2.2 账号认证上下文 (Auth Context)

**职责**：用户认证、角色权限管理

| 元素 | 类型 | 说明 |
|------|------|------|
| **Account** | 聚合根 | 登录账号（用户名、加密密码、关联 UserId） |
| Role | 实体 | 角色（角色名、角色编码、权限列表） |
| Permission | 值对象 | 权限（权限编码、资源路径、操作类型） |
| LoginRecord | 实体 | 登录记录（IP、时间、设备） |
| **UserLoggedInEvent** | 领域事件 | 用户登录成功 |
| PasswordResetEvent | 领域事件 | 密码重置 |
| RoleAssignedEvent | 领域事件 | 角色分配 |

**聚合根：Account**
```
Account (聚合根)
├── id: Long
├── userId: Long (关联 User)
├── username: String
├── password: EncodedPassword (值对象)
├── roles: List<Role>
├── lastLoginAt: LocalDateTime
├── loginRecords: List<LoginRecord>
└── status: AccountStatus
```

### 2.3 商品上下文 (Product Context)

**职责**：商品管理、分类管理、品牌管理

| 元素 | 类型 | 说明 |
|------|------|------|
| **Product** | 聚合根 | 商品（名称、描述、品牌、分类、状态） |
| ProductSku | 实体 | SKU（规格组合、售价、成本价、条形码） |
| ProductImage | 值对象 | 商品图片（URL、类型、排序） |
| ProductSpec | 值对象 | 规格项（颜色、尺码、容量等） |
| ProductSpecValue | 值对象 | 规格值（红色、XL、500ml等） |
| Category | 实体 | 商品分类（支持多级树形结构） |
| Brand | 实体 | 品牌 |
| **ProductCreatedEvent** | 领域事件 | 商品创建 |
| ProductShelvedEvent | 领域事件 | 商品上架 |
| ProductOffShelvedEvent | 领域事件 | 商品下架 |

**聚合根：Product**
```
Product (聚合根)
├── id: Long
├── name: String
├── subtitle: String
├── description: String (富文本)
├── categoryId: Long
├── brandId: Long
├── status: ProductStatus (草稿/已上架/已下架)
├── skus: List<ProductSku>
│   ├── id: Long
│   ├── skuCode: String
│   ├── specCombination: Map<String, String>
│   ├── salePrice: BigDecimal
│   ├── costPrice: BigDecimal
│   └── barcode: String
├── images: List<ProductImage>
├── specs: List<ProductSpec>
│   └── values: List<ProductSpecValue>
├── createdAt: LocalDateTime
└── updatedAt: LocalDateTime
```

### 2.4 库存上下文 (Inventory Context)

**职责**：库存管理、库存扣减/释放、库存预警

| 元素 | 类型 | 说明 |
|------|------|------|
| **Inventory** | 聚合根 | 库存（SKU 对应库存量） |
| StockChangeLog | 实体 | 库存变更日志 |
| **StockDeductedEvent** | 领域事件 | 库存扣减成功 |
| StockReleasedEvent | 领域事件 | 库存释放 |
| StockLowEvent | 领域事件 | 库存预警 |

**聚合根：Inventory**
```
Inventory (聚合根)
├── id: Long
├── skuId: Long
├── skuCode: String
├── availableStock: Integer (可用库存)
├── lockedStock: Integer (锁定库存，下单未支付)
├── totalStock: Integer (总库存 = 可用 + 锁定)
├── safetyStock: Integer (安全库存阈值)
├── version: Integer (乐观锁)
├── changeLogs: List<StockChangeLog>
└── updatedAt: LocalDateTime
```

### 2.5 购物车上下文 (Cart Context)

**职责**：购物车管理、购物车项维护

| 元素 | 类型 | 说明 |
|------|------|------|
| **Cart** | 聚合根 | 购物车（归属用户） |
| CartItem | 实体 | 购物车项（SKU + 数量 + 选中状态） |
| **ItemAddedEvent** | 领域事件 | 商品加入购物车 |
| ItemRemovedEvent | 领域事件 | 商品移出购物车 |

**聚合根：Cart**
```
Cart (聚合根)
├── id: Long
├── userId: Long
├── items: List<CartItem>
│   ├── id: Long
│   ├── skuId: Long
│   ├── productId: Long
│   ├── productName: String
│   ├── skuSpec: String
│   ├── price: BigDecimal (加入时价格，快照)
│   ├── quantity: Integer
│   └── selected: Boolean
└── updatedAt: LocalDateTime
```

### 2.6 价格上下文 (Price Context)

**职责**：价格策略、价格历史、多维度定价

| 元素 | 类型 | 说明 |
|------|------|------|
| **PriceRecord** | 聚合根 | 价格记录 |
| PriceHistory | 实体 | 价格变更历史 |
| PriceType | 值对象 | 价格类型（原价/售价/会员价/活动价） |
| **PriceChangedEvent** | 领域事件 | 价格变更 |

**聚合根：PriceRecord**
```
PriceRecord (聚合根)
├── id: Long
├── skuId: Long
├── originalPrice: BigDecimal
├── sellingPrice: BigDecimal
├── memberPrice: BigDecimal (可空)
├── activePrice: BigDecimal (活动价，可空)
├── history: List<PriceHistory>
└── updatedAt: LocalDateTime
```

### 2.7 营销上下文 (Marketing Context)

**职责**：优惠券、满减活动、促销活动管理

| 元素 | 类型 | 说明 |
|------|------|------|
| **Coupon** | 聚合根 | 优惠券模板 |
| UserCoupon | 实体 | 用户持有的优惠券 |
| **Promotion** | 聚合根 | 促销活动（满减/折扣） |
| PromotionRule | 值对象 | 促销规则（条件 + 优惠） |
| **CouponIssuedEvent** | 领域事件 | 优惠券发放 |
| CouponUsedEvent | 领域事件 | 优惠券使用 |
| PromotionStartedEvent | 领域事件 | 促销活动开始 |

**聚合根：Coupon**
```
Coupon (聚合根)
├── id: Long
├── name: String
├── type: CouponType (满减券/折扣券/直减券)
├── threshold: BigDecimal (满多少可用)
├── discountValue: BigDecimal (减多少/打几折)
├── totalQuantity: Integer
├── issuedQuantity: Integer
├── usedQuantity: Integer
├── validDays: Integer
├── startTime: LocalDateTime
├── endTime: LocalDateTime
└── status: CouponStatus
```

**聚合根：Promotion**
```
Promotion (聚合根)
├── id: Long
├── name: String
├── type: PromotionType (满减/折扣/秒杀)
├── rules: List<PromotionRule>
├── startTime: LocalDateTime
├── endTime: LocalDateTime
├── status: PromotionStatus
└── applicableProducts: List<Long> (适用商品)
```

### 2.8 订单上下文 (Order Context)

**职责**：订单创建、订单状态流转、订单管理

| 元素 | 类型 | 说明 |
|------|------|------|
| **Order** | 聚合根 | 订单（包含订单项、收货地址、金额） |
| OrderItem | 实体 | 订单项（商品快照、SKU 快照、当时价格） |
| OrderAddress | 值对象 | 收货地址快照 |
| OrderAmount | 值对象 | 订单金额（商品总价、运费、优惠、实付） |
| OrderStatus | 值对象 | 订单状态枚举 |
| **OrderCreatedEvent** | 领域事件 | 订单创建 |
| OrderPaidEvent | 领域事件 | 订单已支付 |
| OrderCancelledEvent | 领域事件 | 订单已取消 |
| OrderDeliveredEvent | 领域事件 | 订单已发货 |
| OrderCompletedEvent | 领域事件 | 订单已完成 |

**聚合根：Order**
```
Order (聚合根)
├── id: Long
├── orderNo: String (订单编号，全局唯一)
├── userId: Long
├── status: OrderStatus
│   └── PENDING_PAY → PAID → DELIVERED → RECEIVED → COMPLETED
│            ↓            ↓
│        CANCELLED    CANCELLED(退款)
├── items: List<OrderItem>
│   ├── id: Long
│   ├── productId: Long
│   ├── productName: String
│   ├── skuId: Long
│   ├── skuSpec: String
│   ├── productImage: String
│   ├── price: BigDecimal (下单时价格)
│   └── quantity: Integer
├── address: OrderAddress
│   ├── receiverName: String
│   ├── receiverPhone: String
│   ├── province: String
│   ├── city: String
│   ├── district: String
│   └── detailAddress: String
├── amount: OrderAmount
│   ├── totalAmount: BigDecimal
│   ├── discountAmount: BigDecimal
│   ├── shippingFee: BigDecimal
│   └── payAmount: BigDecimal
├── couponId: Long (可空)
├── couponDiscount: BigDecimal
├── remark: String
├── payTime: LocalDateTime
├── deliverTime: LocalDateTime
├── receiveTime: LocalDateTime
├── cancelTime: LocalDateTime
├── cancelReason: String
├── createdAt: LocalDateTime
└── updatedAt: LocalDateTime
```

### 2.9 支付上下文 (Payment Context)

**职责**：支付管理、支付回调、退款

| 元素 | 类型 | 说明 |
|------|------|------|
| **Payment** | 聚合根 | 支付单 |
| Refund | 实体 | 退款记录 |
| PaymentMethod | 值对象 | 支付方式枚举 |
| PaymentStatus | 值对象 | 支付状态枚举 |
| **PaymentSucceededEvent** | 领域事件 | 支付成功 |
| PaymentFailedEvent | 领域事件 | 支付失败 |
| RefundProcessedEvent | 领域事件 | 退款处理 |

**聚合根：Payment**
```
Payment (聚合根)
├── id: Long
├── paymentNo: String
├── orderId: Long
├── orderNo: String
├── userId: Long
├── amount: BigDecimal
├── method: PaymentMethod (微信/支付宝/银行卡)
├── status: PaymentStatus (待支付/支付成功/支付失败/已退款)
├── thirdPartyTradeNo: String (第三方交易号)
├── paidAt: LocalDateTime
├── refunds: List<Refund>
│   ├── id: Long
│   ├── refundNo: String
│   ├── amount: BigDecimal
│   ├── reason: String
│   ├── status: RefundStatus
│   └── createdAt: LocalDateTime
└── createdAt: LocalDateTime
```

### 2.10 履约物流上下文 (Fulfillment Context)

**职责**：发货管理、物流跟踪

| 元素 | 类型 | 说明 |
|------|------|------|
| **Shipment** | 聚合根 | 发货单 |
| LogisticsRecord | 实体 | 物流轨迹 |
| **ShipmentCreatedEvent** | 领域事件 | 发货单创建 |
| LogisticsUpdatedEvent | 领域事件 | 物流更新 |
| ShipmentDeliveredEvent | 领域事件 | 已签收 |

**聚合根：Shipment**
```
Shipment (聚合根)
├── id: Long
├── shipmentNo: String
├── orderId: Long
├── orderNo: String
├── carrier: String (物流公司)
├── trackingNo: String (运单号)
├── status: ShipmentStatus (待发货/已发货/运输中/已签收)
├── logisticsRecords: List<LogisticsRecord>
│   ├── id: Long
│   ├── status: String
│   ├── location: String
│   ├── description: String
│   └── timestamp: LocalDateTime
├── shippedAt: LocalDateTime
├── deliveredAt: LocalDateTime
└── createdAt: LocalDateTime
```

### 2.11 售后上下文 (AfterSale Context)

**职责**：退货退款、售后流程

| 元素 | 类型 | 说明 |
|------|------|------|
| **AfterSaleOrder** | 聚合根 | 售后单 |
| AfterSaleType | 值对象 | 售后类型（仅退款/退货退款） |
| AfterSaleStatus | 值对象 | 售后状态 |
| **AfterSaleAppliedEvent** | 领域事件 | 售后申请 |
| AfterSaleApprovedEvent | 领域事件 | 售后审核通过 |
| AfterSaleCompletedEvent | 领域事件 | 售后完成 |

### 2.12 审计上下文 (Audit Context)

**职责**：操作审计、日志记录

| 元素 | 类型 | 说明 |
|------|------|------|
| **AuditLog** | 聚合根 | 审计日志 |
| OperationType | 值对象 | 操作类型枚举 |
| **AuditLogCreatedEvent** | 领域事件 | 审计日志记录（异步消费） |

### 2.13 通知上下文 (Notification Context)

**职责**：消息通知、模板管理

| 元素 | 类型 | 说明 |
|------|------|------|
| **Notification** | 聚合根 | 通知消息 |
| NotificationTemplate | 实体 | 通知模板 |
| NotificationChannel | 值对象 | 通知渠道（站内信/短信/邮件） |
| **NotificationSentEvent** | 领域事件 | 通知发送 |

### 2.14 报表上下文 (Report Context)

**职责**：经营数据报表、数据导出

---

## 三、上下文映射关系

```
                    ┌──────────┐
                    │ 用户上下文 │
                    └────┬─────┘
                         │ 关联 (Shared Kernel: UserId)
          ┌──────────────┼──────────────┐
          │              │              │
    ┌─────┴─────┐  ┌────┴────┐  ┌──────┴──────┐
    │ 购物车上下文 │  │ 订单上下文 │  │ 账号认证上下文 │
    └─────┬─────┘  └────┬────┘  └─────────────┘
          │              │
          │    ┌─────────┼─────────┐
          │    │         │         │
    ┌─────┴────┴─┐ ┌────┴────┐ ┌──┴──────┐
    │  价格上下文  │ │ 支付上下文 │ │ 履约上下文 │
    └─────┬──────┘ └────┬────┘ └────┬─────┘
          │              │          │
    ┌─────┴─────┐  ┌────┴────┐     │
    │  库存上下文  │  │ 售后上下文 │◄───┘
    └───────────┘  └─────────┘
```

### 上下文间交互模式

| 调用方向 | 交互方式 | 说明 |
|----------|----------|------|
| 订单 → 用户 | 应用服务同步调用 | 下单时查询用户信息和地址 |
| 订单 → 商品 | 应用服务同步调用 | 下单时校验商品状态和获取价格 |
| 订单 → 库存 | 领域事件 + 同步调用 | 下单扣库存（同步）、取消释放（事件） |
| 订单 → 营销 | 应用服务同步调用 | 下单时计算优惠 |
| 支付 → 订单 | 领域事件 | 支付成功 → OrderPaidEvent |
| 支付 → 库存 | 应用服务调用 | 退款成功 → 恢复库存 |
| 订单 → 履约 | 领域事件 | 支付成功 → 创建发货单 |
| 订单 → 售后 | 领域事件 | 售后申请 → 关联订单 |
| 全局 → 审计 | 领域事件（异步） | 所有操作事件 → 审计日志记录 |
| 全局 → 通知 | 领域事件（异步） | 关键事件 → 站内信/短信/邮件 |

---

## 四、核心业务流程的事件驱动链

### 4.1 下单流程事件链

```
用户点击"提交订单"
  → OrderApplicationService.createOrder()
    → 同步：校验商品（Product Context）
    → 同步：计算价格（Price Context）
    → 同步：计算优惠（Marketing Context）
    → 同步：扣减库存（Inventory Context）
    → 保存订单
    → 发布 OrderCreatedEvent
      → 异步：发通知（Notification Context）
      → 异步：记审计（Audit Context）
      → 启动延迟消息（30分钟超时取消）
```

### 4.2 支付成功事件链

```
支付回调 / 查询支付成功
  → PaymentApplicationService.handleCallback()
    → 更新支付单状态
    → 发布 PaymentSucceededEvent
      → Order Context：更新订单为已支付
      → Fulfillment Context：创建发货单
      → Marketing Context：标记优惠券已使用
      → Notification Context：发送支付成功通知
      → Audit Context：记录支付审计
```

### 4.3 订单超时取消事件链

```
延迟消息触发（30分钟）
  → OrderApplicationService.cancelTimeoutOrder()
    → 更新订单状态为已取消
    → 发布 OrderCancelledEvent
      → Inventory Context：释放锁定库存
      → Marketing Context：恢复优惠券
      → Payment Context：发起退款（如已支付）
      → Notification Context：发送取消通知
      → Audit Context：记录取消审计
```

---

## 五、领域事件清单

| 事件 | 发布者 | 消费者 | 传输方式 |
|------|--------|--------|----------|
| UserRegisteredEvent | User | Notification, Audit | Spring Event（同步/异步） |
| UserLoggedInEvent | Auth | Audit | Spring Event |
| OrderCreatedEvent | Order | Notification, Audit, 延迟取消 | Spring Event + RabbitMQ |
| OrderPaidEvent | Payment→Order | Fulfillment, Notification | Spring Event |
| OrderCancelledEvent | Order | Inventory, Marketing, Notification, Audit | Spring Event |
| OrderDeliveredEvent | Fulfillment | Order, Notification | Spring Event |
| StockDeductedEvent | Inventory | Audit | Spring Event |
| StockLowEvent | Inventory | Notification | Spring Event |
| PaymentSucceededEvent | Payment | Order, Fulfillment, Notification | Spring Event（同模块） |
| CouponUsedEvent | Marketing | Audit | Spring Event |
| AfterSaleAppliedEvent | AfterSale | Notification, Audit | Spring Event |
| ShipmentDeliveredEvent | Fulfillment | Order | Spring Event |

---

*本文档为领域模型基线，与 CLAUDE.md 架构规范配套使用。*
