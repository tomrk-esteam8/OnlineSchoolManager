package me.esteam8.osm.repository;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> findById(Long id);

    List<T> findAll();

    T save(T t);

    void delete(Long id);
}
