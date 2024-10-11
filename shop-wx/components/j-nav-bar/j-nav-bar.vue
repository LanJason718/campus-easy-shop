<template>
  <view>
    <!-- 导航栏 -->
    <view class="nav-bar" :style="{ backgroundColor: bgColor }">
      <view
        class="content"
        :style="{
          height: contentHeight,
          width: middleOpen ? contentWidthFill : contentWidth,
          marginTop: `calc(${statusBarHeight} + ${contentVerticalSpacing})`,
          marginRight: contentSideSpacing,
          marginBottom: contentVerticalSpacing,
          marginLeft: contentSideSpacing
        }"
      >
        <!-- 返回与主页按钮 -->
        <view v-if="isBack && isHome" class="btnBox" :style="{ border: `2rpx solid ${fontColor}` }">
          <i class="iconfont icon-fanhui btn" :style="{ color: fontColor, borderRight: `2rpx solid ${fontColor}` }" @click="goBack"></i>
          <i class="iconfont icon-shouye btn" :style="{ color: fontColor }" @click="goHome"></i>
        </view>
        <!-- 仅返回按钮 -->
        <view v-else-if="isBack">
          <i class="iconfont icon-fanhui btn" :style="{ color: fontColor, borderRight: isHome ? `2rpx solid ${fontColor}` : '0' }" @click="goBack"></i>
        </view>
        <!-- 仅主页按钮 -->
        <view v-else-if="isHome">
          <i class="iconfont icon-shouye btn" :style="{ color: fontColor }" @click="goHome"></i>
        </view>
        <!-- 插槽 -->
        <slot v-else name="left"></slot>

        <!-- 标题 -->
        <view v-if="title" class="middle" :style="{ width: middleMaxWidth, height: contentHeight, color: fontColor }">
          <view class="title">{{ title }}</view>
        </view>
        <view v-else class="middle" :style="{ width: middleMaxWidth, height: contentHeight, color: fontColor }">
          <slot name="middle"></slot>
        </view>
      </view>
    </view>
    <!-- 占位 -->
    <view :style="{ height: `calc(${statusBarHeight} + ${contentHeight} + ${contentVerticalSpacing} * 2)` }"></view>
  </view>
</template>

<script>
export default {
  props: {
    bgColor: {
      type: String,
      default: '#fff'
    },
    fontColor: {
      type: String,
      default: '#303133'
    },
    isBack: {
      type: Boolean,
      default: false
    },
    isHome: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: ''
    },
    middleOpen: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      statusBarHeight: '0px',
      contentHeight: '0px',
      contentWidth: '0px',
      contentWidthFill: '0px',
      contentVerticalSpacing: '0px',
      contentSideSpacing: '0px',
      middleMaxWidth: '0px'
    }
  },
  methods: {
    goBack() {
      uni.navigateBack()
    },
    goHome() {
      this.$emit('navigate-home')
    }
  },
  mounted() {
    const menuButtonInfo = uni.getMenuButtonBoundingClientRect()
    const systemInfo = uni.getWindowInfo()

    this.statusBarHeight = `${systemInfo.statusBarHeight}px`
    this.contentHeight = `${menuButtonInfo.height}px`
    this.contentWidth = `${menuButtonInfo.left - 2 * (systemInfo.screenWidth - menuButtonInfo.right)}px`
    this.contentWidthFill = `${2 * menuButtonInfo.right - systemInfo.screenWidth}px`
    this.contentVerticalSpacing = `${menuButtonInfo.top - systemInfo.statusBarHeight}px`
    this.contentSideSpacing = `${systemInfo.screenWidth - menuButtonInfo.right}px`
    this.middleMaxWidth = `${2 * (systemInfo.screenWidth / 2 - menuButtonInfo.width - 2 * (systemInfo.screenWidth - menuButtonInfo.right))}px`
  }
}
</script>

<style scoped>
.nav-bar {
  width: 100%;
  position: fixed;
  z-index: 999;
}

.content {
  display: flex;
  align-items: center;
  position: relative;
  overflow: hidden;
}

.content .middle {
  box-sizing: border-box;
  overflow: hidden;
  white-space: nowrap;
  font-size: 32rpx;
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  justify-content: center;
  align-items: center;
}

.title {
  overflow: hidden;
  text-overflow: ellipsis;
}

.btnBox {
  border-radius: 50rpx;
  padding: 2rpx 6rpx 2rpx 4rpx;
}

.btn {
  display: inline-block;
  width: 50rpx;
  text-align: center;
}

@font-face {
  font-family: 'iconfont';
  src: url('data:font/ttf;charset=utf-8;base64,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')
    format('truetype');
  font-weight: normal;
  font-style: normal;
  font-display: swap;
}

.iconfont {
  font-family: 'iconfont' !important;
  font-size: 32rpx;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-shouye:before {
  content: '\e61d';
}

.icon-fanhui:before {
  content: '\e61e';
}
</style>
