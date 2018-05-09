package com.github.rodionovsasha.shoppinglist.controllers;

import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;
import com.github.rodionovsasha.shoppinglist.services.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ApiResponses({
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Item not found")
})
@Slf4j
@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    private ItemService itemService;

    @ApiOperation("Get item")
    @GetMapping("/{id}")
    public Item getItem(@PathVariable final long id) {
        return itemService.getItemById(id);
    }

    @ApiOperation(value = "Add item", response = ItemDto.Response.class)
    @PostMapping @ResponseStatus(CREATED)
    public ItemDto.Response saveItem(@Valid @RequestBody ItemDto itemDto) {
        long id = itemService.addItem(itemDto);
        return new ItemDto.Response(id, itemDto.getListId());
    }

    @ApiOperation("Update item")
    @PutMapping
    public ResponseEntity editItem(@Valid @RequestBody ItemDto itemDto) {
        if (itemService.getItemById(itemDto.getId()) == null) {
            log.error("Item with id '" + itemDto.getId() + "' not found");
            return ResponseEntity.notFound().build();
        }
        itemService.updateItem(itemDto);
        return ResponseEntity.ok(itemDto);
    }

    @ApiOperation("Delete item")
    @DeleteMapping("/{id}") @ResponseStatus(NO_CONTENT)
    public void deleteItem(@PathVariable final long id) {
        itemService.deleteItem(id);
    }
}