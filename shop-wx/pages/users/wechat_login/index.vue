<template>
  <view>
    <j-nav-bar isBack title="微信登陆" middleOpen></j-nav-bar>
    <view>logo</view>
    <view class="" @click="wxLogin">微信授权登陆</view>
  </view>
</template>
<script>
import Routine from '@/libs/routine'
export default {
  data() {
    return {}
  },
  methods: {
    async wxLogin() {
      let self = this
      // uni.showLoading({
      //   title: '正在登录中'
      // })
      await Routine.wxLogin()
    },
    getUserProfile() {
      let self = this
      uni.showLoading({
        title: '正在登录中'
      })
      Routine.getUserProfile()
        .then((res) => {
          Routine.getCode()
            .then((code) => {
              self.getWxUser(code, res)
            })
            .catch((res) => {
              uni.hideLoading()
            })
        })
        .catch((res) => {
          uni.hideLoading()
        })
    },

    getWxUser(code, res) {
      let self = this
      let userInfo = res.userInfo
      userInfo.code = code
      userInfo.avatar = userInfo.userInfo.avatarUrl
      userInfo.city = userInfo.userInfo.city
      userInfo.country = userInfo.userInfo.country
      userInfo.nickName = userInfo.userInfo.nickName
      userInfo.province = userInfo.userInfo.province
      userInfo.sex = userInfo.userInfo.gender
      userInfo.type = 'routine'
      Routine.authUserInfo(userInfo.code, userInfo)
        .then((res) => {
          self.authKey = res.data.key
          if (res.data.type === 'register') {
            uni.hideLoading()
            self.isPhoneBox = true
          }
          if (res.data.type === 'login') {
            uni.hideLoading()
            self.$store.commit('LOGIN', {
              token: res.data.token
            })
            self.$store.commit('SETUID', res.data.uid)
            self.getUserInfo()
            self.$util.Tips(
              {
                title: res,
                icon: 'success'
              },
              {
                tab: 3
              }
            )
          }
        })
        .catch((res) => {
          uni.hideLoading()
          uni.showToast({
            title: res,
            icon: 'none',
            duration: 2000
          })
        })
    }
  }
}
</script>
