<template>
  <router-view />
</template>
<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useAppStore } from '@/stores/app'

// 创建 Pinia store 实例
const appStore = useAppStore()

// 定义屏幕宽度为响应式数据
const screenWidth = ref<number>(window.innerWidth)

// 更新屏幕宽度的函数
const updateWidth = () => {
  screenWidth.value = window.innerWidth
  appStore.setScreenLevel(screenWidth.value) // 根据当前宽度设置屏幕级别
}

// 组件挂载时监听窗口宽度变化
onMounted(() => {
  window.addEventListener('resize', updateWidth)
  updateWidth() // 初始化时调用一次，设置初始屏幕级别
})

// 组件销毁时移除监听器
onUnmounted(() => {
  window.removeEventListener('resize', updateWidth)
})
</script>
