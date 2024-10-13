import request from '@/utils/request.js'
import { toLogin, checkLogin } from '../libs/login'

/**
 * 获取登录授权login
 *
 */
export function getLogo() {
  // wechat/get_logo
  return request.get('wechat/getLogo', {}, { noAuth: true })
}

/**
 * 小程序用户登录
 * @param code String
 */
export function login(code) {
  return request.get('wechat/login?code=' + code, {}, { noAuth: true })
}

/**
 * 小程序注册
 * @param code String
 */
export function register(data) {
  return request.post('wechat/register', data, { noAuth: true })
}

/**
 * 分享
 * @returns {*}
 */
export function getShare() {
  return request.get('share', {}, { noAuth: true })
}

/**
 * 获取关注海报
 * @returns {*}
 */
export function follow() {
  return request.get('wechat/follow', {}, { noAuth: true })
}

/**
 * 获取图片base64
 * @retins {*}
 * */
export function imageBase64(image) {
  return request.post('qrcode/base64', image, { noAuth: true }, 1)
}

/**
 * 自动复制口令功能
 * @returns {*}
 */
export function copyWords() {
  return request.get('copy_words', {}, { noAuth: true })
}

/**
 * 首页 获取客服地址
 * @returns {*}
 */
export function kefuConfig() {
  return request.get('config', {}, { noAuth: true })
}

/**
 * 微信（公众号，小程序）绑定手机号
 * @param {Object} data
 */
export function getUserPhone(data) {
  return request.post('wechat/register/binding/phone', data, { noAuth: true })
}

/**
 * APP微信登录
 * @param {Object} data
 */
export function appAuth(data) {
  return request.post('wechat/authorize/app/login', data, { noAuth: true })
}

/**
 * 苹果登录
 * @param {Object} data
 */
export function appleLogin(data) {
  return request.post('ios/login', data, { noAuth: true })
}

/**
 * 苹果绑定手机号
 * @param {Object} data
 */
export function iosBinding(data) {
  return request.post('ios/binding/phone', data, { noAuth: true })
}
