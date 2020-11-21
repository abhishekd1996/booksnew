package com.dilipkumarg.netent.bookstore.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dilipkumarg.netent.bookstore.data.entities.BookStock;

@Repository
public interface InventoryRepository extends JpaRepository<BookStock, Integer> {

    Optional<BookStock> findByBookIsbn(String isbn);
}
