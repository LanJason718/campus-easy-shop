export type SystemMenu = {
  path: string
  component: string
  name: string
  meta: {
    title: string
    icon: string
    roles: string[]
  }
  children?: SystemMenu[]
}
