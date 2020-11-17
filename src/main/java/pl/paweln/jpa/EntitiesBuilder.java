package pl.paweln.jpa;

import pl.paweln.jpa.entities.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class EntitiesBuilder {
    public static Bank createBank(String name) {
        Bank bank = new Bank();
        bank.setName(name);
        bank.setAddressLine1("Bagrowa");
        bank.setAddressLine2("82/32");
        bank.setCity("Kraków");
        bank.setState("MA");
        bank.setZipCode("30725");
        bank.setLastUpdatedBy("paweln");
        bank.setLastUpdatedDate(new Date());
        bank.setCreatedBy("paweln");
        bank.setCreatedDate(new Date());

        return bank;
    }

    public static Currency createCurrency (String countryName, String name, String symbol) {
        Currency currency = new Currency();
        currency.setCountryName(countryName);
        currency.setNAME(name);
        currency.setSYMBOL(symbol);
        return currency;
    }

    public static User createUser(String firstName, String lastName) {
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

    public static Account createAccount(String name) {
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

    public static Transaction createTransaction(Account account, long amount, String title) {
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

    private static Date getMyBirthdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1975, Calendar.MAY, 30);
        return calendar.getTime();
    }
}
