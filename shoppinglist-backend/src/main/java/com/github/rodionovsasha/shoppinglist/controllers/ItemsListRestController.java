package com.github.rodionovsasha.shoppinglist.controllers;

import com.github.rodionovsasha.shoppinglist.dto.ItemsListDto;
import com.github.rodionovsasha.shoppinglist.entities.ItemsList;
import com.github.rodionovsasha.shoppinglist.services.ItemsListService;
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
        @ApiResponse(code = 404, message = "Items list not found")
})
@Slf4j
@RestController
@RequestMapping
public class ItemsListRestController {
    private static final String ITEMS_LIST_BASE_PATH = "/itemsList";

    @Autowired
    private ItemsListService itemsListService;

    @ApiOperation("Get all lists")
    @GetMapping
    public Iterable<ItemsList> getAllLists() {
        return itemsListService.findAllLists();
    }

    @ApiOperation("Get list")
    @GetMapping(ITEMS_LIST_BASE_PATH + "/{id}")
    public ItemsList getItemsList(@PathVariable final long id) {
        return itemsListService.getItemsListById(id);
    }

    @ApiOperation(value = "Add list", response = ItemsListDto.CreateResponse.class)
    @PostMapping(ITEMS_LIST_BASE_PATH) @ResponseStatus(CREATED)
    public ItemsListDto.CreateResponse saveItemsList(@Valid @RequestBody ItemsListDto itemsListDto) {
        long id = itemsListService.addItemsList(itemsListDto);
        return new ItemsListDto.CreateResponse(id);
    }

    @ApiOperation("Update list")
    @PutMapping(ITEMS_LIST_BASE_PATH)
    public ResponseEntity editItemsList(@Valid @RequestBody ItemsListDto itemsListDto) {
        if (itemsListService.getItemsListById(itemsListDto.getId()) == null) {
            log.error("List with id '" + itemsListDto.getId() + "' not found");
            return ResponseEntity.notFound().build();
        }
        itemsListService.updateItemsList(itemsListDto);
        return ResponseEntity.ok(itemsListDto);
    }

    @ApiOperation("Delete list")
    @DeleteMapping(ITEMS_LIST_BASE_PATH + "/{id}")  @ResponseStatus(NO_CONTENT)
    public void deleteItemsList(@PathVariable final long id) {
        itemsListService.deleteItemsList(id);
    }
}
