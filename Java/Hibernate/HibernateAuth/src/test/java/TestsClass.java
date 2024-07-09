import ApplicationLayer.DBServiceImpl;
import Model.Right;
import Model.Role;
import Model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TestsClass {

    @Before
    public void creationTest() {
        DBServiceImpl service = new DBServiceImpl();

        service.openConnection();
        //Rights
        //List<Right> LR = new ArrayList<Right>();
        Right right1 = new Right();
        right1.setRights("Gym Access");
        service.save(right1);
        //LR.add(right1);
        Right right2 = new Right();
        right2.setRights("Dungeon Access");
        service.save(right2);
        //LR.add(right2);
        Right right3 = new Right();
        right3.setRights("Disciplinary Action");
        service.save(right3);
        //LR.add(right3);
        Right right4 = new Right();
        right4.setRights("Supplies access");
        service.save(right4);
        //LR.add(right4);
        Right right5 = new Right();
        right5.setRights("Interrogation");
        service.save(right5);
        //LR.add(right5);

        //Roles
        Role role1 = new Role();
        role1.setTitle("Dungeon Master");
        Set<Right> sr1 = new TreeSet<Right>();
        sr1.add(right2);
        sr1.add(right3);
        sr1.add(right5);
        role1.setAccessList(sr1);
        System.out.println("\n---" + sr1 + "---\n");
        service.save(role1);
        Role role2 = new Role();
        role2.setTitle("Boss of the Gym");
        Set<Right> sr2 = new TreeSet<Right>();
        sr2.add(right1);
        sr2.add(right4);
        sr2.add(right3);
        role2.setAccessList(sr2);
        service.save(role2);

        //Users
        User u1 = new User();
        u1.setLogin("Jabroni");
        u1.setPassword("LeatherMan113");
        u1.setName("Van Darkholme");
        u1.setRole(role1);
        service.save(u1);
        User u2 = new User();
        u2.setLogin("SmartAce");
        u2.setPassword("RingSolution");
        u2.setName("Mark Wolf");
        u2.setRole(role2);
        service.save(u2);
        User u3 = new User();
        u3.setLogin("Aniki");
        u3.setName("Billy Herrington");
        u3.setPassword("HowDoYouLikeThat?");
        u3.setRole(role2);
        service.save(u3);

        System.out.println("\n---\n" + service.readAllRights() + "\n---\n");
        System.out.println("\n---\n" + service.readAllRoles() + "\n---\n");
        System.out.println("\n---\n" + service.readAllUsers() + "\n---\n");

//        System.out.println("\n---"+service.findUserByName("Billy Herrington")+"---\n");
//        System.out.println("\n---"+service.getUser(1)+"---\n");

//        Scanner in = new Scanner(System.in);
//        System.out.println("\n---Enter your login ---\n");
//        String login = in.next();
//        System.out.println("\n---Enter your password ---\n");
//        String password = in.next();


        service.closeConnection();
        //List<Right> LR2 = service.readAllRights();
    }

    @Test
    public void AuthTest() {
        DBServiceImpl service = new DBServiceImpl();
        service.openConnection();
        Scanner in = new Scanner(System.in);
        System.out.println("\n---Enter your login ---\n");
        String login = in.nextLine();
        System.out.println("\n---Enter your password ---\n");
        String password = in.nextLine();
        try {
            User u = (User) service.findUser(login, password);
            if (u != null) {
                System.out.println("\n---Ах, добре чоловiче, погнали! Вот твои права:---\n");
                for (Right r : u.getRole().getAccessList()) {
                    System.out.println("\n---" + r.getRights() + "---\n");
                }
            }
        } catch (Exception e) {
            System.out.println("\n---Гей, чоловiче, я вважаю ти помилився аккаунтами." +
                    " Твiй аккаунт двома повирхами нижче.---\n");
        }
        service.closeConnection();

    }
}