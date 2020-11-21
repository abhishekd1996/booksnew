package com.abhishek.bookstore.data.models;

import lombok.Data;

@Data
public class StockEntryRequest {
    private String bookIsbn;
    private int stock;
}
