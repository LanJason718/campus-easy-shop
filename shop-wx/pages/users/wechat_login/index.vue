<template>
  <view>
    <j-nav-bar isBack isHome title="微信登陆" middleOpen></j-nav-bar>
    <view>logo</view>
    <view class="" @click="wxLogin">微信授权登陆</view>
    <u-popup v-model="popupShow" mode="bottom" safe-area-inset-bottom border-radius="16">
      <view class="pop-container">
        <view class="applyBox">
          <image></image>
          <text>淘乐汇 申请</text>
          <view class="icon" @click="popupShow = false">
            <u-icon name="close"></u-icon>
          </view>
        </view>
        <view class="text1">获取您的昵称、头像</view>
        <view class="text2">提供具有辨识度的用户中心界面</view>
        <view class="avatarBox">
          <text class="label">头像</text>
          <u-avatar :src="avatarUrl" size="80"></u-avatar>
          <button class="avatarBtn" open-type="chooseAvatar" @chooseavatar="chooseavatar">x</button>
        </view>
        <view class="nickNameBox">
          <text class="label">昵称</text>
          <input type="nickname" maxlength="20" placeholder="请输入昵称" v-model="nickname" @input="nickInput" />
        </view>
        <view class="saveBox">
          <u-button throttle-time="10000" :custom-style="{ border: 'none', backgroundColor: '#71d5a1' }" ripple :hair-line="false" :disabled="!saveStatus" @click="submit">
            保存
          </u-button>
        </view>
      </view>
    </u-popup>
  </view>
</template>
<script>
import Routine from '@/libs/routine'
import { register } from '@/api/public'
export default {
  data() {
    return {
      popupShow: false,
      avatarUrl: '',
      nickname: ''
    }
  },
  methods: {
    // 微信登陆
    async wxLogin() {
      let self = this
      uni.showLoading({
        title: '正在登录中'
      })
      try {
        const res = await Routine.wxLogin()
        uni.hideLoading()
        if (res.data.type === 'login') {
          uni.showToast({
            title: '登陆成功',
            icon: 'none'
          })
          setTimeout(() => {
            uni.navigateBack()
          }, 1500)
        } else if (res.data.type === 'register') {
          console.log(res.data.key, 'key')
          this.$Cache.set('key', res.data.key)
          self.popupShow = true
        }
      } catch (error) {
        uni.hideLoading()
        uni.showToast({
          title: error,
          icon: 'none'
        })
      }
    },
    //选择头像
    chooseavatar(res) {
      this.avatarUrl = res.detail.avatarUrl
    },
    nickInput(e) {
      const value = e.detail.value
    },
    submit() {
      console.log('upload')
      uni.uploadFile({
        url: 'http://127.0.0.1:8090/api/front/user/upload/image',
        filePath: this.avatarUrl,
        name: 'multipart',
        formData: {
          model: 'user',
          pid: 7
        },
        success: (res) => {
          const { data } = JSON.parse(res.data)
          console.log(data.url)
          register({ key: this.$Cache.getItem('key'), avatar: data.url, nickName: this.nickname }).then((res) => {
            console.log(res)
          })
        },
        fail: (err) => {
          console.error('上传失败', err)
        }
      })
    }
  },
  computed: {
    saveStatus() {
      return this.avatarUrl && this.nickname
    }
  }
}
</script>

<style lang="scss" scoped>
.pop-container {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  padding: 30rpx;

  .applyBox {
    width: 100%;
    display: flex;
    align-items: center;
    @include pb-lg;
    image {
      width: 50rpx;
      height: 50rpx;
    }
    text {
      font-size: $uni-font-size-sm;
      font-weight: bold;
    }
    .icon {
      margin-left: auto;
      color: #c0c4cc;
    }
  }
  .text1 {
    @include pb-sm;
    font-weight: bold;
  }
  .text2 {
    @include pb-lg;
    font-size: $uni-font-size-sm;
    color: $uni-text-color-grey;
  }
  .label {
    margin-right: 30rpx;
  }
  .avatarBox {
    height: 120rpx;
    position: relative;
    display: flex;
    align-items: center;
    padding: 20rpx 0;
    border-top: $border-sm;
    border-bottom: $border-sm;
    .avatarBtn {
      position: absolute;
      left: 0;
      right: 0;
      top: 0;
      bottom: 0;
      opacity: 0;
    }
  }
  .nickNameBox {
    height: 120rpx;
    display: flex;
    align-items: center;
    padding: 20rpx 0;
    border-bottom: $border-sm;
    input {
      flex: 1;
    }
  }
  .saveBox {
    width: 50%;
    margin: 50rpx auto;
  }
}
</style>
