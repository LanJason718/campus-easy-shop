<template>
  <div class="navbar">
    <div class="left">
      <!-- 汉堡包 -->
      <hamburger
        style="padding-right: 20px"
        :isActive="sidebarCollapse"
        @toggleClick="toggleClick"
      />
      <!-- 面包屑 -->
      <breadcrumb />
    </div>
    <div class="right">
      <!-- 切换主题 -->
      <div style="margin: 0 20px">
        <toggle-theme />
      </div>
      <!-- 个人 -->
      <el-dropdown>
        <span class="el-dropdown-link">
          Dropdown List
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <router-link to="/">
              <el-dropdown-item>主页</el-dropdown-item>
            </router-link>

            <el-dropdown-item>个人中心</el-dropdown-item>
            <el-dropdown-item>退出登陆</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>
<script setup lang="ts">
import Hamburger from './Hamburger'
import ToggleTheme from './ToggleTheme'
import Breadcrumb from './Breadcrumb'
import { useAppStore } from '@/stores/app'
import { storeToRefs } from 'pinia'
const appStore = useAppStore()
const { sidebarCollapse } = storeToRefs(appStore)
//汉堡包
const toggleClick = () => {
  appStore.toggleSidebar()
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  box-sizing: border-box;
  .left {
    display: flex;
    align-items: center;
  }
  .right {
    display: flex;
    align-items: center;
  }
}
</style>
