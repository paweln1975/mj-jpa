package pl.paweln.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ApplicationJpql {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationJpql.class);

    public static void main(String[] args) {
        readTransactions();
        readAccountsWithJoin();
        readTransactionsWithCriteriaAPI();
        readTransactionsWithCriteriaAPIShort();
        readTransactionsWithCriteriaAPIPaging();
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

    private static void readTransactionsWithCriteriaAPI() {
        logger.info("Reading transactions ...");

        EntityManager entityManager = JPAUtil.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = builder.createQuery(Transaction.class);

        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(root);

        criteriaQuery.orderBy(builder.asc(root.get("TITLE")));

        TypedQuery<Transaction> query = entityManager.createQuery(criteriaQuery);

        List<Transaction> transactionList = query.getResultList();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }

    }

    private static void readTransactionsWithCriteriaAPIShort() {
        logger.info("Reading transactions > 250 and <= 1000 short ...");

        EntityManager entityManager = JPAUtil.getEntityManager();

        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> cr = b.createQuery(Transaction.class);
        Root<Transaction> root = cr.from(Transaction.class);

        TypedQuery<Transaction> query = entityManager.createQuery(cr
                .select(root)
                .where(b.and(b.gt(root.get("AMOUNT"), 250), b.le(root.get("AMOUNT"), 1000)))
                .orderBy(b.asc(root.get("TITLE"))));

        List<Transaction> transactionList = query.getResultList();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }
    }

    private static void readTransactionsWithCriteriaAPIPaging() {
        logger.info("Reading transactions with paging ...");

        int pageNumber = 3;
        int pageSize = 4;

        EntityManager entityManager = JPAUtil.getEntityManager();

        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> cr = b.createQuery(Transaction.class);
        Root<Transaction> root = cr.from(Transaction.class);

        TypedQuery<Transaction> query = entityManager.createQuery(cr
                .select(root));

        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        List<Transaction> transactionList = query.getResultList();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }
    }
}
