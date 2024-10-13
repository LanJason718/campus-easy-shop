export function px2rpx(val) {
  return Math.floor(val / (uni.upx2px(val) / val))
}
