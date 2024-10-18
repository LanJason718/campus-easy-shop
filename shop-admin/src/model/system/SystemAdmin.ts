export type SystemAdmin = {
  id?: number
  username: string
  password: string
  nickname: string
  realName?: string
  phone: string
  gender: number
  role: number
  loginCount: number
  status: number
  createTime: Date
  updateTime: Date
}
