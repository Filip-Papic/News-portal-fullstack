import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/all-users',
    name: 'AllUsers',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/Users.vue')
  },
  {
    path: '/add-user',
    name: 'AddUser',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/AddUser.vue')
  },
  {
    path: '/edit_user/:id',
    name: 'EditUser',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/EditUser.vue')
  },
  {
    path: '/news',
    name: 'News',
    component: () => import(/* webpackChunkName: "about" */ '../views/News.vue')
  },
  {
    path: '/single-news/:id',
    name: 'SingleNews',
    component: () => import(/* webpackChunkName: "about" */ '../views/SingleNews.vue')
  },
  {
    path: '/search',
    name: 'Search',
    component: () => import(/* webpackChunkName: "about" */ '../views/Search.vue')
  },
  {
    path: '/add-news',
    name: 'AddNews',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/AddNews.vue')
  },
  {
    path: '/edit-news/:id/:catID',
    name: 'EditNews',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/EditNews.vue')
  },
  {
    path: '/popular-news',
    name:  'PopularNews',
    component: () => import(/* webpackChunkName: "about" */ '../views/PopularNews.vue')
  },
  {
    path: '/news-by-tag/:id/:tag',
    name: 'NewsWithTag',
    component: () => import(/* webpackChunkName: "about" */ '../views/NewsWithTag.vue')
  },
  {
    path: '/categories',
    name: 'Categories',
    component: () => import(/* webpackChunkName: "about" */ '../views/Categories.vue')
  },
  {
    path: '/category/:id',
    name: 'CategoryNews',
    component: () => import(/* webpackChunkName: "about" */ '../views/CategoryNews.vue')
  },
  {
    path: '/add-category',
    name: 'AddCategory',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/AddCategory.vue')
  },
  {
    path: '/edit-category/:id',
    name: 'EditCategory',
    meta: {
      authRequired: true,
    },
    component: () => import(/* webpackChunkName: "about" */ '../views/EditCategory.vue')
  },
  
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue'),
    meta: {
      reload: true,
    }
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.authRequired) {
    const jwt = localStorage.getItem('jwt');

    if (!jwt) {
      next({name: 'Login'});
      return;
    }

    const payload = JSON.parse(atob(jwt.split('.')[1]));
    const expDate = new Date(payload.exp * 1000);
    if (expDate < new Date()) {
      next({name: 'Login'});
      return;
    } 
  }

  next();
});

export default router
