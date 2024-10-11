<template>
  <view>
    <!-- 状态栏 -->
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
    <!-- banner -->
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

    <!-- 金刚区 -->
    <!-- 吸顶分类 -->
    <!-- 瀑布流 -->
    <view class="flow-container">
      <u-waterfall v-model="flowList" ref="uWaterfall1">
        <template v-slot:left="{ leftList }">
          <view class="warter-left" v-for="(item, index) in leftList" :key="index">
            <u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
            <view class="title">
              {{ item.title }}
            </view>
            <view class="price">{{ item.price }}元</view>
            <!-- 头像 昵称 -->
          </view>
        </template>

        <template v-slot:right="{ rightList }">
          <view class="warter-right" v-for="(item, index) in rightList" :key="index">
            <u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
            <view class="title">
              {{ item.title }}
            </view>
            <view class="price">{{ item.price }}元</view>
            <!-- 头像 昵称 -->
          </view>
        </template>
      </u-waterfall>
      <u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="addRandomData"></u-loadmore>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      bannerList: [
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
      ],
      flowList: [
        {
          price: 75,
          title: '笔记本电脑',
          image: '/static/images/dev/11.jpg'
        },
        {
          price: 385,
          title: '手机',
          image: '/static/images/dev/22.jpeg'
        },
        {
          price: 784,
          title: '台式电脑',
          image: '/static/images/dev/33.jpeg'
        },
        {
          price: 7891,
          title: '电脑',
          image: '/static/images/dev/44.jpg'
        },
        {
          price: 2341,
          title: '自行车',
          image: '/static/images/dev/66.jpeg'
        },
        {
          price: 2342,
          title: '考研英语',
          image: '/static/images/dev/55.jpg'
        },
        {
          price: 2341,
          title: '苹果手机',
          image: '/static/images/dev/11.jpg'
        },
        {
          price: 2342,
          title: '球鞋',
          image: '/static/images/dev/22.jpeg'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.logo {
  font-size: 16px;
  font-weight: bold;
}
.search-container {
  box-sizing: border-box;
  width: 100%;
  height: 96%;
  border: 1px solid $uni-border-color;
  border-radius: 50px;
  display: flex;
  align-items: center;
  .icon-search {
    padding: 0 6rpx;
  }
  .search-title {
    font-size: 12px;
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
.flow-container {
  .warter-left {
    background: #eee;
    border-radius: 16rpx;
    margin: 10rpx;
  }
  .warter-right {
    background: #eee;
    border-radius: 16rpx;
    margin: 10rpx 10rpx 10rpx 0;
  }
}
</style>
