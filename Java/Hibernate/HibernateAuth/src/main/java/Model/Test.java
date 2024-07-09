package Model;

import ApplicationLayer.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.Set;
import java.util.TreeSet;


/*Нерелевантный и неэлегантный класс.*/
@Deprecated
public class Test {
    public static void main(String[] args) {

        //Rights
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Right right1 = new Right();
        right1.setRights("Gym Access");
        session.save(right1);

        Right right2 = new Right();
        right2.setRights("Dungeon Access");
        session.save(right2);

        Right right3 = new Right();
        right3.setRights("Disciplinary Action");
        session.save(right3);

        Right right4 = new Right();
        right4.setRights("Supplies access");
        session.save(right4);

        Right right5 = new Right();
        right5.setRights("Interrogation");
        session.save(right5);

        session.getTransaction().commit();


        //Roles

        session.beginTransaction();
        Role role1 = new Role();
        role1.setTitle("Dungeon Master");
        Set<Right> sr1 = new TreeSet<Right>();
        sr1.add(right2);
        sr1.add(right3);
        sr1.add(right5);
        role1.setAccessList(sr1);
        System.out.println("\n---" + sr1 + "---\n");
        session.save(role1);

        Role role2 = new Role();
        role2.setTitle("Boss of the Gym");
        Set<Right> sr2 = new TreeSet<Right>();
        sr2.add(right1);
        sr2.add(right4);
        sr2.add(right3);
        role2.setAccessList(sr2);
        session.save(role2);

        session.getTransaction().commit();

        //Users
        session.beginTransaction();

        User u1 = new User();
        u1.setLogin("Jabroni");
        u1.setPassword("LeatherMan113");
        u1.setName("Van Darkholme");
        u1.setRole(role1);
        session.save(u1);

        User u2 = new User();
        u2.setLogin("SmartAce");
        u2.setPassword("RingSolution");
        u2.setName("Mark Wolf");
        u2.setRole(role2);
        session.save(u2);

        session.getTransaction().commit();

        //session.beginTransaction();

        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        //EntityManager entityManager = emf.createEntityManager();
        // entityManager.getTransaction().begin();
        Query query = session.createQuery("select r " + "from Role r" + " where r.id = :id1 ").setParameter("id1", 1);


        //Role r = session.get(Role.class, 1);

        Role r = (Role) query.getSingleResult();
        System.out.println();
        for (Right rr : r.getAccessList()) {
            System.out.println("---" + rr.getRights() + "---");
        }
        System.out.println();

        session.close();
        System.out.println("\n---Done.---\n");
        System.exit(0);
    }
}