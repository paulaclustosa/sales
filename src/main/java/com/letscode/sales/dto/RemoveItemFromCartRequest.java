package com.letscode.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemoveItemFromCartRequest {
    private String cartUuid;
    private String productUuid;
}
