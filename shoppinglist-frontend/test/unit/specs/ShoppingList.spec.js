import ShoppingList from '@/components/ShoppingList'
import { mount } from '@vue/test-utils'
import router from "../../../src/router"

describe('ShoppingList.vue', () => {
  let wrapper = mount(ShoppingList, {
    router: router
  })

  test('Should render correct heading', () => {
    expect(wrapper.vm.$el.querySelector('h1').textContent).toEqual('Shopping lists')
  })

  test('Should render correct html snapshot with loading', () => {
    let wrapper = mount(ShoppingList, {
      router: router,
      data: {
        loading: true
      }
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('Should render correct h1 tag', () => {
    expect(wrapper.html()).toContain('<h1>Shopping lists</h1>')
  })

  test('Should render correct ul tag', () => {
    expect(wrapper.contains('ul')).toBe(true)
  })

  test('Should render correct html snapshot without loading', () => {
    let wrapper = mount(ShoppingList, {
      router: router,
      data: {
        loading: false,
        itemsLists: [{name: 'list1', id: 1}]
      }
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('Should render error message when backend is not available', () => {
    let wrapper = mount(ShoppingList, {
      router: router,
      data: {
        error: true,
        message: "Network connection"
      }
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
});
