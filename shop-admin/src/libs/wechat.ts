/**
 * 是否是移动端
 */
export function isPhone() {
  return /(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent)
}
