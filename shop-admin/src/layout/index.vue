<template>
  <el-container class="layout-container">
    <el-header> <navbar></navbar></el-header>
    <el-container>
      <el-aside width="auto"><sidebar /></el-aside>
      <el-main> <layout-main></layout-main></el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import Sidebar from './components/sidebar'
import Navbar from './components/navbar'
import LayoutMain from './components/main'
</script>

<style lang="scss">
@import './meida.scss';
</style>
<style lang="scss" scoped>
.layout-container {
  height: 100%;
  .el-aside {
    background-color: var(--theme-color);
    box-shadow: 0 8px 16px var(--box-shadow-color);
    z-index: 22;
  }
  .el-header {
    background-color: var(--theme-color);
    height: var(--navbar-height);
    box-shadow: 0 8px 16px var(--box-shadow-color);
    z-index: 23;
    padding: 0;
  }
  .el-main {
    background-color: var(--theme-bg-color);
  }
}
</style>
