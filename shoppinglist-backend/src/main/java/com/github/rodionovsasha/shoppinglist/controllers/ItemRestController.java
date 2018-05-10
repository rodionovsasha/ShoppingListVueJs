package com.github.rodionovsasha.shoppinglist.controllers;

import com.github.rodionovsasha.shoppinglist.dto.ItemDto;
import com.github.rodionovsasha.shoppinglist.entities.Item;
import com.github.rodionovsasha.shoppinglist.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Api(tags = "Item")
@RestController
@RequestMapping(value = "/item", produces = APPLICATION_JSON_VALUE)
public class ItemRestController {
    @Autowired
    private ItemService itemService;

    @ApiOperation("Get item")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @GetMapping("/{id}")
    public Item getItem(@PathVariable long id) {
        return itemService.getItemById(id);
    }

    @ApiOperation(value = "Add item", response = ItemDto.CreateResponse.class)
    @ApiResponses(@ApiResponse(code = 400, message = "Bad request"))
    @PostMapping @ResponseStatus(CREATED)
    public ItemDto.CreateResponse saveItem(@Valid @RequestBody ItemDto itemDto) {
        long id = itemService.addItem(itemDto);
        return new ItemDto.CreateResponse(id, itemDto.getListId());
    }

    @ApiOperation("Update item")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @PatchMapping("/{id}") @ResponseStatus(NO_CONTENT)
    public void updateItem(@PathVariable long id, @Valid @RequestBody ItemDto itemDto) {
        itemService.updateItem(id, itemDto);
    }

    @ApiOperation("Delete item")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @DeleteMapping("/{id}") @ResponseStatus(NO_CONTENT)
    public void deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
    }
}
