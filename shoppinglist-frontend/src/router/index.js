import Vue from 'vue'
import Router from 'vue-router'
import ShoppingList from '@/components/ShoppingList'
import ItemsList from '@/components/ItemsList'
import AddItemsList from '@/components/AddItemsList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'ShoppingList',
      component: ShoppingList
    },
    {
      path: '/itemsList/:id',
      name: 'ItemsList',
      component: ItemsList,
      props: true
    },
    {
      path: '/addItemsList',
      name: 'AddItemsList',
      component: AddItemsList,
      props: true
    }
  ]
})
