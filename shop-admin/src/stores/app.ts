import { computed, ref } from 'vue'
import { defineStore } from 'pinia'

export const useAppStore = defineStore('app', () => {
  const sidebarCollapse = ref(false)
  function toggleSidebar() {
    sidebarCollapse.value = !sidebarCollapse.value
  }
  const getCollapse = computed(() => sidebarCollapse.value)

  return { sidebarCollapse, toggleSidebar, getCollapse }
})
