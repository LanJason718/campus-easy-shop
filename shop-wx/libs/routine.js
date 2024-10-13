import store from '../store'
import { checkLogin } from './login'
import { login } from '@/api/public'
import Cache from '@/utils/cache'
import { STATE_R_KEY, USER_INFO, EXPIRES_TIME, LOGIN_STATUS } from '@/config/cache'
class Routine {
  constructor() {
    this.scopeUserInfo = 'scope.userInfo'
  }

  /**
   * 获取用户信息
   */
  authorize() {
    let that = this
    return new Promise((resolve, reject) => {
      if (checkLogin())
        return resolve({
          userInfo: Cache.get(USER_INFO, true),
          islogin: true
        })
      uni.authorize({
        scope: that.scopeUserInfo,
        success() {
          resolve({ islogin: false })
        },
        fail(res) {
          reject(res)
        }
      })
    })
  }

  /**
   * 获取code
   */
  async getCode() {
    let provider = await this.getProvider()
    return new Promise((resolve, reject) => {
      uni.login({
        provider: provider,
        success(res) {
          if (res.code) Cache.set(STATE_R_KEY, res.code, 10800)
          return resolve(res.code)
        },
        fail() {
          return reject(null)
        }
      })
    })
  }

  /**
   * 获取服务供应商
   */
  getProvider() {
    return new Promise((resolve, reject) => {
      uni.getProvider({
        service: 'oauth',
        success(res) {
          resolve(res.provider)
        },
        fail() {
          resolve(false)
        }
      })
    })
  }

  /**
   * 小程序登录
   */
  async wxLogin() {
    const code = await this.getCode()
    return new Promise((resolve, reject) => {
      login(code)
        .then((res) => {
          console.log(res)
          if (res.data.type === 'login') {
            store.commit('LOGIN', {
              token: res.data.token
            })
            store.commit('SETUID', res.data.uid)
          }
          return resolve(res)
        })
        .catch((err) => {
          return reject(err)
        })
    })
  }
}

export default new Routine()
