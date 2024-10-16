/**
 * 验证小数点后两位及多个小数
 * money 金额
 */
export function isMoney(money) {
  var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/
  if (reg.test(money)) {
    return true
  } else {
    return false
  }
}

/**
 * 验证手机号码
 * money 金额
 */
export function checkPhone(phone) {
  var reg = /^1(3|4|5|6|7|8|9)\d{9}$/
  if (reg.test(phone)) {
    return true
  } else {
    return false
  }
}
// 允许2-10个字符，中文、英文、数字、下划线、点，不允许以特殊字符开头或结尾
const nickNameRegex = /^[a-zA-Z0-9_\u4e00-\u9fa5]{2,10}$/
/**
 * 函数防抖 (只执行最后一次点击)
 * @param fn
 * @param delay
 * @returns {Function}
 * @constructor
 */
export const Debounce = (fn, t) => {
  const delay = t || 500
  let timer
  return function () {
    const args = arguments
    if (timer) {
      clearTimeout(timer)
    }
    timer = setTimeout(() => {
      timer = null
      fn.apply(this, args)
    }, delay)
  }
}
