# Enterprise Mall — 企业级 B2C 电商平台

## 项目简介

Enterprise Mall 是一个从零构建的企业级 B2C 电商平台，采用 DDD（领域驱动设计）架构，使用模块化单体架构，保留未来拆分微服务的能力。

## 技术栈

### 后端
- **语言**: Java 17
- **框架**: Spring Boot 3.3.5
- **构建**: Maven 多模块 (17 个子模块)
- **ORM**: MyBatis Plus 3.5
- **数据库**: MySQL 8.0 + Flyway 迁移
- **缓存**: Redis 7.x
- **消息队列**: RabbitMQ 3.13
- **安全**: Spring Security + JWT
- **API 文档**: SpringDoc OpenAPI (Swagger)
- **测试**: JUnit 5 + Testcontainers + ArchUnit

### 前端
- **框架**: Vue 3 + TypeScript
- **构建**: Vite 5
- **UI 库**: Element Plus
- **状态管理**: Pinia
- **路由**: Vue Router 4

### DevOps
- **容器化**: Docker + Docker Compose
- **CI/CD**: GitHub Actions
- **反向代理**: Nginx

## 项目结构

```
enterprise-mall/
├── pom.xml                    # 父 POM（依赖管理）
├── mall-common/               # 公共模块（ApiResponse、异常、常量、枚举、基础类）
├── mall-infrastructure/       # 基础设施（MyBatisPlus、Redis、RabbitMQ、Security、Swagger）
├── mall-bootstrap/            # 启动模块（Spring Boot 入口、多环境配置、Flyway、健康检查）
├── mall-auth/                 # 认证鉴权模块
├── mall-user/                 # 用户模块
├── mall-catalog/              # 商品模块
├── mall-inventory/            # 库存模块
├── mall-cart/                 # 购物车模块
├── mall-pricing/              # 价格模块
├── mall-promotion/            # 营销模块
├── mall-order/                # 订单模块
├── mall-payment/              # 支付模块
├── mall-fulfillment/          # 履约模块
├── mall-aftersales/           # 售后模块
├── mall-admin/                # 管理后台模块
├── mall-message/              # 消息通知模块
├── mall-audit/                # 审计模块
├── mall-ui/                   # Vue 3 前端
├── docker/                    # Docker Compose + Nginx 配置
└── docs/                      # 设计文档
```

## 快速开始

### 前置条件
- JDK 17+
- Maven 3.9+
- Docker Desktop
- Node.js 18+ (前端)

### 启动开发环境

```bash
# 1. 启动基础设施（MySQL, Redis, RabbitMQ）
docker compose -f docker/docker-compose.yml up -d

# 2. 构建项目
mvn clean install -DskipTests

# 3. 启动后端
cd mall-bootstrap
mvn spring-boot:run -Dspring.profiles.active=dev

# 4. 启动前端
cd mall-ui
npm install
npm run dev

# 5. 访问
# 后端 Swagger: http://localhost:8080/swagger-ui.html
# 前端页面:     http://localhost:5173/mall
# 管理后台:     http://localhost:5173/admin
```

## 开发阶段

| 阶段 | 内容 | 状态 |
|------|------|------|
| 0 | 项目骨架搭建 | ✅ 已完成 |
| 1 | 账号与权限中心 | 📋 规划中 |
| 2 | 商品中心 | 📋 规划中 |
| 3+ | 后续阶段 | 📋 规划中 |

详见 [CLAUDE.md](./CLAUDE.md) 和 [docs/](./docs/) 目录。

## 设计文档

- [业务需求文档](docs/business-requirements.md)
- [领域模型文档](docs/domain-model.md)
- [架构设计文档](docs/architecture.md)
- [验收计划文档](docs/acceptance-plan.md)

## License

MIT
