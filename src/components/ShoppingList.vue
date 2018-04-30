<template>
  <div class="col-md-6 offset-md-3">
    <h1>Shopping list</h1>
    <ul class="list-unstyled" v-if="itemsLists.length">
      <li v-for="list in itemsLists" v-bind:key="list.id">
        <p>
          <a href="/itemsList(id=list.id)" class="btn btn-outline-secondary">{{ list.name }}</a>
          <a href="/itemsList/edit(id=list.id)" class="btn btn-warning btn-sm">Edit</a>
          <a href="/itemsList/delete(id=list.id)" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this list?')">Delete</a>
        </p>
      </li>
    </ul>
    <p v-else>
      There are no available lists.
    </p>
    <a href="/itemsList/add" class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="right"
       title="Add new list" aria-hidden="true">
      <span class="oi oi-plus"></span>
    </a>
  </div>
</template>

<script>
export default {
  name: 'ShoppingList',
  data () {
    return {
      itemsLists: [
        {id: 1, name: 'Fruits'},
        {id: 2, name: 'Vegetables'},
        {id: 3, name: 'Dairy'}
      ]
    }
  }
}
</script>
