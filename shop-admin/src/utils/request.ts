import axios, {
  type AxiosInstance,
  type AxiosError,
  type AxiosRequestConfig,
  type AxiosResponse,
} from 'axios'
import { ElMessage } from 'element-plus'
import Setting from '@/config/setting'

const config = {
  // 默认地址
  baseURL: Setting.apiBaseUrl,
  // 设置超时时间
  timeout: 60 * 1000,
}

class RequestHttp {
  // 定义成员变量并指定类型
  service: AxiosInstance
  public constructor(config: AxiosRequestConfig) {
    // 实例化axios
    this.service = axios.create(config)

    this.service.interceptors.request.use(
      (config: AxiosRequestConfig): any => {
        const token = localStorage.getItem('token') || ''
        config.headers = {
          ...config.headers,
          'Authori-zation': token,
        }
        return config
      },
      (error: AxiosError) => {
        console.log(error, 'e')

        ElMessage.error(error.message)
        return Promise.reject(error)
      },
    )

    this.service.interceptors.response.use(
      (response: AxiosResponse<ResultData>) => {
        console.log(response, 'res')
        const { data } = response
        switch (data.code) {
          case 200:
            return data.data
          case 401:
            ElMessage.error('无效的会话，或者登录已过期，请重新登录。')
            location.href = '/login'
            break
          case 403:
            ElMessage.error('没有权限访问。')
            break
          default:
            ElMessage.error(data.msg || '请求失败')
            return Promise.reject(data)
        }
      },
      (error: AxiosError) => {
        ElMessage.error(error.message)
        return Promise.reject(error)
      },
    )
  }

  // 常用方法封装
  get<T>(url: string, params?: Record<string, any>): Promise<ResultData<T>> {
    return this.service.get(url, { params })
  }

  post<T>(url: string, params?: Record<string, any>): Promise<ResultData<T>> {
    return this.service.post(url, params)
  }

  put<T>(url: string, params?: Record<string, any>): Promise<ResultData<T>> {
    return this.service.put(url, params)
  }

  delete<T>(url: string, params?: Record<string, any>): Promise<ResultData<T>> {
    return this.service.delete(url, { params })
  }
}

// 导出一个实例对象
export default new RequestHttp(config)
