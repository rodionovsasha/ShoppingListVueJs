package com.github.rodionovsasha.shoppinglist.controllers;

import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;
import com.github.rodionovsasha.shoppinglist.services.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @ApiOperation("Add item")
    @PostMapping
    public ResponseEntity<ItemDto> saveItem(@Valid @RequestBody ItemDto itemDto) {
        itemService.addItem(itemDto);
        return new ResponseEntity<>(itemDto, HttpStatus.CREATED);
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
    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable final long id) {
        if (itemService.getItemById(id) == null) {
            log.error("Item with id " + id + " not found");
            return ResponseEntity.notFound().build();
        }

        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
