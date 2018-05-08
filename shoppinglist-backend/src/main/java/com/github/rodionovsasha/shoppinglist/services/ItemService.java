package com.github.rodionovsasha.shoppinglist.services;


import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;

public interface ItemService {
    void addItem(ItemDto itemDto);
    void updateItem(ItemDto itemDto);
    void deleteItem(long id);
    Item getItemById(long id);
    void toggleBoughtStatus(long id);
}
