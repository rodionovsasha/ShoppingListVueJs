package com.github.rodionovsasha.shoppinglist.services;


import com.github.rodionovsasha.shoppinglist.dto.ItemsListDto;
import com.github.rodionovsasha.shoppinglist.entities.ItemsList;

public interface ItemsListService {
    long addItemsList(ItemsListDto itemsListDto);
    void updateItemsList(long id, ItemsListDto itemsListDto);
    void deleteItemsList(long id);
    ItemsList getItemsListById(long id);
    Iterable<ItemsList> findAllLists();
}
