package com.dilipkumarg.netent.bookstore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dilipkumarg.netent.bookstore.data.entities.Order;
import com.dilipkumarg.netent.bookstore.data.models.OrderRequest;

public interface OrderService {

    Page<Order> findAllOrders(Pageable pageable);

    Page<Order> findAllByBook(String bookIsbn, Pageable pageable);

    Order buyBook(OrderRequest orderRequest);
}
