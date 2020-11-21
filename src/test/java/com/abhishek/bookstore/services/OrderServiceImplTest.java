package com.abhishek.bookstore.services;

import com.abhishek.bookstore.repo.InMemoryBookRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import com.abhishek.bookstore.data.entities.Author;
import com.abhishek.bookstore.data.entities.Book;
import com.abhishek.bookstore.data.models.OrderRequest;
import com.abhishek.bookstore.data.repositories.BookRepository;
import com.abhishek.bookstore.data.repositories.InventoryRepository;
import com.abhishek.bookstore.data.repositories.OrderRepository;
import com.abhishek.bookstore.repo.InMemoryAuthorRepository;
import com.abhishek.bookstore.repo.InMemoryInventoryRepository;
import com.abhishek.bookstore.repo.InMemoryOrderRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceImplTest {

    private OrderRepository repository;
    private BookRepository bookRepository;
    private InventoryRepository inventoryRepository;

    private InventoryService inventoryService;
    private BookService bookService;

    private OrderService orderService;

    @BeforeEach
    public void onBefore() {
        bookRepository = new InMemoryBookRepositoryImpl();
        bookService = new BookServiceImpl(bookRepository, new InMemoryAuthorRepository());

        inventoryRepository = new InMemoryInventoryRepository();
        inventoryService = new InventoryServiceImpl(inventoryRepository);

        repository = new InMemoryOrderRepository();
        orderService = new OrderServiceImpl(bookService, inventoryService, repository);
    }

    @Test
    void findAllOrders() {
        assertEquals(0, inventoryService.findAll(Pageable.unpaged()).getTotalElements());
        assertEquals(0, orderService.findAllOrders(Pageable.unpaged()).getTotalElements());
    }

    @Test
    void buyBook() {
        Book book = new Book();
        book.setIsbn("xyz");
        book.setTitle("abcd");
        book.setPrice(10.0);
        book.setAuthor(new Author(1, "abc"));

        bookService.registerBook(book);

        OrderRequest request = new OrderRequest();
        request.setBookIsbn("xyz");
        request.setQuantity(10);
        orderService.buyBook(request);

        assertEquals(1, inventoryService.findAll(Pageable.unpaged()).getTotalElements());
        assertEquals(1, orderService.findAllOrders(Pageable.unpaged()).getTotalElements());
    }
}
