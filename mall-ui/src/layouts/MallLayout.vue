<template>
  <el-container class="mall-layout">
    <el-header class="mall-header">
      <div class="header-left">
        <router-link to="/mall" class="logo">CloudMall</router-link>
      </div>
      <div class="header-nav">
        <router-link to="/mall/home">首页</router-link>
        <router-link to="/mall/products">商品</router-link>
        <router-link to="/mall/cart">购物车</router-link>
        <router-link to="/mall/login">登录</router-link>
      </div>
    </el-header>
    <el-main class="mall-main">
      <router-view />
    </el-main>
    <el-footer class="mall-footer">
      <p>&copy; 2026 CloudMall Enterprise. All rights reserved.</p>
    </el-footer>
  </el-container>
</template>

<script setup lang="ts">
</script>

<style scoped>
.mall-layout {
  min-height: 100vh;
}
.mall-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: #fff;
  border-bottom: 1px solid #e4e7ed;
  padding: 0 40px;
}
.header-left .logo {
  font-size: 22px;
  font-weight: bold;
  color: #409eff;
}
.header-nav a {
  margin-left: 24px;
  font-size: 14px;
  color: #606266;
}
.header-nav a:hover,
.header-nav a.router-link-active {
  color: #409eff;
}
.mall-main {
  min-height: calc(100vh - 120px);
  background: #f5f7fa;
  padding: 20px 40px;
}
.mall-footer {
  display: flex;
  align-items: center;
  justify-content: center;
  background: #fff;
  border-top: 1px solid #e4e7ed;
  color: #909399;
  font-size: 13px;
}
</style>
