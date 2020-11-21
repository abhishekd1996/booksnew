package com.dilipkumarg.netent.bookstore.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dilipkumarg.netent.bookstore.data.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

    Page<Book> findAllByAuthorId(Integer authorId, Pageable pageable);

}
