import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import SignUp from '@/components/SignUp'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: SignUp
    }
  ]
})
