package pl.paweln.infiniteskills.simple.data;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info("Starting...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.close();

    }
}
