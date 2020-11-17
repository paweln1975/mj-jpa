package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static Logger logger = LoggerFactory.getLogger(JPAUtil.class);

    private static EntityManagerFactory entityManagerFactory = null;
    private static final EntityManager entityManager = createEntityManager();

    private static EntityManager createEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
        return entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager() { return entityManager;}

}
