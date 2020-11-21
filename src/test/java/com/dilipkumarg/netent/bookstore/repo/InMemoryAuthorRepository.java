package com.dilipkumarg.netent.bookstore.repo;

import com.dilipkumarg.netent.bookstore.data.entities.Author;
import com.dilipkumarg.netent.bookstore.data.repositories.AuthorRepository;

public class InMemoryAuthorRepository extends InMemoryJpaRepositoryImpl<Author, Integer> implements AuthorRepository {
}
