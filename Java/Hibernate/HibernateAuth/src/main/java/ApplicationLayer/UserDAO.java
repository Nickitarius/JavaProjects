package ApplicationLayer;

import Model.*;
import lombok.SneakyThrows;
import org.hibernate.Session;

import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public void save(User user) {
        session.beginTransaction();
        //Query query = session.createQuery();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    public void save(Role role) {
        session.beginTransaction();
        //Query query = session.createQuery();
        session.saveOrUpdate(role);
        session.getTransaction().commit();
    }

    public void save(Right right) {
        session.beginTransaction();
        //Query query = session.createQuery();
        session.saveOrUpdate(right);
        session.getTransaction().commit();
    }

    public User getUser(Integer id) {
        //session.beginTransaction();
        Query query = session.createQuery("select u from User u where u.id=:id").setParameter("id", id);
        return (User) query.getSingleResult();
    }

    public List<User> findUserByName(String name) {
        Query query = session.createQuery("select u from User u where u.name = :name").setParameter("name", name);
        return query.getResultList();
    }

    @SneakyThrows
    public User findUser(String login, String pass) {
        Query query = session.createQuery("select u from User u where u.login=:login and u.password=:pass")
                .setParameter("login", login).setParameter("pass", User.getHash(pass));
        //System.out.println("\n---"++"---\n");
        return (User) query.getSingleResult();
    }

    public List<Role> readAllRoles() {
        Query query = session.createQuery("select r from Role r");
        return query.getResultList();
    }

    public List<Right> readAllRights() {
        Query query = session.createQuery("select r from Role r");
        return query.getResultList();
    }

    public List<User> readAllUsers() {
        Query query = session.createQuery("select u from User u");
        return query.getResultList();
    }

    public Session getSession() {
        return this.session;
    }
}
