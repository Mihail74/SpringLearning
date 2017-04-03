package demo5;

import org.apache.commons.lang3.Validate;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class demo {

    public static void main(String[] args) throws Exception {
        Server.createWebServer().start();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo5");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // New state
        Employee e = new Employee("John", "Doe", new Address("Dallas", "Texas"));
        Validate.isTrue(!em.contains(e));

        // Managed
        em.persist(e);
        Validate.isTrue(em.contains(e));

        // Detached
        em.detach(e);
        Validate.isTrue(!em.contains(e));
        Validate.isTrue(e.getId() != 0);

        // em.persist(e); // IllegalArgumentException: try persist detached entity
        // em.remove(e); // IllegalArgumentException: try remove detached entity

        // Managed
        e = em.merge(e);
        Validate.isTrue(em.contains(e));

        // Removed
        em.remove(e);
        // em.merge(e); // IllegalArgumentException: try merge removed entity
        Validate.isTrue(!em.contains(e));

    }
}
