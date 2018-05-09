package com.github.rodionovsasha.shoppinglist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class FieldErrorDTO {
    private final String field;
    private final String message;
}
