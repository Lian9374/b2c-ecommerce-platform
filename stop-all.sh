#!/bin/bash
# ============================================
# CloudMall 一键停止脚本 (Git Bash)
# ============================================

PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$PROJECT_DIR"

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

log_ok()    { echo -e "${GREEN}[OK]${NC} $1"; }
log_warn()  { echo -e "${YELLOW}[WAIT]${NC} $1"; }

echo ""
echo "============================================"
echo "  CloudMall — 一键停止"
echo "============================================"
echo ""

# Stop backend
if [ -f .backend.pid ]; then
    BACKEND_PID=$(cat .backend.pid)
    if kill -0 "$BACKEND_PID" 2>/dev/null; then
        kill "$BACKEND_PID" 2>/dev/null && log_ok "后端已停止 (PID=$BACKEND_PID)"
    else
        log_warn "后端进程不存在 (PID=$BACKEND_PID)"
    fi
    rm -f .backend.pid
fi

# Kill any remaining process on port 8080
PORT_PID=$(netstat -ano 2>/dev/null | grep ":8080" | grep "LISTENING" | awk '{print $5}' | head -1)
if [ -n "$PORT_PID" ]; then
    taskkill //F //PID "$PORT_PID" 2>/dev/null && log_ok "端口 8080 已释放"
fi

# Stop frontend
if [ -f .frontend.pid ]; then
    FRONTEND_PID=$(cat .frontend.pid)
    if kill -0 "$FRONTEND_PID" 2>/dev/null; then
        kill "$FRONTEND_PID" 2>/dev/null && log_ok "前端已停止 (PID=$FRONTEND_PID)"
    else
        log_warn "前端进程不存在 (PID=$FRONTEND_PID)"
    fi
    rm -f .frontend.pid
fi

# Kill any remaining process on port 5173
PORT_PID=$(netstat -ano 2>/dev/null | grep ":5173" | grep "LISTENING" | awk '{print $5}' | head -1)
if [ -n "$PORT_PID" ]; then
    taskkill //F //PID "$PORT_PID" 2>/dev/null && log_ok "端口 5173 已释放"
fi

# Stop Docker containers
log_warn "停止 Docker 容器..."
docker compose -f docker/docker-compose.yml stop 2>/dev/null && log_ok "Docker 容器已停止"

echo ""
echo "所有服务已停止"
echo ""
