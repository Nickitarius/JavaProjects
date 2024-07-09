package delo;

import delo.model.Tag;
import delo.model.Zadacha;
import org.hibernate.Session;
import org.springframework.format.datetime.joda.LocalDateParser;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

@Deprecated
public class InitUtil {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Tag tag1 = new Tag();
        tag1.setName("Urgent");
        Tag tag2 = new Tag();
        tag2.setName("Work");
        Tag tag3 = new Tag();
        tag3.setName("Home");
        session.save(tag1);
        session.save(tag2);
        session.save(tag3);
        session.getTransaction().commit();

        /*Tag tag= session.get(Tag.class,2);

        Zadacha z1 = new Zadacha();
        LocalDate ld = LocalDate.now();
        z1.setDate(ld);
        z1.setOpisanie("TEST");
        z1.addTag(tag);
        session.save(z1);
        session.getTransaction().commit();*/


        session.close();

        System.exit(0);
    }
}
