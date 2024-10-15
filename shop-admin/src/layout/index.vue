<template>
  <el-container class="layout-container">
    <el-aside width="auto"><sidebar /></el-aside>
    <el-container>
      <el-header> <navbar></navbar></el-header>
      <el-main> <layout-main></layout-main></el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import Sidebar from './components/sidebar'
import Navbar from './components/navbar'
import LayoutMain from './components/main'
</script>

<style lang="scss" scoped>
@import '@/styles/variables.scss';

.layout-container {
  height: 100%;
  .el-aside {
    background-color: var(--sub-menu-background);
  }
  .el-header {
    background-color: var(--el-color-primary-light-7);
    height: $navbar-height;
  }
}
</style>
