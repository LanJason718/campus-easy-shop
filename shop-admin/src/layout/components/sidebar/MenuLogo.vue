<template>
  <div class="logoBox" @click="goHome">
    <Transition name="logo">
      <div class="logo" v-if="isCollapse">淘</div>
      <div class="logo" v-else>校易淘后台管理</div>
    </Transition>
  </div>
</template>

<script setup lang="ts">
import router from '@/router'
import { useAppStore } from '@/stores/app'
import { computed, ref, watch } from 'vue'
const appStore = useAppStore()
const isCollapse = ref(false)

watch(
  () => appStore.getCollapse,
  newVal => {
    if (!newVal) {
      setTimeout(() => {
        isCollapse.value = newVal
      }, 200)
    } else {
      isCollapse.value = newVal
    }
  },
)
const goHome = () => {
  router.push('/')
}
</script>

<style lang="scss" scoped>
.logo-enter-active {
  transition: opacity 1.5s;
}
.logo-enter-from {
  opacity: 0;
}
.logoBox {
  width: 100%;
  height: 50px;

  .logo {
    height: 50px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    overflow: hidden;
    white-space: nowrap;
  }
}
</style>
