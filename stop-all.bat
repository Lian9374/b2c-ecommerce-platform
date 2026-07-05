@echo off
chcp 65001 >nul
title CloudMall 一键停止

echo.
echo ╔══════════════════════════════════════════════╗
echo ║     CloudMall 企业级电商平台 — 系统停止       ║
echo ╚══════════════════════════════════════════════╝
echo.

:: 停止后端 (通过端口查找并关闭)
echo [1/3] 停止后端服务 (端口 8080)...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do (
    taskkill /F /PID %%a >nul 2>&1
    echo       已终止进程 PID=%%a
)

:: 停止前端 (通过端口查找并关闭)
echo [2/3] 停止前端服务 (端口 5173)...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :5173 ^| findstr LISTENING') do (
    taskkill /F /PID %%a >nul 2>&1
    echo       已终止进程 PID=%%a
)

:: 停止 Docker 容器
echo [3/3] 停止 Docker 容器...
cd /d "%~dp0"
docker compose -f docker/docker-compose.yml stop
echo       容器已停止

echo.
echo [✓] 所有服务已停止
echo.
echo [提示] 如需完全清理容器和数据卷，运行:
echo       docker compose -f docker/docker-compose.yml down -v
echo.
pause
