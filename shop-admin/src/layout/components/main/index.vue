<template>
  <transition name="fade-transform" mode="out-in">
    <keep-alive><router-view /> </keep-alive>
  </transition>
</template>

<style scoped>
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.5s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
</style>
