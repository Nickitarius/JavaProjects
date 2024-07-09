package delo.service;

import delo.HibernateUtil;
import delo.model.Tag;
import delo.model.Zadacha;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;


@Service("zadachaService")
public class ZadachaService {

    private Session session;


    public ZadachaService() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<Tag> getAllTags() {
        Query query = session.createQuery("select t from Tag t");
        return query.getResultList();
    }

    public List<Zadacha> getAllZadachas() {
        Query query = session.createQuery("select z from Zadacha z");
        return query.getResultList();
    }

    public Tag getTagById(Integer id) {
        //Query query = session.createQuery("select t from Tag t where t.id = :id").setParameter("id", id);
        Tag tag = (Tag) session.get(Tag.class, id);
        return tag;
    }

    public List<Zadacha> getZadachasToday() {
        Query query = session.createQuery("select z from Zadacha z");
        LinkedList<Zadacha> res = new LinkedList<>();
        for (Object z : query.getResultList()) {
            if (((Zadacha) z).getDate().equals(LocalDate.now())) {
                res.add((Zadacha) z);
            }
        }
        return res;
    }

    public List<Zadacha> getZadachasWeek() {
        Query query = session.createQuery("select z from Zadacha z");
        LinkedList<Zadacha> res = new LinkedList<>();
        // Go backward to get Monday
        LocalDate monday = LocalDate.now();
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }
        // Go forward to get Sunday
        LocalDate sunday = LocalDate.now();
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.plusDays(1);
        }
        for (Object z : query.getResultList()) {
            LocalDate temp = ((Zadacha) z).getDate();
            if ((temp.isBefore(sunday) && temp.isAfter(monday)) || temp.isEqual(sunday) || temp.isEqual(monday)) {
                res.add((Zadacha) z);
            }
        }
        return res;
    }

    public List<Zadacha> getZadachasMonth() {
        Query query = session.createQuery("select z from Zadacha z");
        LinkedList<Zadacha> res = new LinkedList<>();
        for (Object z : query.getResultList()) {
            LocalDate temp = ((Zadacha) z).getDate();
            if ((temp.getMonthValue() == LocalDate.now().getMonthValue())
                    && (temp.getYear() == LocalDate.now().getYear())
            ) {
                res.add((Zadacha) z);
            }
        }
        return res;
    }

    public List<Zadacha> getZadachasYear() {
        Query query = session.createQuery("select z from Zadacha z");
        LinkedList<Zadacha> res = new LinkedList<>();
        for (Object z : query.getResultList()) {
            LocalDate temp = ((Zadacha) z).getDate();
            if (temp.getYear() == LocalDate.now().getYear()) {
                res.add((Zadacha) z);
            }
        }
        return res;
    }

    public List<Zadacha> getZadachasByTag(Tag tag) {
        Query query = session.createQuery("select z from Zadacha z where z.tag=:tag").setParameter("tag", tag);
        return query.getResultList();
    }

    public void saveTransaction(Zadacha zadacha) {
        session.beginTransaction();
        session.save(zadacha);
        session.getTransaction().commit();
    }
}
