<template>
  <div class="container">
    <div class="col-md-6 offset-md-3">
      <h1>Shopping list</h1>
      <ul class="list-unstyled">
        <list-item
          v-for="list in itemsLists"
          v-bind:list="list"
          v-bind:key="list.id">
        </list-item>
      </ul>
      <a href="/itemsList/add" class="btn btn-success btn-sm" data-toggle="tooltip" data-placement="right"
         title="Add new list" aria-hidden="true">
        <span class="oi oi-plus"></span>
      </a>
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
