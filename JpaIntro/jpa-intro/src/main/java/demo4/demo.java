package demo4;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class demo {

    public static void main(String[] args) throws Exception {
        Server.createWebServer().start();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo4");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Project p1 = new Project("project 1");
        Project p2 = new Project("project 2");
        Employee e1 = new Employee("John", "Doe");

        p1.assignEmployee(e1);
        p2.assignEmployee(e1);

        em.persist(p1);
        em.persist(p2);

        em.getTransaction().commit();

        Employee e = em.find(Employee.class, 2);
        System.out.println(e.getFirstName() + " " + e.getLastName());
        e.getProjects().forEach( p -> System.out.println(p.getName()) );

    }
}
