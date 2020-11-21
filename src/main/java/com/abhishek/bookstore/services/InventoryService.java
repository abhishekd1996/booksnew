package com.abhishek.bookstore.services;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abhishek.bookstore.data.entities.BookStock;
import com.abhishek.bookstore.data.models.StockEntryRequest;

public interface InventoryService {

    Page<BookStock> findAll(Pageable pageable);

    BookStock addStock(String bookIsbn, Integer stock);

    default BookStock addStock(StockEntryRequest request) {
        return addStock(request.getBookIsbn(), request.getStock());
    }

    BookStock updateOrderedStock(String bookIsbn, Integer quantity);

    BookStock getBookStock(String bookIsbn);

    <T> T doInTransaction(String bookIsbn, Integer requiredQuantity, Function<BookStock, T> callback);
}
