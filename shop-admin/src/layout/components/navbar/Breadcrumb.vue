<template>
  <el-breadcrumb separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item
        v-for="item in tabs"
        :key="item.path"
        @click="handleClick(item.path)"
        >{{ item.meta.title }}</el-breadcrumb-item
      >
    </transition-group>
  </el-breadcrumb>
</template>

<script setup lang="ts">
import router from '@/router'
import { onMounted, ref, watch, type Ref } from 'vue'
import { useRoute, type RouteLocationMatched } from 'vue-router'
//获取当前路由
const route = useRoute()
//定义面包屑导航数据
const tabs: Ref<RouteLocationMatched[]> = ref([])
//获取面包数据
const getBreadcrumb = () => {
  let matched = route.matched.filter(item => item.meta && item.meta.title)

  if (matched[0].path != '/dashboard') {
    //构造首页
    matched = [
      {
        path: '/dashboard',
        meta: {
          title: '首页',
        },
      } as any,
    ].concat(matched)
  }
  tabs.value = matched
}
watch(
  () => route.path,
  () => getBreadcrumb(),
)
onMounted(() => {
  getBreadcrumb()
})
const handleClick = (path: string) => {
  router.push(path)
}
</script>

<style lang="scss" scoped>
.breadcrumb-enter-active {
  transition: all 0.5s;
}
.breadcrumb-leave-active {
  transition: all 0.5s;
  opacity: 0;
  transform: translateX(20px);
}
.breadcrumb-enter {
  opacity: 0;
  transform: translateX(20px);
}

.breadcrumb-move {
  transition: all 0.5s;
}

.breadcrumb-leave-active {
  position: absolute;
}
</style>
