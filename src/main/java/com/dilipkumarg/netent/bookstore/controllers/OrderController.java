package com.dilipkumarg.netent.bookstore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dilipkumarg.netent.bookstore.config.Constants;
import com.dilipkumarg.netent.bookstore.data.entities.Order;
import com.dilipkumarg.netent.bookstore.data.models.OrderRequest;
import com.dilipkumarg.netent.bookstore.services.OrderService;

@RestController
@RequestMapping(Constants.BASE_PATH + "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderService.findAllOrders(pageable);
    }

    @PostMapping
    public Order buyBook(@RequestBody @Validated OrderRequest orderRequest) {
        return orderService.buyBook(orderRequest);
    }
}
