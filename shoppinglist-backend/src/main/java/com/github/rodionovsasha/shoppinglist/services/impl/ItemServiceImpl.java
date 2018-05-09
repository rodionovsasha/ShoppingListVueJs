package com.github.rodionovsasha.shoppinglist.services.impl;

import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;
import com.github.rodionovsasha.shoppinglist.exceptions.NotFoundException;
import com.github.rodionovsasha.shoppinglist.repositories.ItemRepository;
import com.github.rodionovsasha.shoppinglist.repositories.ItemsListRepository;
import com.github.rodionovsasha.shoppinglist.services.ItemService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemsListRepository itemsListRepository;

    @Override
    public long addItem(ItemDto itemDto) {
        val item = itemDto.toItem();
        item.setItemsList(itemsListRepository
                .findById(itemDto.getListId())
                .orElseThrow(() -> NotFoundException.forId(itemDto.getListId()))
        );
        return itemRepository.save(item).getId();
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        val item = itemRepository
                .findById(itemDto.getId())
                .orElseThrow(() -> NotFoundException.forId(itemDto.getId()));
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
        val item = itemRepository.findById(itemId).orElseThrow(() -> NotFoundException.forId(itemId));
        item.setBought(!item.isBought());
        itemRepository.save(item);
    }
}
