package pl.paweln.infiniteskills.simple.data;

import org.hibernate.SessionFactory;
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
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory
                    (new StandardServiceRegistryBuilder().build());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occured during creation the session");
        }

    }

}
