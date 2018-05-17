import Vue from 'vue'
import Router from 'vue-router'
import ShoppingList from '@/components/ShoppingList'
import ItemsList from '@/components/ItemsList'
import AddItemsList from '@/components/AddItemsList'
import AddItem from '@/components/AddItem'

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
      component: AddItemsList
    },
    {
      path: '/addItem/:listId',
      name: 'AddItem',
      component: AddItem,
      props: true
    }
  ]
})
