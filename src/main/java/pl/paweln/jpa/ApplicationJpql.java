package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ApplicationJpql {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationJpql.class);

    public static void main(String[] args) {
        readTransactions();
    }

    private static void readTransactions() {

        EntityManager entityManager = JPAUtil.getEntityManager();

        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction t order by t.TITLE", Transaction.class);
        List<Transaction> transactionList = query.getResultList();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }

    }
}
