@echo off
:: ============================================
:: CloudMall 启动入口
:: 自动调用 Git Bash 运行 start-all.sh
:: 双击此文件即可
:: ============================================

set "PROJECT_DIR=%~dp0"

:: Find Git Bash
set "GIT_BASH="
if exist "C:\Program Files\Git\bin\bash.exe" set "GIT_BASH=C:\Program Files\Git\bin\bash.exe"
if exist "C:\Program Files (x86)\Git\bin\bash.exe" set "GIT_BASH=C:\Program Files (x86)\Git\bin\bash.exe"

if "%GIT_BASH%"=="" (
    echo [ERROR] 未找到 Git Bash，请先安装 Git for Windows
    echo         下载地址: https://git-scm.com/download/win
    pause
    exit /b 1
)

:: Launch Git Bash with the start script
start "CloudMall" "%GIT_BASH%" --login -c "cd \"%PROJECT_DIR%\" && bash start-all.sh; read -p 'Press Enter to close...'"
