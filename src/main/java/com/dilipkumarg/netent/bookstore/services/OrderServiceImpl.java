package com.dilipkumarg.netent.bookstore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.entities.Order;
import com.dilipkumarg.netent.bookstore.data.models.OrderRequest;
import com.dilipkumarg.netent.bookstore.data.models.OrderStatus;
import com.dilipkumarg.netent.bookstore.data.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final BookService bookService;
    private final InventoryService inventoryService;
    private final OrderRepository repository;

    public OrderServiceImpl(
            final BookService bookService, final InventoryService inventoryService,
            final OrderRepository repository) {
        this.bookService = bookService;
        this.inventoryService = inventoryService;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findAllOrders(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findAllByBook(final String bookIsbn, final Pageable pageable) {
        return repository.findAllByBookIsbn(bookIsbn, pageable);
    }

    @Transactional
    @Override
    public Order buyBook(final OrderRequest request) {
        final Book book = bookService.getByIsbn(request.getBookIsbn());
        return inventoryService.doInTransaction(request.getBookIsbn(), request.getQuantity(), bookStock -> {
            Order order = new Order();
            order.setBookIsbn(request.getBookIsbn());
            order.setQuantity(request.getQuantity());
            order.setCost(book.getPrice());
            order.setTotal(request.getQuantity() * book.getPrice());
            order.setOrderStatus(OrderStatus.COMPLETED);
            return repository.save(order);
        });
    }
}
