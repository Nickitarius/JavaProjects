package ApplicationLayer;

import Model.*;

import java.util.List;

public interface DBService {

    public void save(User user);

    public void save(Role role);

    public void save(Right right);

    public User getUser(Integer id);

    public List<User> findUserByName(String name);

    public User findUser(String login, String pass);

    public List<Role> readAllRoles();

    public List<Right> readAllRights();

    public List<User> readAllUsers();

    public void openConnection();

    public void closeConnection();

}
