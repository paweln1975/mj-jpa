package pl.paweln.jpa.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Logger logger = LoggerFactory.getLogger(Dao.class);

    Optional<T> get(Long id);

    List<T> getAll();

    void save(T t);

    void delete(T t);

    void update(T t);

    void clear();

    void setEntityManager(EntityManager entityManager);
}
