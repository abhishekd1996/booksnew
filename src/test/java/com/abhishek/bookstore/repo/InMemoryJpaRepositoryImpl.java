package com.abhishek.bookstore.repo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.persistence.Id;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public class InMemoryJpaRepositoryImpl<T, ID> implements JpaRepository<T, ID> {

    protected Map<ID, T> map = new LinkedHashMap<>();

    @Override
    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<T> findAll(final Sort sort) {
        return findAll();
    }

    @Override
    public List<T> findAllById(final Iterable<ID> ids) {
        return StreamSupport.stream(ids.spliterator(), false)
                .map(id -> map.get(id))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public <S extends T> List<S> saveAll(final Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends T> S saveAndFlush(final S entity) {
        ID idValue = null;
        for (final Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                try {
                    idValue = (ID) field.get(entity);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        map.put(idValue, entity);
        return entity;
    }

    @Override
    public void deleteInBatch(final Iterable<T> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public T getOne(final ID id) {
        return map.get(id);
    }

    @Override
    public <S extends T> List<S> findAll(final Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(final Example<S> example, final Sort sort) {
        return null;
    }

    @Override
    public Page<T> findAll(final Pageable pageable) {
        return new PageImpl<>(this.findAll());
    }

    @Override
    public <S extends T> S save(final S entity) {
        return saveAndFlush(entity);
    }

    @Override
    public Optional<T> findById(final ID id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public boolean existsById(final ID id) {
        return map.containsKey(id);
    }

    @Override
    public long count() {
        return map.size();
    }

    @Override
    public void deleteById(final ID id) {
        map.remove(id);
    }

    @Override
    public void delete(final T entity) {
    }

    @Override
    public void deleteAll(final Iterable<? extends T> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends T> Optional<S> findOne(final Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends T> Page<S> findAll(final Example<S> example, final Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> long count(final Example<S> example) {
        return 0;
    }

    @Override
    public <S extends T> boolean exists(final Example<S> example) {
        return false;
    }
}
