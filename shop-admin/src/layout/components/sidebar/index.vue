<template>
  <el-scrollbar>
    <menu-logo></menu-logo>
    <el-menu
      router
      class="el-menu-vertical"
      default-active="/dashboard"
      background-color="var(--sub-menu-background)"
      text-color="var(--menu-color)"
      :unique-opened="true"
      :collapse="sidebarCollapse"
    >
      <menu-item v-for="menu in menuList" :menu="menu" :key="menu.path" />
    </el-menu>
  </el-scrollbar>
</template>

<script setup lang="ts">
import MenuItem from './MenuItem.vue'
import MenuLogo from './MenuLogo.vue'
import { reactive, ref } from 'vue'
import { useAppStore } from '@/stores/app'
import { storeToRefs } from 'pinia'
const appStore = useAppStore()
const { sidebarCollapse } = storeToRefs(appStore)
//菜单数据
let menuList = reactive([
  {
    path: '/dashboard',
    component: 'Layout',
    name: 'dashboard',
    meta: {
      title: '首页',
      icon: 'HomeFilled',
      roles: ['sys:dashboard'],
    },
  },
  {
    path: '/system',
    component: 'Layout',
    name: 'system',
    meta: {
      title: '系统管理',
      icon: 'Setting',
      roles: ['sys:manage'],
    },
    children: [
      {
        path: '/adminUser',
        component: '/system/AdminUser',
        name: 'adminUser',
        meta: {
          title: '管理员管理',
          icon: 'UserFilled',
          roles: ['sys:adminUser'],
        },
      },
      {
        path: '/userList',
        component: '/system/UserList',
        name: 'userList',
        meta: {
          title: '用户管理',
          icon: 'Wallet',
          roles: ['sys:userList'],
        },
      },
      {
        path: '/menuList',
        component: '/system/MenuList',
        name: 'menuList',
        meta: {
          title: '菜单管理',
          icon: 'Menu',
          roles: ['sys:menu'],
        },
      },
    ],
  },
  {
    path: '/goodsRoot',
    component: 'Layout',
    name: 'goodsRoot',
    meta: {
      title: '商品管理',
      icon: 'Setting',
      roles: ['sys:goodsRoot'],
    },
    children: [
      {
        path: '/goodsType',
        component: '/goods/GoodsType',
        name: 'goodsType',
        meta: {
          title: '商品分类',
          icon: 'UserFilled',
          roles: ['sys:goodsType'],
        },
      },
      {
        path: '/unusedList',
        component: '/goods/UnusedList',
        name: 'unusedList',
        meta: {
          title: '闲置商品',
          icon: 'Wallet',
          roles: ['sys:unusedList'],
        },
      },
      {
        path: '/buyList',
        component: '/goods/BuyList',
        name: 'buyList',
        meta: {
          title: '求购商品',
          icon: 'Wallet',
          roles: ['sys:buyList'],
        },
      },
    ],
  },
  {
    path: '/order',
    component: 'Layout',
    name: 'order',
    meta: {
      title: '订单管理',
      icon: 'Setting',
      roles: ['sys:order'],
    },
    children: [
      {
        path: '/unusedOrder',
        component: '/order/UnusedOrder',
        name: 'unusedOrder',
        meta: {
          title: '闲置订单',
          icon: 'UserFilled',
          roles: ['sys:unusedOrder'],
        },
      },
      {
        path: '/buyOrder',
        component: '/order/BuyOrder',
        name: 'buyOrder',
        meta: {
          title: '求购订单',
          icon: 'Wallet',
          roles: ['sys:buyOrder'],
        },
      },
    ],
  },
  {
    path: '/comment',
    component: 'Layout',
    name: 'comment',
    meta: {
      title: '评论管理',
      icon: 'Setting',
      roles: ['sys:comment'],
    },
    children: [
      {
        path: '/commentList',
        component: '/comment/CommentList',
        name: 'commentList',
        meta: {
          title: '评论列表',
          icon: 'UserFilled',
          roles: ['sys:commentList'],
        },
      },
    ],
  },
])
</script>

<style lang="scss" scoped>
.el-menu {
  border-right: 0;
  --el-transition-duration: 0.5s;
  transition-timing-function: ease;
}
.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
}
</style>
