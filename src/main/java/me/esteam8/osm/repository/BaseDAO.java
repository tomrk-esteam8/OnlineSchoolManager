package me.esteam8.osm.repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import me.esteam8.osm.model.BaseEntity;

public abstract class BaseDAO<T extends BaseEntity> implements DAO<T> {

    protected EntityManagerFactory entityManagerFactory;

    public BaseDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
    }

    public Optional<T> findById(Long id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            return (Optional<T>) Optional.ofNullable(null).orElse(manager.find(getClassName(), id));
        } catch (ClassNotFoundException e) {
        } finally {
            manager.close();
        }
        return Optional.ofNullable(null);
    }

    public List<T> findAll() {
        EntityManager manager = entityManagerFactory.createEntityManager();
        try {
            Query query = manager.createQuery(getQuery());
            @SuppressWarnings("unchecked")
            List<T> items = query.getResultList();
            return items;

        } finally {
            manager.close();
        }
    }

    public void save(T baseEntity) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.merge(baseEntity);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    public void delete(Long id) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            T object = (T) manager.find(getClassName(), id);
            manager.remove(object);
            tx.commit();
        } catch (RuntimeException | ClassNotFoundException e) {
            tx.rollback();
        } finally {
            manager.close();
        }
    }

    abstract protected String getQuery();

    protected Class getClassName() throws ClassNotFoundException {
        Type sooper = getClass().getGenericSuperclass();
        Type t = ((ParameterizedType) sooper).getActualTypeArguments()[0];
        return Class.forName(t.getTypeName());
    }

}
