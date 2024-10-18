import { computed, ref } from 'vue'
import { defineStore } from 'pinia'

export const useAppStore = defineStore('app', () => {
  const isCollapse = ref(false)

  function toggleCollapse() {
    isCollapse.value = !isCollapse.value
  }

  const getCollapse = computed(() => isCollapse.value)

  const screenLevel = ref<number>(1) // 使用字符串存储屏幕级别

  function setScreenLevel(width: number) {
    // 根据屏幕宽度设置不同的屏幕级别，使用字符串
    if (width < 992) {
      screenLevel.value = 2 // 手机
      isCollapse.value = true
    } else {
      screenLevel.value = 1 // 笔记本电脑
    }
  }
  return {
    isCollapse,
    toggleCollapse,
    getCollapse,
    screenLevel,
    setScreenLevel,
  }
})
