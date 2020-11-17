package pl.paweln.jpa;

import pl.paweln.jpa.entities.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ApplicationJPA {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("infinite-finances");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        Bank bank = EntitiesBuilder.createBank("Pekao S.A.");

        entityManager.persist(bank);

        tx.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
