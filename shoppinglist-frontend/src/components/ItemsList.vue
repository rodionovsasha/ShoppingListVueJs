<template>
  <div class="col-md-6 offset-md-3">
    <div v-if="error">
      <div class="alert alert-danger" role="alert">
        <strong>{{ message }}</strong>
      </div>
    </div>
    <div class="list-group">
      <h1 class="list-group-item list-group-item-info">{{ list.name }} <span class="badge badge-light">{{ list.items.length }}</span></h1>
      <div class="list-group-item" v-for="(item, index) in list.items" v-bind:key="item.id" v-bind:class="{ 'list-group-item-success': item.bought }">
        <div class="row">
          <div class="col-md-7">
            <router-link v-bind:to="{ path: '/item/' + item.id }">{{ item.name }}</router-link>
            <small class="clearfix" v-if="item.comment">{{ item.comment }}</small>
          </div>
          <div class="col-md-5">
            <ul class="list-inline text-right">
              <li class="list-inline-item">
                <a href="#" @click.prevent="buy(item.id, index)" class="badge badge-success" role="button">
                  {{ item.bought ? 'Take out' : 'Bought' }}
                </a>
              </li>
              <li class="list-inline-item">
                <router-link to="{ path: '/item/edit', params: {id: item.id} }" class="badge badge-warning" role="button">Edit</router-link>
              </li>
              <li class="list-inline-item">
                <a href="#" class="badge badge-danger" role="button" @click.prevent="confirmDelete(item.id, index)">Delete</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <ul class="list-inline">
      <li class="list-inline-item">
        <router-link to="{ path: '/item/add/', params: { listId: item.itemsList } }" class="btn btn-success btn-sm" role="button" data-toggle="tooltip" data-placement="bottom" title="Add new item">
          <span class="oi oi-plus"></span>
        </router-link>
      </li>
      <li class="list-inline-item">
        <router-link to="{ path: '/itemsList/edit/' + item.itemsList }" class="btn btn-warning btn-sm" role="button" data-toggle="tooltip" data-placement="bottom" title="Edit list">
          <span class="oi oi-pencil"></span>
        </router-link>
      </li>
      <li class="list-inline-item">
        <router-link to="{ path: '/itemsList/delete/' + item.itemsList }" onclick="return confirm('Are you sure you want to delete this list?')" class="btn btn-danger btn-sm" role="button" data-toggle="tooltip" data-placement="bottom" title="Delete list">
          <span class="oi oi-x"></span>
        </router-link>
      </li>
      <li class="list-inline-item">
        <router-link to="/" class="btn btn-info btn-sm" role="button" data-toggle="tooltip" data-placement="bottom" title="All lists">
          <span class="oi oi-list"></span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import {AXIOS} from './http-common'

export default {
  name: 'ItemsList',
  props: ['id'],
  data () {
    return {
      list: [],
      loading: true,
      error: false,
      message: ''
    }
  },
  mounted () {
    AXIOS.get('/itemsList/' + this.id)
      .then(response => {
        this.list = response.data
        this.loading = false
      })
      .catch(error => {
        console.log(error)
        this.error = true
        this.loading = false
      })
  },
  methods: {
    buy: function (id, index) {
      AXIOS.patch('/item/' + id + '/buy')
        .then(() => {
          console.log('Buy clicked')
          this.error = false
          this.list.items[index].bought = !this.list.items[index].bought
        })
        .catch(error => {
          console.log(error)
          this.error = true
          this.message = error.toString()
        })
    },
    confirmDelete: function (id, index) {
      this.$dialog.confirm('Are you sure you want to delete this item?')
        .then(() => {
          console.log('Delete clicked')
          AXIOS.delete('/item/' + id)
            .then(() => {
              // remove item from array
              this.list.items.splice(index, 1)
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
