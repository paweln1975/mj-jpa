package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.*;
import pl.paweln.jpa.entities.ids.CurrencyId;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ApplicationJPA {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationJPA.class);
    public static void main(String[] args) {
        operateOnBank();
        operateOnCurrency();
        operateOnMarket();
        operateOnInvestments();
    }

    private static void operateOnMarket() {
        Currency currencydb = readCurrency("Poland", "Zloty");
        Currency currency;
        if (currencydb != null) {
            currency = currencydb;
        } else {
            currency = EntitiesBuilder.createCurrency("Poland", "Zloty", "PLN");
        }

        Market market = new Market();
        market.setMarketName("Gielda Papierow Wartosciowych");
        market.setCurrency(currency);

        persistObject(market);
    }

    private static void operateOnCurrency() {
        Currency dbCurrency = readCurrency("USA", "Dollar");
        if (dbCurrency != null) {
            deleteObject(dbCurrency);
        }

        Currency currency = EntitiesBuilder.createCurrency("USA", "Dollar", "$");
        persistObject(currency);
    }


    private static Currency readCurrency (String countryName, String currencyName) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Currency currency = entityManager.find(Currency.class, new CurrencyId(currencyName, countryName));

        tx.commit();
        return currency;

    }



    private static void operateOnBank() {
        Bank bank = EntitiesBuilder.createBank("Pekao S.A.");
        persistObject(bank);

        Bank dbBank1 = (Bank) readObject(Bank.class, bank.getBankId());

        logger.info("Bank: " + dbBank1.getName());

        updateBank(dbBank1);

        Bank bank1 = (Bank)detachAttachFromPersistentContext(dbBank1);

        //deleteBank(bank1);
    }

    private static void operateOnInvestments() {
        Stock stock = EntitiesBuilder.createStock("Stock 1", 10);
        Bond bond = EntitiesBuilder.createBond("Bond 1", 20);

        persistObjects(new Object[] {stock, bond});
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

    private static void deleteObject(Object object) {
        EntityTransaction tx = null;

        try {
            EntityManager entityManager = JPAUtil.getEntityManager();

            tx = entityManager.getTransaction();
            tx.begin();

            entityManager.remove(object);

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
