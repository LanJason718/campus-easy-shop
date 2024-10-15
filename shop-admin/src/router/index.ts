import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/index.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        component: () => import('@/views/dashboard.vue'),
        name: 'dashboard',
        meta: {
          title: '首页',
          icon: '#icondashboard',
        },
      },
    ],
  },
  {
    path: '/system',
    component: Layout,
    name: 'system',
    meta: {
      title: '系统管理',
      icon: 'Setting',
      roles: ['sys:manage'],
    },
    children: [
      {
        path: '/adminUser',
        component: () => import('@/views/system/AdminUser.vue'),
        name: 'adminUser',
        meta: {
          title: '管理员管理',
          icon: 'UserFilled',
          roles: ['sys:adminUser'],
        },
      },
      {
        path: '/userList',
        component: () => import('@/views/system/UserList.vue'),
        name: 'userList',
        meta: {
          title: '用户管理',
          icon: 'Wallet',
          roles: ['sys:userList'],
        },
      },
      {
        path: '/menuList',
        component: () => import('@/views/system/MenuList.vue'),
        name: 'menuList',
        meta: {
          title: '菜单管理',
          icon: 'Menu',
          roles: ['sys:menu'],
        },
      },
    ],
  },
]
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
