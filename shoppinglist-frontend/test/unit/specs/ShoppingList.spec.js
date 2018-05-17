import Vue from 'vue'
import ShoppingList from '@/components/ShoppingList'

describe('ShoppingList.vue', () => {
  it('Should render correct heading', () => {
    const Constructor = Vue.extend(ShoppingList);
    const vm = new Constructor().$mount();
    expect(vm.$el.querySelector('h1').textContent)
      .toEqual('Shopping lists')
  })
});
