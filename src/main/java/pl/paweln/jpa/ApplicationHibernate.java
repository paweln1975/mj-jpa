package pl.paweln.jpa;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.*;
import pl.paweln.jpa.entities.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;

public class ApplicationHibernate {
    private static Logger logger = LoggerFactory.getLogger(ApplicationHibernate.class);
    public static void main(String[] args) {

        logger.info("Starting...");

        deleteBank();
        readAndUpdateBank();


        operateOnUser();
        operateOnTime();
        operateOnBank();
        operateOnCredential();
        operateOnAccount();
        operateOnBudget();
        operateOnAccountUser();
        operateOnUserAccount();

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

        Address address = new Address();
        address.setAddressLine1("Bagrowa");
        address.setAddressLine2("82/32");
        address.setCity("Warszawa");
        address.setZipCode("00000");
        address.setState("MA");

        User user = EntitiesBuilder.createUser("Pawel", "Niedziela");

        user.setAddress(address);

        Address address1 = new Address();
        address1.setAddressLine1("Lanowa");
        address1.setAddressLine2("43C/27");
        address1.setCity("Kraków");
        address1.setZipCode("00001");
        address1.setState("ML");

        user.getAddressList().add(address);
        user.getAddressList().add(address1);

        logger.info("Before save: session contains object:" + session.contains(user));

        session.save(user);

        logger.info("After save: session contains object:" + session.contains(user));

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

        Bank bank = EntitiesBuilder.createBank("mBank");


        bank.getContacts().put("CASHIER", "Emilka");
        bank.getContacts().put("MANAGER", "Pawel");


        session.save(bank);

        session.getTransaction().commit();
        session.close();

    }


    private static void operateOnCredential() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();


        User user = EntitiesBuilder.createUser("Emilka", "Niedziela");

        Credential credential = new Credential();
        credential.setUserName("emilka");
        credential.setPassword("password");
        credential.setUser(user);

        user.setCredential(credential);

        session.save(credential);

        session.getTransaction().commit();

        User dbUser = session.get(User.class, user.getUserId());

        logger.info("User created: " + dbUser.getFirstName() + " " + dbUser.getLastName() + " " + dbUser.getCredential().getUserName());
        session.close();
    }

    private static void operateOnAccount() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Account account = EntitiesBuilder.createAccount("Normal", AccountType.SAVINGS);

        account.getTransactionsList().add(EntitiesBuilder.createTransaction(account,10, "Shoes"));
        account.getTransactionsList().add(EntitiesBuilder.createTransaction(account, 20, "Books"));
        session.save(account);

        session.getTransaction().commit();

        Account dbAccount = session.get(Account.class, account.getAccountId());

        logger.info("Account created: " + dbAccount.getAccountId() + " " + dbAccount.getAccountType() + " " + dbAccount.getCurrentBalance());
        session.close();
    }



    private static void operateOnBudget() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Budget budget = new Budget();
        budget.setNAME("Budget Test");
        budget.setPERIOD("MONTH");
        budget.setGoalAmount(BigDecimal.valueOf(100));

        Account account = EntitiesBuilder.createAccount("Budget Normal", AccountType.CHECKING);

        budget.getTransactionList().add(EntitiesBuilder.createTransaction(account, 150, "Hat"));
        budget.getTransactionList().add(EntitiesBuilder.createTransaction(account, 200, "Trousers"));

        session.save(budget);
        session.getTransaction().commit();
        session.close();
    }

    private static void operateOnAccountUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user1 = EntitiesBuilder.createUser("Robot1", "Bob");
        User user2 = EntitiesBuilder.createUser("Robot2", "Arisa");

        Account account1 = EntitiesBuilder.createAccount("UserAccount1", AccountType.SAVINGS);
        Account account2 = EntitiesBuilder.createAccount("UserAccount2", AccountType.CHECKING);

        account1.getUsers().add(user1);
        account1.getUsers().add(user2);
        account2.getUsers().add(user1);
        account2.getUsers().add(user2);

        session.save(account1);
        session.save(account2);

        session.getTransaction().commit();

        Account dbAccount = session.get(Account.class, account1.getAccountId());
        logger.info("Account created: " + dbAccount.getAccountId() + " " + dbAccount.getAccountType() + " " + dbAccount.getCurrentBalance());

        for (User user : dbAccount.getUsers()) {
            logger.info("User: " + user.getEmailAddress());
        }
        dbAccount = session.get(Account.class, account2.getAccountId());
        logger.info("Account created: " + dbAccount.getAccountId() + " " + dbAccount.getAccountType() + " " + dbAccount.getCurrentBalance());

        session.close();


    }

    private static void operateOnUserAccount() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user1 = EntitiesBuilder.createUser("Robot1", "Bob");
        User user2 = EntitiesBuilder.createUser("Robot2", "Arisa");

        Account account1 = EntitiesBuilder.createAccount("UserAccount1", AccountType.SAVINGS);
        Account account2 = EntitiesBuilder.createAccount("UserAccount2", AccountType.CHECKING);

        user1.getAccounts().add(account1);
        user1.getAccounts().add(account2);

        user2.getAccounts().add(account1);
        user2.getAccounts().add(account2);

        account1.getUsers().add(user1);
        account1.getUsers().add(user2);

        account2.getUsers().add(user1);
        account2.getUsers().add(user2);

        session.save(user1);
        session.save(user2);

        session.getTransaction().commit();

        User dbUser = session.get(User.class, user1.getUserId());
        logger.info("User created: " + dbUser.getEmailAddress());

        for (Account account : dbUser.getAccounts()) {
            logger.info("Account: " + account.getAccountId() + " " + account.getNAME() + " " + account.getAccountType());
        }

        dbUser = session.get(User.class, user2.getUserId());
        logger.info("User created: " + dbUser.getEmailAddress());

        session.close();

    }

    private static void readAndUpdateBank() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Bank bank = session.get(Bank.class, 1L);
        logger.info("readBank(): " + bank.getName());

        bank.setName("New Hope Bank");
        bank.setLastUpdatedBy("paweln");
        bank.setLastUpdatedDate(new Date());

        session.getTransaction().commit();

        bank = session.load(Bank.class, 1L);
        logger.info("readBank(): CHANGED: " + bank.getName());


        session.close();
    }

    private static void deleteBank() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        try {

            Bank bank = session.load(Bank.class, 2L);

            if (bank != null) {
                logger.info("deleteBank(): " + bank.getName());

                if (session.contains(bank)) {
                    session.delete(bank);
                }

                logger.info("deleteBank(): session contains: " + session.contains(bank));
                session.flush();
            }

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            logger.error("Exception - deleteBank()", e);

        } finally {
            session.close();
        }


    }

}
