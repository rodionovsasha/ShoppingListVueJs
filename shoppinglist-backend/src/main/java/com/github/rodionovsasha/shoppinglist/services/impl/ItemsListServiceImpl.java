package com.github.rodionovsasha.shoppinglist.services.impl;

import com.github.rodionovsasha.shoppinglist.dto.ItemsListDto;
import com.github.rodionovsasha.shoppinglist.entities.ItemsList;
import com.github.rodionovsasha.shoppinglist.exceptions.NotFoundException;
import com.github.rodionovsasha.shoppinglist.repositories.ItemsListRepository;
import com.github.rodionovsasha.shoppinglist.services.ItemsListService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
@Transactional
public class ItemsListServiceImpl implements ItemsListService {
    private final ItemsListRepository itemsListRepository;

    @Override
    public long addItemsList(ItemsListDto itemsListDto) {
        return itemsListRepository.save(itemsListDto.toItemsList()).getId();
    }

    @Override
    public void updateItemsList(long id, ItemsListDto itemsListDto) {
        val itemsList = getItemsListById(id);
        itemsListDto.toItemsList(itemsList);
        itemsListRepository.save(itemsList);
    }

    @Override
    public void deleteItemsList(long id) {
        itemsListRepository.delete(getItemsListById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public ItemsList getItemsListById(long id) {
        return itemsListRepository.findById(id).orElseThrow(() -> NotFoundException.forId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<ItemsList> findAllLists() {
        return itemsListRepository.findAll();
    }
}
