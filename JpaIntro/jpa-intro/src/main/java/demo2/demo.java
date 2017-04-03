package demo2;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class demo {

    public static void main(String[] args) throws Exception {
        Server.createWebServer().start();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo2");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.persist(new Employee("John", "Doe", new Address("Dallas", "Texas")));

        em.getTransaction().commit();

        Employee e = em.find(Employee.class, 1);
        System.out.println(e.getFirstName() + " " + e.getLastName());

        Address a = e.getAddress();

        System.out.println(a.getCity() + " " + a.getState());
    }
}
