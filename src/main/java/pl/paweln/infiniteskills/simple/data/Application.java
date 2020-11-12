package pl.paweln.infiniteskills.simple.data;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.infiniteskills.simple.data.entities.User;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info("Starting...");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setFirstName("Pawel");
        user.setLastName("Niedziela");
        user.setBirthDate(new Date());

        user.setEmailAddress("pawel.niedziela@gmail.com");

        user.setLastUpdatedDate(new Date());
        user.setLastUpdatedBy("pawel");
        user.setCreateDate(new Date());
        user.setCreatedBy("pawel");

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
