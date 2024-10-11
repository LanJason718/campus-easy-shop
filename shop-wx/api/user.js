import request from '@/utils/request.js'

export function wxLogin(data) {
  return request.get('/wechat/authorize/program/login', data, { noAuth: true })
}
