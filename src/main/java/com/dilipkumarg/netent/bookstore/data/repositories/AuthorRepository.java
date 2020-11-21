package com.dilipkumarg.netent.bookstore.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dilipkumarg.netent.bookstore.data.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
