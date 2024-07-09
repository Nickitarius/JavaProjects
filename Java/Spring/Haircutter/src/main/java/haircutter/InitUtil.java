package haircutter;

import haircutter.model.HaircutCase;
import haircutter.model.HaircutType;
import haircutter.model.Master;
import haircutter.model.Option;
import haircutter.service.HaircutService;
import org.hibernate.Session;

/*Заполняет БД данными о мастерах, видах стрижек и т.д.*/
@Deprecated
public class InitUtil {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        Master master1 = new Master();
//        master1.setName("Van Darkholme");
//        session.save(master1);
//
//        Master master2 = new Master();
//        master2.setName("Billy Herrington");
//        session.save(master2);
//
//        Master master3 = new Master();
//        master3.setName("Danny Lee");
//        session.save(master3);
//
//        session.getTransaction().commit();
//        session.beginTransaction();
//
//        HaircutType h1 = new HaircutType();
//        h1.setName("Fade");
//        h1.setPrice(300);
//        session.save(h1);
//
//        HaircutType h2 = new HaircutType();
//        h2.setName("Undercut");
//        h2.setPrice(150);
//        session.save(h2);
//
//        HaircutType h3 = new HaircutType();
//        h3.setName("Pompadour");
//        h3.setPrice(400);
//        session.save(h3);
//
//        HaircutType h4 = new HaircutType();
//        h4.setName("Quiff");
//        h4.setPrice(350);
//        session.save(h4);
//
//        HaircutType h5 = new HaircutType();
//        h5.setName("Faux Hawk");
//        h5.setPrice(300);
//        session.save(h5);
//
//        session.getTransaction().commit();
//        session.beginTransaction();
//
//        Option o1 = new Option();
//        o1.setName("Shower");
//        // o1.setPrice(50);
//        session.save(o1);
//
//        Option o2 = new Option();
//        o2.setName("Barbing");
//        //o1.setPrice(100);
//        session.save(o2);
//
//
//        session.getTransaction().commit();
//
//        HaircutService s = new HaircutService();
//        System.out.println("------" + s.getAllHaircutTypes() + "-----------");

//        session.beginTransaction();
//
//        HaircutCase hc = new HaircutCase();
//        hc.setMaster(master1);
//        hc.setType(h1);
//        hc.setTotalPrice(h1.getPrice());
//        session.save(hc);
//        HaircutService s = new HaircutService();
//        s.saveTransaction(hc);

        session.getTransaction().commit();
        session.close();

        System.exit(0);
    }
}
