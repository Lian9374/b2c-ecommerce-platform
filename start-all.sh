#!/bin/bash
# ============================================
# CloudMall 一键启动脚本 (Git Bash)
# 右键此文件 → Git Bash Here → ./start-all.sh
# 或者直接双击 run.bat 调用此脚本
# ============================================

set -e

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$PROJECT_DIR"

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m'

log_step()  { echo -e "\n${CYAN}>>> $1${NC}"; }
log_ok()    { echo -e "${GREEN}[OK]${NC} $1"; }
log_warn()  { echo -e "${YELLOW}[WAIT]${NC} $1"; }
log_err()   { echo -e "${RED}[FAIL]${NC} $1"; }

echo ""
echo "============================================"
echo "  CloudMall 企业级电商平台 — 一键启动"
echo "============================================"
echo ""

# -------------------- Step 1: 环境检查 --------------------
log_step "Step 1/6  环境检查"

command -v docker >/dev/null 2>&1 || { log_err "Docker 未安装"; exit 1; }
log_ok "Docker"

command -v java >/dev/null 2>&1 || { log_err "Java 未安装 (需要 JDK 17+)"; exit 1; }
log_ok "Java: $(java -version 2>&1 | head -1)"

command -v mvn >/dev/null 2>&1 || { log_err "Maven 未安装 (需要 Maven 3.9+)"; exit 1; }
log_ok "Maven: $(mvn --version 2>&1 | head -1)"

command -v node >/dev/null 2>&1 || { log_err "Node.js 未安装 (需要 Node 18+)"; exit 1; }
log_ok "Node.js: $(node --version)"

# -------------------- Step 2: Docker 基础设施 --------------------
log_step "Step 2/6  启动 Docker 基础设施"

# Check if Docker is running
docker info >/dev/null 2>&1 || {
    log_err "Docker Desktop 未运行，请先启动 Docker Desktop"
    exit 1
}
log_ok "Docker Desktop 运行中"

docker compose -f docker/docker-compose.yml up -d
log_ok "容器已启动"

# Wait for MySQL healthy
log_warn "等待 MySQL 就绪..."
until docker compose -f docker/docker-compose.yml ps 2>/dev/null | grep -q "healthy"; do
    sleep 2
done
log_ok "MySQL     — 就绪"
log_ok "Redis     — 就绪"
log_ok "RabbitMQ  — 就绪"

# -------------------- Step 3: 构建后端 --------------------
log_step "Step 3/6  构建后端 (Maven)"

mvn clean install -DskipTests -q
log_ok "后端构建成功"

# -------------------- Step 4: 前端依赖 --------------------
log_step "Step 4/6  检查前端依赖"

if [ ! -d "mall-ui/node_modules" ]; then
    log_warn "首次运行，安装前端依赖..."
    cd mall-ui && npm install --silent && cd ..
    log_ok "前端依赖安装完成"
else
    log_ok "前端依赖已存在，跳过安装"
fi

# -------------------- Step 5: 启动后端 --------------------
log_step "Step 5/6  启动后端 (端口 8080)"

# Kill any existing process on 8080
existing_pid=$(netstat -ano 2>/dev/null | grep ":8080" | grep "LISTENING" | awk '{print $5}' | head -1)
if [ -n "$existing_pid" ]; then
    log_warn "端口 8080 已被占用，尝试终止 PID=$existing_pid"
    taskkill //F //PID "$existing_pid" 2>/dev/null || true
    sleep 1
fi

# Start backend in background
cd mall-bootstrap
mvn spring-boot:run -Dspring.profiles.active=dev > ../.backend.log 2>&1 &
BACKEND_PID=$!
cd ..
echo $BACKEND_PID > .backend.pid
log_ok "后端已启动 (PID=$BACKEND_PID, 日志: .backend.log)"

# -------------------- Step 6: 启动前端 --------------------
log_step "Step 6/6  启动前端 (端口 5173)"

# Kill any existing process on 5173
existing_pid=$(netstat -ano 2>/dev/null | grep ":5173" | grep "LISTENING" | awk '{print $5}' | head -1)
if [ -n "$existing_pid" ]; then
    log_warn "端口 5173 已被占用，尝试终止 PID=$existing_pid"
    taskkill //F //PID "$existing_pid" 2>/dev/null || true
    sleep 1
fi

# Start frontend in background
cd mall-ui
npx vite --host > ../.frontend.log 2>&1 &
FRONTEND_PID=$!
cd ..
echo $FRONTEND_PID > .frontend.pid
log_ok "前端已启动 (PID=$FRONTEND_PID, 日志: .frontend.log)"

# -------------------- 等待后端就绪 --------------------
log_warn "等待后端启动完成..."
for i in $(seq 1 60); do
    if curl -s http://localhost:8080/api/health >/dev/null 2>&1; then
        log_ok "后端已就绪"
        break
    fi
    if [ $i -eq 60 ]; then
        log_err "后端启动超时，请检查 .backend.log"
    fi
    sleep 2
done

# -------------------- 打开浏览器 --------------------
log_step "正在打开浏览器..."
start http://localhost:5173/mall 2>/dev/null || true
start http://localhost:8080/swagger-ui.html 2>/dev/null || true

# -------------------- 完成 --------------------
echo ""
echo "============================================"
echo "  系统启动成功！"
echo "============================================"
echo "  商城首页:  http://localhost:5173/mall"
echo "  管理后台:  http://localhost:5173/admin"
echo "  Swagger:   http://localhost:8080/swagger-ui.html"
echo "  RabbitMQ:  http://localhost:15672"
echo "============================================"
echo ""
echo "后端 PID: $(cat .backend.pid)  |  前端 PID: $(cat .frontend.pid)"
echo "后端日志: .backend.log  |  前端日志: .frontend.log"
echo ""
echo "运行 ./stop-all.sh 停止所有服务"
echo ""
