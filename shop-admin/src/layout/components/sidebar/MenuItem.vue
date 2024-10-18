<template>
  <el-sub-menu
    v-if="menu.children && menu.children.length > 0"
    :index="menu.path"
  >
    <template #title>
      <el-icon>
        <component :is="menu.meta.icon"></component>
      </el-icon>
      <span>{{ menu.meta.title }}</span>
    </template>
    <!-- 递归调用生成下级菜单  递归：自己调用自己-->
    <menu-item
      v-for="child in menu.children"
      :menu="child"
      :level="level + 1"
    ></menu-item>
  </el-sub-menu>
  <el-menu-item v-else :index="menu.path" @click="itemClick">
    <el-icon v-if="level === 1">
      <component :is="menu.meta.icon"></component>
    </el-icon>
    <template #title>
      {{ menu.meta.title }}
    </template>
  </el-menu-item>
</template>

<script setup lang="ts">
import { type SystemMenu } from '@/model/system/systemMenu'
import { useAppStore } from '@/stores/app'
const appStore = useAppStore()
interface MenuProps {
  menu: SystemMenu
  level?: number
}
withDefaults(defineProps<MenuProps>(), {
  level: 1,
})

const itemClick = () => {
  if (appStore.screenLevel === 1) return
  appStore.toggleCollapse()
}
</script>
