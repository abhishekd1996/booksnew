package com.abhishek.bookstore.services;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import com.abhishek.bookstore.data.entities.BookStock;
import com.abhishek.bookstore.data.models.StockEntryRequest;
import com.abhishek.bookstore.data.repositories.InventoryRepository;
import com.abhishek.bookstore.repo.InMemoryInventoryRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryServiceImplTest {

    private InventoryRepository repository;
    private InventoryService inventoryService;

    @BeforeEach
    public void onBefore() {
        repository = new InMemoryInventoryRepository();
        inventoryService = new InventoryServiceImpl(repository);
    }

    @Test
    void findAll() {
        assertEquals(0, inventoryService.findAll(Pageable.unpaged()).getTotalElements());

        StockEntryRequest request = new StockEntryRequest();
        request.setBookIsbn("xyz");
        request.setStock(100);
        inventoryService.addStock(request);

        final List<BookStock> stocks = inventoryService.findAll(Pageable.unpaged()).getContent();

        assertEquals(1, stocks.size());
        assertEquals(100, stocks.get(0).getTotalStock());
        assertEquals(0, stocks.get(0).getOrderedStock());
        assertEquals(1, stocks.get(0).getStockEntries().size());
    }

    @Test
    void addStock() {
        StockEntryRequest request = new StockEntryRequest();
        request.setBookIsbn("xyz");
        request.setStock(100);
        inventoryService.addStock(request);
        inventoryService.addStock(request); // adding twice

        final List<BookStock> stocks = inventoryService.findAll(Pageable.unpaged()).getContent();

        assertEquals(1, stocks.size());
        assertEquals(200, stocks.get(0).getTotalStock());
        assertEquals(0, stocks.get(0).getOrderedStock());
        assertEquals(2, stocks.get(0).getStockEntries().size());
    }

    @Test
    void updateOrderedStock() {
        StockEntryRequest request = new StockEntryRequest();
        request.setBookIsbn("xyz");
        request.setStock(100);
        inventoryService.addStock(request);
        inventoryService.updateOrderedStock("xyz", 10); // adding twice

        final List<BookStock> stocks = inventoryService.findAll(Pageable.unpaged()).getContent();

        assertEquals(1, stocks.size());
        assertEquals(100, stocks.get(0).getTotalStock());
        assertEquals(10, stocks.get(0).getOrderedStock());
        assertEquals(1, stocks.get(0).getStockEntries().size());
    }

    @Test
    void doInTransaction() {
        StockEntryRequest request = new StockEntryRequest();
        request.setBookIsbn("xyz");
        request.setStock(5);
        inventoryService.addStock(request);

        inventoryService.doInTransaction("xyz", 10, stock -> 10);

        final List<BookStock> stocks = inventoryService.findAll(Pageable.unpaged()).getContent();

        assertEquals(1, stocks.size());
        assertEquals(10, stocks.get(0).getTotalStock());
        assertEquals(10, stocks.get(0).getOrderedStock());
        assertEquals(2, stocks.get(0).getStockEntries().size());
    }
}
