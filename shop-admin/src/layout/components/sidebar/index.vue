<template>
  <el-scrollbar>
    <logo />
    <el-menu
      router
      class="el-menu-vertical"
      :default-active="active"
      :unique-opened="true"
      :collapse="isCollapse"
    >
      <menu-item v-for="menu in menuList" :menu="menu" :key="menu.path" />
    </el-menu>
  </el-scrollbar>
</template>

<script setup lang="ts">
import Logo from '../Logo.vue'
import MenuItem from './MenuItem.vue'
import { reactive, ref } from 'vue'
import { useAppStore } from '@/stores/app'
import { storeToRefs } from 'pinia'
import { useRoute } from 'vue-router'
const route = useRoute()
const appStore = useAppStore()
const { isCollapse } = storeToRefs(appStore)

const active = ref(route.fullPath)

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
  --el-transition-duration: 0.5s;
  transition-timing-function: ease;

  --el-menu-bg-color: var(--theme-color);
  --el-menu-text-color: var(--theme-text-color);

  :deep(.el-sub-menu__title),
  :deep(.el-menu-item) {
    font-weight: 600 !important;
    i {
      color: var(--menu-icon-color);
    }
  }

  :deep(.el-sub-menu__title:hover),
  :deep(.el-menu-item:hover) {
    color: var(--menu-hover-text-color);
    i {
      color: var(--menu-hover-text-color);
    }
  }

  :deep(.el-sub-menu.is-active) {
    .el-sub-menu__title {
      color: var(--menu-active-text-color);
      i {
        color: var(--menu-active-text-color);
      }
    }
    .el-menu-item.is-active {
      color: var(--menu-active-text-color);
      i {
        color: var(--menu-active-text-color);
      }
    }
  }
  :deep(.el-menu-item.is-active) {
    color: var(--menu-active-text-color);
    i {
      color: var(--menu-active-text-color);
    }
  }

  :deep(.el-menu-item) {
    border-bottom: 1px solid var(--menu-border-color);
    transition: none;
  }
  :deep(.el-sub-menu .el-menu-item) {
    border-bottom: none;
    border-top: 1px solid var(--menu-border-color);
  }
  :deep(.el-sub-menu) {
    border-bottom: 1px solid var(--menu-border-color);
  }

  :deep(.el-sub-menu .el-menu.el-menu--inline) {
    padding-left: 10px;
  }
}
.el-menu-vertical:not(.el-menu--collapse) {
  width: var(--menu-width);
}
:deep(
    .el-menu--vertical:not(.el-menu--collapse):not(.el-menu--popup-container)
      .el-sub-menu
      .el-menu-item
  ) {
  padding-left: calc(
    -10px + var(--el-menu-base-level-padding) + var(--el-menu-level) * var(--el-menu-level-padding)
  );
}
:deep(
    .el-menu--vertical:not(.el-menu--collapse):not(.el-menu--popup-container)
      .el-menu-item
  ) {
  padding-left: calc(
    var(--el-menu-base-level-padding) + var(--el-menu-level) *
      var(--el-menu-level-padding)
  );
}
</style>
<style lang="scss">
.el-popper {
  background-color: var(--theme-color) !important;
  color: var(--theme-text-color) !important;
  .el-menu-item {
    color: var(--theme-text-color);
    &.is-active {
      color: var(--menu-active-text-color);
    }
  }
}

.el-popper .el-menu-item:hover {
  color: var(--menu-hover-text-color);
}
</style>
