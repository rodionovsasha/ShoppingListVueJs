<template>
  <div class="col-md-6 offset-md-3">
    <error-alert v-if="error" v-bind:message="message"/>

    <h1>Edit {{this.name}}</h1>
    <form @submit.prevent="editItem" method="post" class="form-horizontal" @keydown="fieldErrors.clear($event.target.name)">
      <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Name:</label>
        <div class="col-sm-6">
          <input type="text" id="name" name="name" v-model="name" class="form-control"/>
          <small v-if="fieldErrors.has('name')" class="text-danger" v-text="fieldErrors.get('name')"></small>
        </div>
      </div>
      <div class="form-group">
        <label for="comment" class="col-sm-2 control-label">Comment:</label>
        <div class="col-sm-6">
          <textarea id="comment" name="comment" v-model="comment" rows="3" class="form-control"></textarea>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <input type="submit" value="Update item" class="btn btn-outline-secondary" :disabled="fieldErrors.any()"/>
        </div>
      </div>
    </form>
    <ul class="list-inline">
      <li class="list-inline-item">
        <router-link v-bind:to="{ path: '/itemsList/' + this.listId }" class="btn btn-info btn-sm" role="button" data-toggle="tooltip" data-placement="bottom" title="Back to the list">
          <span class="oi oi-arrow-thick-left"></span>
        </router-link>
      </li>
      <all-lists-button/>
    </ul>
  </div>
</template>

<script>
import {AXIOS} from './http-common'
import ErrorAlert from './ErrorAlert'
import AllListsButton from './AllListsButton'
import FieldErrors from './FieldErrors'

export default {
  name: 'EditItem',
  components: {AllListsButton, ErrorAlert},
  props: ['itemId'],
  data () {
    return {
      name: '',
      comment: '',
      listId: '',
      error: false,
      fieldErrors: new FieldErrors(),
      message: ''
    }
  },
  mounted () {
    AXIOS.get('/item/' + this.itemId)
      .then(response => {
        this.name = response.data.name
        this.comment = response.data.comment
        this.listId = response.data.listId
        this.error = false
      })
      .catch(error => {
        console.log(error)
        this.error = true
      })
  },
  methods: {
    editItem: function () {
      AXIOS.put('/item/' + this.itemId, {
        name: this.name,
        comment: this.comment,
        listId: this.listId
      })
        .then(() => {
          this.$router.push('/itemsList/' + this.listId)
        })
        .catch(error => {
          if (!error.response) {
            console.log(error)
            this.error = true
            this.message = error.toString()
          } else {
            this.fieldErrors.set(error.response.data)
            console.log(this.fieldErrors)
          }
        })
    }
  }
}
</script>
