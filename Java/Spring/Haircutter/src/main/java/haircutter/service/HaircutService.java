package haircutter.service;

import haircutter.HibernateUtil;
import haircutter.model.HaircutCase;
import haircutter.model.HaircutType;
import haircutter.model.Master;
import haircutter.model.Option;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service("haircutService")
public class HaircutService {

    private Session session;


    public HaircutService() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<HaircutType> getAllHaircutTypes() {
        Query query = session.createQuery("select t from HaircutType t");
        return query.getResultList();
    }

    public List<Master> getAllMasters() {
        Query query = session.createQuery("select m from Master m");
        return query.getResultList();
    }

    public List<Option> getAllOptions() {
        Query query = session.createQuery("select o from Option  o");
        return query.getResultList();
    }

    public List<HaircutCase> getAllHaircutCases() {
        Query query = session.createQuery("select c from HaircutCase c");
        return query.getResultList();
    }

    public void saveTransaction(HaircutCase haircutCase) {
        session.beginTransaction();
        session.save(haircutCase);
        session.getTransaction().commit();
    }
}
