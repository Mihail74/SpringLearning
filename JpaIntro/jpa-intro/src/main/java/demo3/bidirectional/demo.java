package demo3.bidirectional;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class demo {

    public static void main(String[] args) throws Exception {
        Server.createWebServer().start();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo3-bidirectional");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Employee e1 = new Employee("John", "Doe");
        e1.addPhone(Phone.PhoneType.WORK, "555-555-555");
        e1.addPhone(Phone.PhoneType.HOME, "123-456-789");

        em.persist(e1);

        em.getTransaction().commit();

        Employee e = em.find(Employee.class, 1);
        System.out.println(e.getFirstName() + " " + e.getLastName());
        e.getPhones().forEach( p -> System.out.println(p.getPhoneType() + " : " + p.getPhoneNumber()) );

    }
}
