package pl.paweln.jpa;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.paweln.jpa.entities.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        logger.info("Starting...");

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

        User user = createUser("Pawel", "Niedziela");

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

    private static void operateOnCredential() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setFirstName("Emilka");
        user.setLastName("Niedziela");
        user.setBirthDate(getMyBirthdate());

        user.setEmailAddress("emilka.niedziela@gmail.com");

        user.setLastUpdatedDate(new Date());
        user.setLastUpdatedBy("pawel");
        user.setCreateDate(new Date());
        user.setCreatedBy("pawel");

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

        Account account = createAccount("Normal");

        account.getTransactionsList().add(createTransaction(account,10, "Shoes"));
        account.getTransactionsList().add(createTransaction(account, 20, "Books"));
        session.save(account);

        session.getTransaction().commit();

        Account dbAccount = session.get(Account.class, account.getAccountId());

        logger.info("Account created: " + dbAccount.getAccountId() + " " + dbAccount.getAccountType() + " " + dbAccount.getCurrentBalance());
        session.close();
    }

    private static User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(getMyBirthdate());

        user.setEmailAddress(firstName + "." + lastName + "@gmail.com");

        user.setLastUpdatedDate(new Date());
        user.setLastUpdatedBy("pawel");
        user.setCreateDate(new Date());
        user.setCreatedBy("pawel");

        return user;
    }

    private static Account createAccount(String name) {
        Account account = new Account();
        account.setAccountType(name);
        account.setCreatedBy("pawel");
        account.setCreatedDate(new Date());
        account.setOpenDate(new Date());
        account.setCloseDate(new Date());
        account.setCurrentBalance(BigDecimal.valueOf(0));
        account.setInitialBalance(BigDecimal.valueOf(0));
        account.setLastUpdatedDate(new Date());
        account.setLastUpdatedBy("pawel");

        return account;
    }

    private static Transaction createTransaction(Account account, long amount, String title) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAMOUNT(BigDecimal.valueOf(amount));
        transaction.setCreatedBy("pawel");
        transaction.setCreatedDate(new Date());
        transaction.setLastUpdatedDate(new Date());
        transaction.setLastUpdatedBy("pawel");
        transaction.setClosingBalance(BigDecimal.valueOf(0));
        transaction.setInitialBalance(BigDecimal.valueOf(0));
        transaction.setTITLE(title);
        transaction.setTransactionType("Purchase");

        return transaction;
    }

    private static void operateOnBudget() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Budget budget = new Budget();
        budget.setNAME("Budget Test");
        budget.setPERIOD("MONTH");
        budget.setGoalAmount(BigDecimal.valueOf(100));

        Account account = createAccount("Budget Normal");

        budget.getTransactionList().add(createTransaction(account, 150, "Hat"));
        budget.getTransactionList().add(createTransaction(account, 200, "Trousers"));

        session.save(budget);
        session.getTransaction().commit();
        session.close();
    }

    private static void operateOnAccountUser() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user1 = createUser("Robot1", "Bob");
        User user2 = createUser("Robot2", "Arisa");

        Account account1 = createAccount("UserAccount1");
        Account account2 = createAccount("UserAccount2");

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

        User user1 = createUser("Robot1", "Bob");
        User user2 = createUser("Robot2", "Arisa");

        Account account1 = createAccount("UserAccount1");
        Account account2 = createAccount("UserAccount2");

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
            logger.info("Account: " + account.getAccountId());
        }

        dbUser = session.get(User.class, user2.getUserId());
        logger.info("User created: " + dbUser.getEmailAddress());

        session.close();


    }
}
