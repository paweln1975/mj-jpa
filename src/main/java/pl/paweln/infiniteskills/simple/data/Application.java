package pl.paweln.infiniteskills.simple.data;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.infiniteskills.simple.data.entities.TimeTest;
import pl.paweln.infiniteskills.simple.data.entities.User;

import java.util.Date;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        logger.info("Starting...");

        operateOnTime();
    }

    private static void operateOnTime() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        TimeTest timeTest = new TimeTest(new Date());
        session.save(timeTest);

        session.refresh(timeTest);

        session.getTransaction().commit();
        session.close();

        logger.info(timeTest.toString());
    }

    private static void operateOnUser() {
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

        session.beginTransaction();
        User newUser = session.get(User.class, user.getUserId());
        newUser.setFirstName("Emilia");

        session.update(newUser);

        session.getTransaction().commit();
        session.close();
    }
}
