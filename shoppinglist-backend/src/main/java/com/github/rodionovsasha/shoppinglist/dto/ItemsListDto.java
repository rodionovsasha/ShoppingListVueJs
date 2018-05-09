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

@Getter @Setter
@NoArgsConstructor
public class ItemsListDto implements Serializable {
    private static final long serialVersionUID = 1;

    private long id;
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

    @ApiModel("Create Items List response")
    @AllArgsConstructor
    @Getter
    public static class CreateResponse {
        @ApiModelProperty(notes = "ID of newly created Items List", example = "45326")
        private final long id;
    }
}
