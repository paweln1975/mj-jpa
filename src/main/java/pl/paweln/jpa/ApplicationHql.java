package pl.paweln.jpa;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Transaction;

import java.util.List;

public class ApplicationHql {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationHql.class);

    public static void main(String[] args) {
        readTransactions();
    }

    private static void readTransactions() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Query<Transaction> query = session.createQuery("select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = query.list();

        for (Transaction transaction : transactionList) {
            logger.info(transaction.getTITLE());
        }
        session.getTransaction().commit();
    }
}
