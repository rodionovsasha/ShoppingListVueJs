package com.github.rodionovsasha.shoppinglist.services.impl;

import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;
import com.github.rodionovsasha.shoppinglist.exceptions.NotFoundException;
import com.github.rodionovsasha.shoppinglist.repositories.ItemRepository;
import com.github.rodionovsasha.shoppinglist.services.ItemService;
import com.github.rodionovsasha.shoppinglist.services.ItemsListService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemsListService itemsListService;

    @Override
    public long addItem(ItemDto itemDto) {
        val item = itemDto.toItem();
        item.setItemsList(itemsListService.getItemsListById(itemDto.getListId()));
        return itemRepository.save(item).getId();
    }

    @Override
    public void updateItem(long id, ItemDto itemDto) {
        val item = getItemById(id);
        itemDto.toItem(item);
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.delete(getItemById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Item getItemById(long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> NotFoundException.forId(itemId));
    }

    @Override
    public void toggleBoughtStatus(long itemId) {
        val item = getItemById(itemId);
        item.setBought(!item.isBought());
        itemRepository.save(item);
    }
}
