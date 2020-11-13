package pl.paweln.jpa;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HibernateUtil {

    private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    private static final SessionFactory sessionFactory =
            buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    private static SessionFactory buildSessionFactory() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        try {
            SessionFactory sessionFactory;
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            return sessionFactory;
        } catch (Exception e) {
            logger.error("An error occurred during creation the session", e);
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("An error occurred during creation the session");
        }

    }

}
