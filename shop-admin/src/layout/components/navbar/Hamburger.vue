<template>
  <div @click="toggleClick" style="margin: 0 var(--el-menu-base-level-padding)">
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
  fun: {
    type: String,
    default: 'computer',
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

  color: #8f9bb3;
  cursor: pointer;
}
.hamburger.is-active {
  transform: rotateY(180deg);
}
</style>
