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
      error: false
    }
  },
  mounted () {
    AXIOS.get('/')
      .then(response => {
        this.itemsLists = response.data
        this.loading = false
      })
      .catch(error => {
        console.log(error)
        this.error = true
        this.loading = false
      })
  },
  methods: {
    confirmDelete: function (id, index) {
      this.$dialog.confirm('Are you sure you want to delete this list?')
        .then(() => {
          console.log('Deletion confirmed')
          AXIOS.delete('/itemsList/' + id)
            .then(() => {
              // remove list from current page
              this.itemsLists.splice(index, 1)
            })
            .catch(error => {
              console.log(error)
              this.error = true
            })
        })
        .catch(function () {
          console.log('Deletion canceled')
        })
    }
  }
}
</script>
