package org.example;


import org.example.classes.mapping.one_to_many.Guide;
import org.example.classes.mapping.one_to_many.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p1");
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();



            txn.commit();
        } catch (Exception e) {
            if (txn != null)
                txn.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void createMultipleGuides(EntityManager em) {
        for (int i = 1; i <= 30; i++) {
            Guide guide = new Guide();
            guide.setName("Guide " + i);
            Person person = new Person();
            person.setName("Person " + i);

            person.setGuide(guide);

            em.persist(guide);
        }
    }
}
