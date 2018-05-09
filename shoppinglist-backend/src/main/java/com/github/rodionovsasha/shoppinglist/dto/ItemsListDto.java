package com.github.rodionovsasha.shoppinglist.dto;


import com.github.rodionovsasha.shoppinglist.entities.ItemsList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ItemsListDto implements Serializable {
    private static final long serialVersionUID = 1;

    @NotEmpty
    private String name;

    public ItemsListDto(String name) {
        this.name = name;
    }

    public ItemsList toItemsList() {
        return toItemsList(new ItemsList());
    }

    public ItemsList toItemsList(ItemsList itemsList) {
        itemsList.setName(this.name);
        return itemsList;
    }

    @ApiModel("Get Items List response")
    @NoArgsConstructor
    @Setter @Getter
    public static class GetResponse {
        @ApiModelProperty(notes = "Items List ID", example = "45326")
        private long id;
        @ApiModelProperty(notes = "Items List name", example = "Shopping list")
        private String name;
        @ApiModelProperty(notes = "List of items in current Items List")
        private List<ItemDto> items;
    }

    @ApiModel("Create Items List response")
    @AllArgsConstructor
    @Getter
    public static class CreateResponse {
        @ApiModelProperty(notes = "ID of newly created Items List", example = "45326")
        private final long id;
    }
}
