package com.github.rodionovsasha.shoppinglist.controllers;

import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;
import com.github.rodionovsasha.shoppinglist.services.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Item getItem(@PathVariable long id) {
        return itemService.getItemById(id);
    }

    @ApiOperation(value = "Add item", response = ItemDto.CreateResponse.class)
    @PostMapping @ResponseStatus(CREATED)
    public ItemDto.CreateResponse saveItem(@Valid @RequestBody ItemDto itemDto) {
        long id = itemService.addItem(itemDto);
        return new ItemDto.CreateResponse(id, itemDto.getListId());
    }

    @ApiOperation("Update item")
    @PatchMapping("/{id}") @ResponseStatus(NO_CONTENT)
    public void updateItem(@PathVariable long id, @Valid @RequestBody ItemDto itemDto) {
        itemService.updateItem(id, itemDto);
    }

    @ApiOperation("Delete item")
    @DeleteMapping("/{id}") @ResponseStatus(NO_CONTENT)
    public void deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
    }
}
