package com.dilipkumarg.netent.bookstore.repo;

import java.util.Optional;

import com.dilipkumarg.netent.bookstore.data.entities.BookStock;
import com.dilipkumarg.netent.bookstore.data.repositories.InventoryRepository;

public class InMemoryInventoryRepository extends InMemoryJpaRepositoryImpl<BookStock, Integer> implements
        InventoryRepository {

    @Override
    public Optional<BookStock> findByBookIsbn(final String isbn) {
        return map.values().stream()
                .filter(bookStock -> bookStock.getBookIsbn().equals(isbn))
                .findFirst();
    }
}
