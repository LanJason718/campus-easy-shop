import request from '@/utils/request'
import { type SystemAdmin } from '@/model/system/SystemAdmin'

export const adminAdd = (params: SystemAdmin) => {
  return request.post('/system/admin/add', params)
}
