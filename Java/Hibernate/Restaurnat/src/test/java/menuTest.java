import menuPackage.HibernateUtil;
import menuPackage.Menu;
import menuPackage.MenuItem;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class menuTest {

    @Before
    public void beforeMethod() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Menu menu = new Menu();
        menu.setTitle("menuTest2");
        //menu.addItem(friedPotato);
        //menu.addItem(tequila);

        session.save(menu);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void newMenu() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        System.out.println("\n---TEST: Creating new menu.---\n");

        MenuItem friedPotato = new MenuItem();
        friedPotato.setName("Fried Potato");
        friedPotato.setPrice(Double.valueOf(100));
        friedPotato.setCalories(Integer.valueOf(300));
        friedPotato.setCarbohydrates(Integer.valueOf(15));
        friedPotato.setFat(Integer.valueOf(80));
        friedPotato.setProtein(Integer.valueOf(5));
        session.save(friedPotato);

        MenuItem tequila = new MenuItem();
        tequila.setName("Tequila");
        tequila.setPrice(Double.valueOf(1000));
        tequila.setCalories(Integer.valueOf(150));
        tequila.setCarbohydrates(Integer.valueOf(0));
        tequila.setFat(Integer.valueOf(0));
        tequila.setProtein(Integer.valueOf(0));
        session.save(tequila);

        Menu menu = new Menu();
        menu.setTitle("menuTest");
        menu.addItem(friedPotato);
        menu.addItem(tequila);

        session.save(menu);
        session.getTransaction().commit();

        //Menu menu = session.get();


        System.out.println("-------NEW MENU---------------");
        assertEquals(menu, session.get(Menu.class, menu.getId()));

        session.close();
    }

    @Test
    public void addItem() {
        System.out.println("\n---Test: Adding new item to pre-existing menu.---\n");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        MenuItem omelette = new MenuItem();
        omelette.setName("Omelette with bacon");
        omelette.setPrice(Double.valueOf(120));
        omelette.setCalories(Integer.valueOf(250));
        omelette.setCarbohydrates(Integer.valueOf(30));
        omelette.setFat(Integer.valueOf(70));
        omelette.setProtein(Integer.valueOf(20));
        //session.save(omelette);

        //System.out.println("\n---AAAAAAAAAAAAAAAAAAAA ---\n");

        Menu menu = (Menu) session.get(Menu.class, 1L);
        //System.out.println("\n---MENUUUUUUUUUUUUUUU: " + menu.getTitle() + " ---\n");
        System.out.println("---------------ADD ITEM---------------------------");
        menu.addItem(omelette);


        //session.save(omelette);
        session.saveOrUpdate(menu);
        //session.save(omelette);
        session.getTransaction().commit();

        assertEquals(omelette, session.get(MenuItem.class, omelette.getId()));
        //assertEquals(omelette.getMenu(), session.get(Menu.class,menu.getId()));

        session.close();
    }
}
