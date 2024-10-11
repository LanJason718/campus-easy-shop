let domain = 'http://127.0.0.1:8090'

module.exports = {
  // 请求域名 格式： https://您的域名
  HTTP_REQUEST_URL: domain,

  HTTP_ADMIN_URL: 'http://127.0.0.1:8089', //PC后台的API请求地址，上传图片用,影响h5上传头像

  HEADER: {
    'content-type': 'application/json'
  },
  HEADERPARAMS: {
    'content-type': 'application/x-www-form-urlencoded'
  },
  // 回话密钥名称 请勿修改此配置
  TOKENNAME: 'Authori-zation',
  // 缓存时间 0 永久
  EXPIRE: 0,
  //分页最多显示条数
  LIMIT: 10
}
