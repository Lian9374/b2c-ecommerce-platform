# CloudMall — 企业级 B2C 电商平台

## 项目简介

CloudMall 是一个从零构建的企业级 B2C 电商平台，采用 DDD（领域驱动设计）架构，使用模块化单体架构，保留未来拆分微服务的能力。

## 技术栈

### 后端
- **语言**: Java 17
- **框架**: Spring Boot 3.3.x
- **构建**: Maven 多模块
- **ORM**: MyBatis Plus
- **数据库**: MySQL 8.0 + Flyway 迁移
- **缓存**: Redis 7.x
- **消息队列**: RabbitMQ
- **安全**: Spring Security + JWT
- **API 文档**: SpringDoc OpenAPI (Swagger)
- **测试**: JUnit 5 + Testcontainers + ArchUnit

### 前端
- **框架**: Vue 3 + TypeScript
- **构建**: Vite
- **UI 库**: Element Plus
- **状态管理**: Pinia
- **路由**: Vue Router

### DevOps
- **容器化**: Docker + Docker Compose
- **CI/CD**: GitHub Actions
- **反向代理**: Nginx

## 快速开始

### 前置条件
- JDK 17+
- Maven 3.9+
- Docker Desktop
- Node.js 18+ (前端)

### 启动开发环境

```bash
# 1. 启动基础设施（MySQL, Redis, RabbitMQ）
docker-compose -f docker/docker-compose.yml up -d

# 2. 构建项目
mvn clean install -DskipTests

# 3. 启动应用
cd cloudmall-boot
mvn spring-boot:run -Dspring.profiles.active=dev

# 4. 访问 Swagger
open http://localhost:8080/swagger-ui.html
```

## 项目结构

```
b2c-ecommerce-platform/
├── cloudmall-common/        # 公共模块（基础类、异常、工具）
├── cloudmall-domain/        # 领域模块（DDD 核心）
├── cloudmall-infrastructure/# 基础设施（安全、缓存、MQ）
├── cloudmall-api/           # API 层（Controller）
├── cloudmall-boot/          # 启动模块
└── cloudmall-ui/            # 前端
```

## 开发阶段

详见 [CLAUDE.md](./CLAUDE.md) 中的开发阶段计划。

- 阶段 0：项目骨架搭建 ✅（规划中）
- 阶段 1：账号与权限中心
- 阶段 2：商品中心
- 阶段 3：库存中心
- ...（共 15 个阶段）

## 开发规范

严格遵循 [CLAUDE.md](./CLAUDE.md) 中定义的 DDD 分层规范、命名规范、Git 提交规范和测试规范。

## License

MIT
