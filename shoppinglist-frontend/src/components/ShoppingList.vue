<template>
  <div class="col-md-6 offset-md-3">
    <div v-if="error">
      <div class="alert alert-danger" role="alert">
        <strong>{{ message }}</strong>
      </div>
    </div>
    <h1>Shopping list</h1>
    <div v-if="loading">Loading...</div>
    <ul class="list-unstyled" v-else>
      <li v-for="(list, index) in itemsLists" v-bind:key="list.id">
        <p>
          <router-link v-bind:to="{ path: '/itemsList/' + list.id }" class="btn btn-outline-secondary">{{ list.name }}</router-link>
          <a v-bind:href="'/#/itemsList/edit?id=' + list.id" class="btn btn-warning btn-sm">Edit</a>
          <a href="#" class="btn btn-danger btn-sm" @click.prevent="confirmDelete(list.id, index)">Delete</a>
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
import {AXIOS} from './http-common'

export default {
  name: 'ShoppingList',
  data () {
    return {
      itemsLists: [],
      loading: true,
      error: false,
      message: ''
    }
  },
  mounted () {
    AXIOS.get('/')
      .then(response => {
        this.itemsLists = response.data
        this.loading = false
        this.error = false
      })
      .catch(error => {
        console.log(error)
        this.error = true
        this.message = error.toString()
        this.loading = false
      })
  },
  methods: {
    confirmDelete: function (id, index) {
      this.$dialog.confirm('Are you sure you want to delete this list?')
        .then(() => {
          console.log('Delete clicked')
          AXIOS.delete('/itemsList/' + id)
            .then(() => {
              // remove list from array
              this.itemsLists.splice(index, 1)
              this.error = false
            })
            .catch(error => {
              console.log(error)
              this.error = true
              this.message = error.toString()
            })
        })
        .catch(function () {
          console.log('Cancel delete clicked')
        })
    }
  }
}
</script>
