package com.github.rodionovsasha.shoppinglist.dto;


import com.github.rodionovsasha.shoppinglist.entities.Item;
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
public class ItemDto implements Serializable {
    private static final long serialVersionUID = 1;

    private Long id;
    private long listId;
    @NotEmpty
    private String name;
    private String comment;

    public ItemDto(long listId, String name) {
        this.listId = listId;
        this.name = name;
    }

    public Item toItem() {
        return toItem(new Item());
    }

    public Item toItem(Item item) {
        item.setName(this.name);
        item.setComment(this.comment);
        return item;
    }

    @ApiModel("Create Item response")
    @AllArgsConstructor
    @Getter
    public static class CreateResponse {
        @ApiModelProperty(notes = "ID of newly created Item", example = "45326")
        private final long id;

        @ApiModelProperty(notes = "ID of parent items list", example = "4365")
        private final long listId;
    }
}
