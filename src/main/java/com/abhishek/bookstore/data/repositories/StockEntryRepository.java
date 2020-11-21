package com.abhishek.bookstore.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhishek.bookstore.data.entities.StockEntry;

@Repository
public interface StockEntryRepository extends JpaRepository<StockEntry, Integer> {
}
