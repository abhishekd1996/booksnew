package com.dilipkumarg.netent.bookstore.repo;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.dilipkumarg.netent.bookstore.data.entities.Order;
import com.dilipkumarg.netent.bookstore.data.repositories.OrderRepository;

public class InMemoryOrderRepository extends InMemoryJpaRepositoryImpl<Order, Integer> implements OrderRepository {

    @Override
    public Page<Order> findAllByBookIsbn(
            final String isbn, final Pageable pageable) {
        return new PageImpl<>(map.values().stream()
                .filter(order -> order.getBookIsbn().equals(isbn))
                .collect(Collectors.toList()));
    }
}
