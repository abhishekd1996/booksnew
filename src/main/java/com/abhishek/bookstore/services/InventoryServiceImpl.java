package com.abhishek.bookstore.services;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhishek.bookstore.data.entities.BookStock;
import com.abhishek.bookstore.data.entities.StockEntry;
import com.abhishek.bookstore.data.repositories.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(
            final InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BookStock> findAll(final Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public BookStock addStock(final String bookIsbn, final Integer stock) {
        final BookStock bookStock = getBookStock(bookIsbn);

        StockEntry stockEntry = new StockEntry();
        stockEntry.setStock(stock);
        stockEntry.setBookStock(bookStock);

        bookStock.setTotalStock(bookStock.getTotalStock() + stock);
        bookStock.getStockEntries().add(stockEntry);

        return inventoryRepository.save(bookStock);
    }

    @Transactional
    @Override
    public BookStock updateOrderedStock(final String bookIsbn, final Integer quantity) {
        final BookStock bookStock = getBookStock(bookIsbn);
        bookStock.setOrderedStock(bookStock.getOrderedStock() + quantity);
        return inventoryRepository.save(bookStock);
    }

    @Transactional(readOnly = true)
    @Override
    public BookStock getBookStock(final String bookIsbn) {
        return inventoryRepository.findByBookIsbn(bookIsbn)
                .orElseGet(() -> new BookStock(bookIsbn));
    }

    @Transactional
    @Override
    public <T> T doInTransaction(
            final String bookIsbn, final Integer requiredQuantity, final Function<BookStock, T> callback) {
        // ensuring the quantity
        BookStock bookStock = null;
        // as per the requirement adding stocks on demand.
        // while doing this making sure other threads will not be conflicted.
        // In case of multi server with Single database, optimistic lock ensures the data validity
        // this can be optimized by using cache.
        synchronized ("store_stocks_" + bookIsbn) {
            bookStock = getBookStock(bookIsbn);
            if (bookStock.getRemaining() < requiredQuantity) {
                bookStock = addStock(bookIsbn, requiredQuantity - bookStock.getRemaining());
            }
            // updating the stock
            updateOrderedStock(bookIsbn, requiredQuantity);
        }
        return callback.apply(bookStock);
    }
}
