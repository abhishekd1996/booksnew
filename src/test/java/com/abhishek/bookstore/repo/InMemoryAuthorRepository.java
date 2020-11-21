package com.abhishek.bookstore.repo;

import com.abhishek.bookstore.data.repositories.AuthorRepository;
import com.abhishek.bookstore.data.entities.Author;

public class InMemoryAuthorRepository extends InMemoryJpaRepositoryImpl<Author, Integer> implements AuthorRepository {
}
