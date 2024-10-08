<template>
  <!-- banner -->
  <j-nav-bar middleOpen>
    <template #left>
      <view class="logo">校易淘</view>
    </template>
    <!-- 输入框 -->
    <template #middle>
      <view class="search-container">
        <text class="iconfont icon-search"></text>
        <text class="search-title">搜索</text>
      </view>
    </template>
  </j-nav-bar>
  <view class="banner" v-if="bannerList.length">
    <swiper
      class="swiper-container"
      indicator-dots="true"
      autoplay
      :circular="true"
      :interval="4000"
      :duration="500"
      indicator-color="rgba(255,255,255,0.6)"
      indicator-active-color="#fff"
      easing-function="easeInOutCubic"
    >
      <swiper-item v-for="(item, index) in bannerList" :key="index">
        <image :src="item.pic" lazy-load></image>
      </swiper-item>
    </swiper>
  </view>
</template>

<script setup>
const bannerList = [
  {
    pic: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
    title: '昨夜星辰昨夜风，画楼西畔桂堂东'
  },
  {
    pic: 'https://cdn.uviewui.com/uview/swiper/2.jpg',
    title: '身无彩凤双飞翼，心有灵犀一点通'
  },
  {
    pic: 'https://cdn.uviewui.com/uview/swiper/3.jpg',
    title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
  }
]
</script>

<style lang="scss" scoped>
.logo {
  font-weight: bold;
}
.search-container {
  box-sizing: border-box;
  width: 100%;
  height: 80%;
  border: 1px solid #333;
  border-radius: 50px;
  display: flex;
  align-items: center;
  .icon-search {
    padding: 0 4rpx;
  }
  .search-title {
    font-size: 24rpx;
  }
}
.banner {
  width: 100%;
  .swiper-container {
    image {
      width: 100%;
    }
  }
}
</style>
