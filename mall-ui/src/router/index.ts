import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/mall',
    component: () => import('@/layouts/MallLayout.vue'),
    children: [
      {
        path: '',
        redirect: '/mall/home'
      },
      {
        path: 'home',
        name: 'MallHome',
        component: () => import('@/pages/mall/Home.vue'),
        meta: { title: '首页' }
      },
      {
        path: 'products',
        name: 'Products',
        component: () => import('@/pages/mall/Products.vue'),
        meta: { title: '商品列表' }
      }
    ]
  },
  {
    path: '/admin',
    component: () => import('@/layouts/AdminLayout.vue'),
    children: [
      {
        path: '',
        name: 'AdminHome',
        component: () => import('@/pages/admin/Home.vue'),
        meta: { title: '管理后台' }
      },
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/pages/admin/Dashboard.vue'),
        meta: { title: '数据概览' }
      }
    ]
  },
  {
    path: '/404',
    name: 'NotFound',
    component: () => import('@/pages/NotFound.vue'),
    meta: { title: '404' }
  },
  {
    path: '/',
    redirect: '/mall'
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404'
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, _from, next) => {
  document.title = (to.meta.title as string) || 'CloudMall'
  next()
})

export default router
