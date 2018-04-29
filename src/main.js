// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
// import ShoppingList from './components/ShoppingList'
import router from './router'

Vue.config.productionTip = false

Vue.component('listItem', {
  props: ['list'],
  template: '<li>{{ list.name }}</li>'
  // template: '<li>' +
  // '<p>' +
  // '<a href="/itemsList(id=list.id)" text="list.name" class="btn btn-outline-secondary">{{ list.name }}</a>' +
  // '<a href="/itemsList/edit(id=list.id)" class="btn btn-warning btn-sm">Edit</a>' +
  // '<a href="/itemsList/delete(id=list.id)" class="btn btn-danger btn-sm" onclick="return confirm(\'Are you sure you want to delete this list?\')">Delete</a>' +
  // '</p>' +
  // '</li>'
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>',
  data: {
    itemsLists: [
      {id: 1, name: 'Vegetables'},
      {id: 2, name: 'Cheese'},
      {id: 3, name: 'Whatever else humans are supposed to eat'}
    ]
  }
})
