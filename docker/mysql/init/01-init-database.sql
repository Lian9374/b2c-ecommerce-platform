-- Docker MySQL 初始化脚本
-- 确保数据库使用 utf8mb4 编码

ALTER DATABASE enterprise_mall CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建测试数据库
CREATE DATABASE IF NOT EXISTS enterprise_mall_test
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
