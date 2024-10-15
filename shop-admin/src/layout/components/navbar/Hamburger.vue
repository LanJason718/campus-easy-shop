<template>
  <div @click="toggleClick">
    <el-icon :size="24" :class="{ 'is-active': isActive }" class="hamburger"
      ><Fold
    /></el-icon>
  </div>
</template>

<script lang="ts" setup>
const props = defineProps({
  isActive: {
    type: Boolean,
    default: false,
  },
})
const emit = defineEmits(['toggleClick'])
function toggleClick() {
  emit('toggleClick')
}
</script>

<style scoped>
.hamburger {
  transition: transform 0.5s;
}
.hamburger.is-active {
  transform: rotateY(180deg);
}
</style>
