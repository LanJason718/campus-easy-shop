const VITE_BASE_URL: string =
  import.meta.env.VITE_BASE_URL || `${window.location.origin}`

const Setting = {
  httpUrl: VITE_BASE_URL,
  apiBaseUrl: VITE_BASE_URL + '/api/admin',
}

export default Setting
