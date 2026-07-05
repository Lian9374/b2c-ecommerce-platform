@echo off
chcp 65001 >nul
title CloudMall 一键启动

:: ============================================
:: CloudMall 企业级电商平台 — 一键启动脚本
:: 双击此文件即可启动整个系统
:: ============================================

set "PROJECT_DIR=%~dp0"
cd /d "%PROJECT_DIR%"

echo.
echo ╔══════════════════════════════════════════════╗
echo ║     CloudMall 企业级电商平台 — 系统启动       ║
echo ╚══════════════════════════════════════════════╝
echo.
echo [%time%] 开始启动...

:: ============================================
:: 1. 环境检查
:: ============================================
echo.
echo ┌──────────────────────────────────────────────┐
echo │  Step 1/6  环境检查                          │
echo └──────────────────────────────────────────────┘

where docker >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [✗] Docker 未安装或未加入 PATH，请先安装 Docker Desktop
    pause
    exit /b 1
)
echo [✓] Docker 已就绪

where java >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [✗] Java 未安装或未加入 PATH，请先安装 JDK 17+
    pause
    exit /b 1
)
echo [✓] Java 已就绪

where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [✗] Maven 未安装或未加入 PATH，请先安装 Maven 3.9+
    pause
    exit /b 1
)
echo [✓] Maven 已就绪

where node >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [✗] Node.js 未安装或未加入 PATH，请先安装 Node.js 18+
    pause
    exit /b 1
)
echo [✓] Node.js 已就绪

:: ============================================
:: 2. 启动 Docker 基础设施
:: ============================================
echo.
echo ┌──────────────────────────────────────────────┐
echo │  Step 2/6  启动 Docker 基础设施              │
echo └──────────────────────────────────────────────┘

docker info >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [!] Docker Desktop 未运行，正在尝试启动...
    start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
    echo [i] 等待 Docker Desktop 启动 (最多 60 秒)...
    set /a COUNT=0
    :wait_docker
        timeout /t 2 >nul
        set /a COUNT+=2
        docker info >nul 2>&1
        if %ERRORLEVEL% EQU 0 goto docker_ready
        if %COUNT% LSS 60 goto wait_docker
        echo [✗] Docker Desktop 启动超时，请手动启动后重试
        pause
        exit /b 1
    :docker_ready
)
echo [✓] Docker Desktop 运行中

echo [i] 正在启动 MySQL + Redis + RabbitMQ...
docker compose -f docker/docker-compose.yml up -d
if %ERRORLEVEL% NEQ 0 (
    echo [✗] Docker Compose 启动失败
    pause
    exit /b 1
)

echo [i] 等待服务健康检查...
:wait_mysql
    docker compose -f docker/docker-compose.yml ps | findstr "healthy" | findstr "mysql" >nul
    if %ERRORLEVEL% EQU 0 goto infra_ready
    timeout /t 2 >nul
    goto wait_mysql
:infra_ready
echo [✓] MySQL     — 就绪
echo [✓] Redis     — 就绪
echo [✓] RabbitMQ  — 就绪

:: ============================================
:: 3. 构建后端
:: ============================================
echo.
echo ┌──────────────────────────────────────────────┐
echo │  Step 3/6  构建后端 (Maven)                  │
echo └──────────────────────────────────────────────┘

echo [i] 正在编译项目 (首次较长，后续秒级)...
call mvn clean install -DskipTests -q
if %ERRORLEVEL% NEQ 0 (
    echo [✗] Maven 构建失败，请检查错误信息
    pause
    exit /b 1
)
echo [✓] 后端构建成功

:: ============================================
:: 4. 安装前端依赖（首次）
:: ============================================
echo.
echo ┌──────────────────────────────────────────────┐
echo │  Step 4/6  检查前端依赖                      │
echo └──────────────────────────────────────────────┘

if not exist "mall-ui\node_modules" (
    echo [i] 首次运行，正在安装前端依赖...
    cd mall-ui
    call npm install --silent
    cd ..
    if %ERRORLEVEL% NEQ 0 (
        echo [✗] npm install 失败
        pause
        exit /b 1
    )
    echo [✓] 前端依赖安装完成
) else (
    echo [✓] 前端依赖已存在，跳过安装
)

:: ============================================
:: 5. 启动后端服务
:: ============================================
echo.
echo ┌──────────────────────────────────────────────┐
echo │  Step 5/6  启动后端服务 (端口 8080)           │
echo └──────────────────────────────────────────────┘

start "CloudMall Backend" cmd /c "cd /d %PROJECT_DIR%mall-bootstrap && mvn spring-boot:run -Dspring.profiles.active=dev 2>&1"
echo [✓] 后端已在独立窗口启动

:: ============================================
:: 6. 启动前端服务
:: ============================================
echo.
echo ┌──────────────────────────────────────────────┐
echo │  Step 6/6  启动前端服务 (端口 5173)           │
echo └──────────────────────────────────────────────┘

start "CloudMall Frontend" cmd /c "cd /d %PROJECT_DIR%mall-ui && npm run dev 2>&1"
echo [✓] 前端已在独立窗口启动

:: ============================================
:: 等待后端就绪后打开浏览器
:: ============================================
echo.
echo [i] 等待后端启动完成...
:wait_backend
    curl -s http://localhost:8080/api/health >nul 2>&1
    if %ERRORLEVEL% EQU 0 goto backend_ready
    timeout /t 2 >nul
    goto wait_backend
:backend_ready
echo [✓] 后端已就绪

:: 打开浏览器
echo [i] 正在打开浏览器...
start http://localhost:5173/mall
start http://localhost:8080/swagger-ui.html

:: ============================================
:: 完成
:: ============================================
echo.
echo ╔══════════════════════════════════════════════╗
echo ║         系统启动成功！                       ║
echo ╠══════════════════════════════════════════════╣
echo ║  商城首页:  http://localhost:5173/mall      ║
echo ║  管理后台:  http://localhost:5173/admin     ║
echo ║  Swagger:   http://localhost:8080/swagger-ui ║
echo ║  RabbitMQ:  http://localhost:15672          ║
echo ╚══════════════════════════════════════════════╝
echo.
echo [提示] 不要关闭本窗口，关闭后端/前端窗口即可停止对应服务
echo [提示] 运行 stop-all.bat 可一键停止所有服务
echo.
pause
