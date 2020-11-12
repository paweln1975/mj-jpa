package pl.paweln.infiniteskills.simple.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.paweln.infiniteskills.simple.data.entities.User;

public class HibernateUtil {

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
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException("An error occurred during creation the session");
        }

    }

}
