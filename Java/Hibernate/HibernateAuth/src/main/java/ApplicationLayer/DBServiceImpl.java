package ApplicationLayer;

import Model.Right;
import Model.Role;
import Model.User;
import org.hibernate.Session;

import java.util.List;

public class DBServiceImpl implements DBService {

    private UserDAO userDAO;


    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void save(Role role) {
        userDAO.save(role);
    }

    @Override
    public void save(Right right) {
        userDAO.save(right);
    }

    @Override
    public User getUser(Integer id) {
        return userDAO.getUser(id);
    }

    @Override
    public List<User> findUserByName(String name) {
        return userDAO.findUserByName(name);
    }

    @Override
    public User findUser(String login, String pass) {
        return userDAO.findUser(login, pass);
    }

    @Override
    public List<Role> readAllRoles() {
        return userDAO.readAllRoles();
    }

    @Override
    public List<Right> readAllRights() {
        return userDAO.readAllRights();
    }

    @Override
    public List<User> readAllUsers() {
        return userDAO.readAllUsers();
    }

    @Override
    public void openConnection() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        this.userDAO = new UserDAO(session);
    }

    @Override
    public void closeConnection() {
        userDAO.getSession().close();
    }
}
