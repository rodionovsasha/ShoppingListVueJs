import ShoppingList from '@/components/ShoppingList'
import { mount } from '@vue/test-utils'

describe('ShoppingList.vue', () => {
  let wrapper = mount(ShoppingList)

  test('Should render correct heading', () => {
    expect(wrapper.vm.$el.querySelector('h1').textContent).toEqual('Shopping lists')
  })

  test('Should render correct html snapshot with loading', () => {
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('Should render correct h1 tag', () => {
    expect(wrapper.html()).toContain('<h1>Shopping lists</h1>')
  })

  test('Should render correct router-link', () => {
    expect(wrapper.contains('router-link')).toBe(true)
  })

  test('Should render correct html snapshot without loading', () => {
    let wrapper = mount(ShoppingList, {
      data: {
        loading: false,
        itemsLists: [{name: 'list1', id: 1}]
      }
    })
    expect(wrapper.html()).toMatchSnapshot()
  })

  test('Should render error message when backend is not available', () => {
    let wrapper = mount(ShoppingList, {
      data: {
        error: true,
        message: "Network connection"
      }
    })
    expect(wrapper.html()).toMatchSnapshot()
  })
});
