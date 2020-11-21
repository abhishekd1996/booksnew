package com.dilipkumarg.netent.bookstore.data.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderRequest {
    // currently handling for single book in each order, we can extend this by taking multiple books list.
    @NotBlank
    private String bookIsbn;
    @NotNull
    private Integer quantity;
}
