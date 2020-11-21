package com.abhishek.bookstore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.bookstore.config.Constants;
import com.abhishek.bookstore.data.entities.BookStock;
import com.abhishek.bookstore.data.models.StockEntryRequest;
import com.abhishek.bookstore.services.InventoryService;

@RestController
@RequestMapping(Constants.BASE_PATH + "/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(final InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Page<BookStock> fetchStock(Pageable pageable) {
        return inventoryService.findAll(pageable);
    }

    @GetMapping("/{isbn}")
    public BookStock fetchStockForBook(@PathVariable String isbn, Pageable pageable) {
        return inventoryService.getBookStock(isbn);
    }

    // these should be for Admin
    @PostMapping
    public BookStock addStock(@RequestBody StockEntryRequest request) {
        return inventoryService.addStock(request);
    }
}
