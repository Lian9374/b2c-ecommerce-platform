<template>
  <div class="mall-home">
    <div class="hero-section">
      <h1 class="hero-title">CloudMall 企业级电商平台</h1>
      <p class="hero-subtitle">高性能 · 高可用 · 可扩展的新一代电商解决方案</p>
      <div class="search-bar">
        <el-input
          v-model="keyword"
          placeholder="搜索你想要的商品..."
          size="large"
          :prefix-icon="Search"
          clearable
          @keyup.enter="handleSearch"
        />
        <el-button type="primary" size="large" @click="handleSearch">搜索</el-button>
      </div>
    </div>

    <div class="feature-section">
      <el-row :gutter="24">
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <el-icon :size="40" color="#409eff"><Goods /></el-icon>
            <h3>百万商品</h3>
            <p>覆盖全品类，精选优质商家</p>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <el-icon :size="40" color="#67c23a"><Van /></el-icon>
            <h3>极速物流</h3>
            <p>全国次日达，送货上门</p>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover" class="feature-card">
            <el-icon :size="40" color="#e6a23c"><Medal /></el-icon>
            <h3>品质保障</h3>
            <p>正品保证，七天无理由退换</p>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Search } from '@element-plus/icons-vue'

const keyword = ref('')

function handleSearch() {
  if (keyword.value.trim()) {
    // 后续对接商品搜索
    console.log('搜索:', keyword.value)
  }
}
</script>

<style scoped>
.hero-section {
  text-align: center;
  padding: 60px 0;
  background: linear-gradient(135deg, #409eff 0%, #337ecc 100%);
  border-radius: 12px;
  color: #fff;
  margin-bottom: 40px;
}
.hero-title {
  font-size: 36px;
  font-weight: 700;
  margin-bottom: 12px;
}
.hero-subtitle {
  font-size: 16px;
  opacity: 0.9;
  margin-bottom: 32px;
}
.search-bar {
  display: flex;
  max-width: 560px;
  margin: 0 auto;
  gap: 12px;
}
.search-bar .el-input {
  flex: 1;
}
.feature-section {
  max-width: 960px;
  margin: 0 auto;
}
.feature-card {
  text-align: center;
  padding: 32px 0;
}
.feature-card h3 {
  margin: 16px 0 8px;
  font-size: 18px;
}
.feature-card p {
  color: #909399;
  font-size: 14px;
}
</style>
