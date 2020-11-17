package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ApplicationJPA {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationJPA.class);
    public static void main(String[] args) {
        operateOnBank();
    }

    private static void operateOnBank() {
        Bank bank = EntitiesBuilder.createBank("Pekao S.A.");
        persistObject(bank);

        Bank dbBank1 = (Bank) readObject(Bank.class, 1L);

        logger.info("Bank: " + dbBank1.getName());

        updateBank(dbBank1);

        Bank bank1 = (Bank)detachAttachFromPersistentContext(dbBank1);

        deleteBank(bank1);
    }

    private static Object detachAttachFromPersistentContext(Object object) {
        EntityTransaction tx = null;
        Object retObj = null;

        try {
            EntityManager entityManager = JPAUtil.getEntityManager();

            tx = entityManager.getTransaction();
            tx.begin();

            logger.info("Exists in the persistent context " + entityManager.contains(object));
            entityManager.clear();
            logger.info("Exists in the persistent context " + entityManager.contains(object));

            retObj = entityManager.merge(object);


            tx.commit();

        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Exception during updating objects: ", e);
        }

        return retObj;
    }
    private static void updateBank(Bank bank) {
        EntityTransaction tx = null;

        try {
            EntityManager entityManager = JPAUtil.getEntityManager();

            tx = entityManager.getTransaction();
            tx.begin();

            bank.setName("ING");

            tx.commit();

        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Exception during updating objects: ", e);
        }
    }

    private static void deleteBank(Bank bank) {
        EntityTransaction tx = null;

        try {
            EntityManager entityManager = JPAUtil.getEntityManager();

            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.remove(bank);

            tx.commit();

        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Exception during deleting objects: ", e);
        }
    }


    private static Object readObject (Class aClass, long Id) {
        EntityTransaction tx = null;
        Object object = null;

        try {
            EntityManager entityManager = JPAUtil.getEntityManager();

            tx = entityManager.getTransaction();
            tx.begin();

            object = entityManager.find(aClass, Id);

            tx.commit();

        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Exception during retrieving objects: ", e);
        }

        return object;
    }

    private static void persistObject(Object object) {
        persistObjects(new Object[] {object});
    }

    private static void persistObjects(Object[] objects) {

        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction tx = null;

        try {

            tx = entityManager.getTransaction();

            tx.begin();

            for (Object object : objects) {
                entityManager.persist(object);
            }


            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Exception during persisting objects: ", e);
        }
    }
}
