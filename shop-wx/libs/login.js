import store from '../store'
import Cache from '../utils/cache'
import { Debounce } from '@/utils/validate.js'

import { LOGIN_STATUS, USER_INFO, EXPIRES_TIME, STATE_R_KEY, BACK_URL } from './../config/cache'

function prePage() {
  let pages = getCurrentPages()
  let prePage = pages[pages.length - 1]
  return prePage.route
}

export const toLogin = Debounce(_toLogin, 800)

export function _toLogin() {
  store.commit('LOGOUT')
  let path = prePage()
  let login_back_url = Cache.get(BACK_URL)
  // 如果不是用户页面，则跳转到微信登录页面
  if (['/pages/user/index'].indexOf(login_back_url) == -1) {
    uni.navigateTo({
      url: '/pages/users/wechat_login/index'
    })
  }
}

export function checkLogin() {
  let token = Cache.get(LOGIN_STATUS)
  let expiresTime = Cache.get(EXPIRES_TIME)
  let newTime = Cache.time()

  // 检查登录状态
  if (expiresTime < newTime || !token) {
    Cache.clear(LOGIN_STATUS)
    Cache.clear(EXPIRES_TIME)
    Cache.clear(USER_INFO)
    Cache.clear(STATE_R_KEY)
    return false
  } else {
    store.commit('UPDATE_LOGIN', token)
    let userInfo = Cache.get(USER_INFO, true)
    if (userInfo) {
      store.commit('UPDATE_USERINFO', userInfo)
    }
    return true
  }
}
