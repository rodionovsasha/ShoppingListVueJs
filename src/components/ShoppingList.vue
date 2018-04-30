<template>
  <div class="col-md-6 offset-md-3" v-if="error">
    <div class="alert alert-danger" role="alert">
      <strong>Service unavailable.</strong> Please try back later.
    </div>
  </div>
  <div class="col-md-6 offset-md-3" v-else>
    <h1>Shopping list</h1>
    <div v-if="loading">Loading...</div>
    <ul class="list-unstyled" v-else>
      <li v-for="list in itemsLists" v-bind:key="list.id">
        <p>
          <a v-bind:href="'/itemsList?id=' + list.id" class="btn btn-outline-secondary">{{ list.name }}</a>
          <a v-bind:href="'/itemsList/edit?id=' + list.id" class="btn btn-warning btn-sm">Edit</a>
          <a v-bind:href="'/itemsList/delete?id=' + list.id" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this list?')">Delete</a>
        </p>
      </li>
    </ul>
    <a href="/itemsList/add" class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="right"
       title="Add new list" aria-hidden="true">
      <span class="oi oi-plus"></span>
    </a>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ShoppingList',
  data () {
    return {
      itemsLists: [],
      loading: true,
      error: false
    }
  },
  mounted () {
    axios
      .get('http://localhost:8000/v1/api')
      .then(response => {
        this.itemsLists = response.data
        this.loading = false
      })
      .catch(error => {
        console.log(error)
        this.error = true
        this.loading = false
      })
  }
}
</script>
