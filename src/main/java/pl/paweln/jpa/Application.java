package pl.paweln.jpa;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.Address;
import pl.paweln.jpa.entities.Bank;
import pl.paweln.jpa.entities.TimeTest;
import pl.paweln.jpa.entities.User;

import java.util.Calendar;
import java.util.Date;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        logger.info("Starting...");

        operateOnUser();
        operateOnTime();
        operateOnBank();

        logger.info("Application completed.");
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
        user.setBirthDate(getMyBirthdate());

        user.setEmailAddress("pawel.niedziela@gmail.com");

        user.setLastUpdatedDate(new Date());
        user.setLastUpdatedBy("pawel");
        user.setCreateDate(new Date());
        user.setCreatedBy("pawel");

        Address address = new Address();
        address.setAddressLine1("Bagrowa");
        address.setAddressLine2("82/32");
        address.setCity("Warszawa");
        address.setZipCode("00000");
        address.setState("MA");

        user.setAddress(address);

        Address address1 = new Address();
        address1.setAddressLine1("Lanowa");
        address1.setAddressLine2("43C/27");
        address1.setCity("Kraków");
        address1.setZipCode("00001");
        address1.setState("ML");

        user.getAddressList().add(address);
        user.getAddressList().add(address1);

        session.save(user);

        session.getTransaction().commit();

        session.refresh(user);

        logger.info("I'm " + user.getAge() + " years old.");

//        session.beginTransaction();
//        User newUser = session.get(User.class, user.getUserId());
//        newUser.setFirstName("Emilia");
//
//        session.update(newUser);
//
//        session.getTransaction().commit();
        session.close();
    }

    public static void operateOnBank() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Bank bank = new Bank();
        bank.setName("mBank");
        bank.setAddressLine1("Bagrowa");
        bank.setAddressLine2("82/32");
        bank.setCity("Kraków");
        bank.setState("MA");
        bank.setZipCode("30725");
        bank.setLastUpdatedBy("paweln");
        bank.setLastUpdatedDate(new Date());
        bank.setCreatedBy("paweln");
        bank.setCreatedDate(new Date());


        bank.getContacts().put("CASHIER", "Emilka");
        bank.getContacts().put("MANAGER", "Pawel");


        session.save(bank);

        session.getTransaction().commit();
        session.close();

    }
    private static Date getMyBirthdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1975, Calendar.MAY, 30);
        return calendar.getTime();
    }
}
