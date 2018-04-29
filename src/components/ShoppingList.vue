<template>
  <ul class="list-unstyled">
    <list-item
      v-for="list in itemsLists"
      v-bind:list="list"
      v-bind:key="list.id">
    </list-item>
  </ul>
</template>

<script>
export default {
  name: 'ShoppingList',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>
