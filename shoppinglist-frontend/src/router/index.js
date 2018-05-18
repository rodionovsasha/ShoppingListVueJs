import Vue from 'vue'
import Router from 'vue-router'
import ShoppingList from '@/components/ShoppingList'
import ItemsList from '@/components/ItemsList'
import AddItemsList from '@/components/AddItemsList'
import EditItemsList from '@/components/EditItemsList'
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
      path: '/itemsList/:listId',
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
      path: '/editItemsList/:listId',
      name: 'EditItemsList',
      component: EditItemsList,
      props: true
    },
    {
      path: '/addItem/:listId',
      name: 'AddItem',
      component: AddItem,
      props: true
    }
  ]
})
