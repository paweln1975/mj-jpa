package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ApplicationJpql {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationJpql.class);

    public static void main(String[] args) {
        readTransactions();
        readAccountsWithJoin();
    }

    private static void readTransactions() {

        EntityManager entityManager = JPAUtil.getEntityManager();

        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction t order by t.TITLE", Transaction.class);
        List<Transaction> transactionList = query.getResultList();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }

    }

    @SuppressWarnings("unchecked")
    private static void readAccountsWithJoin() {

        EntityManager entityManager = JPAUtil.getEntityManager();

        Query query = entityManager.createQuery(
                    "select distinct t.account.NAME," +
                        " concat(concat(t.account.NAME, ' '), 'TT')"  +
                        " from Transaction t" +
                        " join t.account a" +
                        " where t.AMOUNT > 500 and lower(t.transactionType) = 'deposit'");
        List<Object[]> accountList = query.getResultList();

        for (Object[] accountData : accountList) {
            logger.info("Account: " + accountData[0] + " " + accountData[1]);
        }

    }
}
