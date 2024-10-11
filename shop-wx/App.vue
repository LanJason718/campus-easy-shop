<script>
export default {
  onLaunch: function () {},
  onShow: function () {},
  onHide: function () {}
}
</script>

<style lang="scss">
@import './uni_modules/vk-uview-ui/index.scss';
@import './static/iconfont/iconfont.css';
page {
  font-size: $uni-font-size-base;
  color: $uni-text-color;
}

page ::v-deep ::-webkit-scrollbar {
  width: 0;
  height: 0;
  color: transparent;
}
</style>
