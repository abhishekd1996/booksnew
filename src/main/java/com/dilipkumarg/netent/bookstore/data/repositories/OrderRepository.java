package com.dilipkumarg.netent.bookstore.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dilipkumarg.netent.bookstore.data.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Page<Order> findAllByBookIsbn(String isbn, Pageable pageable);
}
