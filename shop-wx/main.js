import App from './App'
import Vue from 'vue'
import './uni.promisify.adaptor'
import store from './store'
import Cache from './utils/cache'
import uView from './uni_modules/vk-uview-ui'
Vue.use(uView)
Vue.prototype.$Cache = Cache
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
  ...App,
  store,
  Cache
})
app.$mount()
