package menuPackage;

import org.hibernate.Session;

import java.util.*;

public class MenuDemo {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        MenuItem friedPotato = new MenuItem();
//        friedPotato.setName("Fried Potato");
//        friedPotato.setPrice(Double.valueOf(100));
//        friedPotato.setCalories(Integer.valueOf(300));
//        friedPotato.setCarbohydrates(Integer.valueOf(15));
//        friedPotato.setFat(Integer.valueOf(80));
//        friedPotato.setProtein(Integer.valueOf(5));
//        session.save(friedPotato);
//
//        MenuItem tequila = new MenuItem();
//        tequila.setName("Tequila");
//        tequila.setPrice(Double.valueOf(1000));
//        tequila.setCalories(Integer.valueOf(150));
//        tequila.setCarbohydrates(Integer.valueOf(0));
//        tequila.setFat(Integer.valueOf(0));
//        tequila.setProtein(Integer.valueOf(0));
//        session.save(tequila);
//
//        MenuItem omelette = new MenuItem();
//        omelette.setName("Omelette with bacon");
//        omelette.setPrice(Double.valueOf(120));
//        omelette.setCalories(Integer.valueOf(250));
//        omelette.setCarbohydrates(Integer.valueOf(30));
//        omelette.setFat(Integer.valueOf(70));
//        omelette.setProtein(Integer.valueOf(20));
//        session.save(omelette);
//
//        Menu menu1 = new Menu();
//        menu1.setTitle("menu1");
//        menu1.addItem(friedPotato);
//        menu1.addItem(tequila);
//
//        session.save(menu1);
//        session.getTransaction().commit();
//
//        session.beginTransaction();
//
//        Menu menu2 = new Menu();
//        menu2.setTitle("menu2");
//        menu2.addItem(tequila);
//        menu2.addItem(omelette);
//
//
//        session.save(menu2);
//        session.save(tequila);
//        session.save(omelette);
//        session.save(friedPotato);
//        session.getTransaction().commit();

        //System.out.println(session.get(Menu.class, 1l).getTitle());

        session.close();
        System.out.println("---OK---");
        System.exit(0);
    }
}
