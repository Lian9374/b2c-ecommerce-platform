<template>
  <div class="not-found">
    <h1 class="code">404</h1>
    <p class="message">页面未找到</p>
    <p class="desc">您访问的页面不存在或已被移除</p>
    <el-button type="primary" size="large" @click="$router.push('/mall')">
      返回首页
    </el-button>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
.not-found {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  text-align: center;
}
.code {
  font-size: 120px;
  font-weight: 700;
  color: #409eff;
  margin: 0;
  line-height: 1;
}
.message {
  font-size: 24px;
  color: #303133;
  margin: 20px 0 8px;
}
.desc {
  font-size: 14px;
  color: #909399;
  margin-bottom: 32px;
}
</style>
