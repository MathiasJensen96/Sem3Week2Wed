package dat.jpademo.entities;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        
        p1.addFee(f1);
        p1.addFee(f3);
        p2.addFee(f2);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Butterfly");
        SwimStyle s3 = new SwimStyle("Breast Stroke");
        
        p1.addSwimStyle(s1);
        p1.addSwimStyle(s3);
        p2.addSwimStyle(s2);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        p1.removeSwimStyle(s3);
        em.getTransaction().commit();
        
        System.out.println("p1: " + p1.getP_id() + ", " + p1.getName());
        System.out.println("p2: " + p2.getP_id() + ", " + p2.getName());
        
        
        System.out.println("Simons gade: " + p2.getAddress().getStreet());
        
        System.out.println("Lad os se om to-vejs virker: " + a1.getPerson().getName());
        
        System.out.println("Hvem har betalt f2? Det har: " + f2.getPerson().getName());
        
        System.out.println("Hvad er der blevet betalt i alt?");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        
        for (Fee f: fees) {
            System.out.println(f.getPerson().getName() + ": " + f.getAmount() + " kr. Den " + f.getPayDate() + "Adr: " + f.getPerson().getAddress().getCity());
        }
    }
}