package pl.paweln.jpa.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractDao<T> implements Dao<T> {
    private EntityManager entityManager;
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> query = entityManager.createQuery("from " + this.getPersistentClass().getSimpleName(), this.getPersistentClass());
        return query.getResultList();
    }

    @Override
    public Optional<T> get(Long id) {
        return Optional.ofNullable(this.getEntityManager().find(this.getPersistentClass(), id));
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public void save(T object) {
        executeInsideTransaction(entityManager1 -> entityManager1.persist(object));
    }

    @Override
    public void delete(T object) {
        executeInsideTransaction(entityManager1 -> entityManager1.remove(object));
    }

    @Override
    public void update(T object) {
        executeInsideTransaction(entityManager1 -> entityManager1.merge(object));
    }

    public void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            logger.error("JPA error: ", e);
            tx.rollback();
            throw e;
        }
    }
}
