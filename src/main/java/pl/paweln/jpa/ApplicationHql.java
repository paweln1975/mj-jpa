package pl.paweln.jpa;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Account;
import pl.paweln.jpa.entities.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class ApplicationHql {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationHql.class);

    public static void main(String[] args) {
        readTransactions();
        readDeposits();

    }

    private static void readTransactions() {
        logger.info("Reading transactions ...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query<Transaction> query = session.createQuery("select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = query.list();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    private static void readDeposits() {
        logger.info("Reading deposits ...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        //Query<Account> query = session.createQuery(
        //        "", Account.class);
        Query<Account> query = session.getNamedQuery("Account.largeDeposit");
        query.setParameter("amount", BigDecimal.valueOf(500));

        List<Account> accountList = query.list();
        logger.info("After query execution.");
        for (Account account : accountList) {
            logger.info("Account: " + account.getNAME() + " Bank:" + account.getBank().getName());
        }
        session.getTransaction().commit();
    }


}
