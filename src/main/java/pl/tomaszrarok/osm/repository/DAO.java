package pl.tomaszrarok.osm.repository;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> findById(Long id);

    List<T> findAll();

    void save(T t);

    void delete(Long id);
}
