package com.github.rodionovsasha.shoppinglist.services;


import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;

public interface ItemService {
    long addItem(ItemDto itemDto);
    void updateItem(long id, ItemDto itemDto);
    void deleteItem(long id);
    Item getItemById(long id);
    void toggleBoughtStatus(long id);
}
