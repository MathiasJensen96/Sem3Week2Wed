package dat.jpademo.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mathiasjensen
 */
public class Tester {
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("Mathias", 1996);
        Person p2 = new Person("Simon", 1993);
        
        Address a1 = new Address("Holsteinsgade 66", 2100, "København Ø");
        Address a2 = new Address("Sofievej 5", 2800, "Kgs. Lyngby");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        
        System.out.println("p1: " + p1.getP_id() + ", " + p1.getName());
        System.out.println("p2: " + p2.getP_id() + ", " + p2.getName());
        
        
        System.out.println("Simons gade: " + p2.getAddress().getStreet());
        
        System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());
    }
}