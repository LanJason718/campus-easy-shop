<template>
  <view @tap="login">登陆</view>
</template>

<script>
export default {
  methods: {
    login() {
      uni.navigateTo({
        url: '/pages/users/wechat_login/index'
      })
    }
  }
}
</script>

<style></style>
