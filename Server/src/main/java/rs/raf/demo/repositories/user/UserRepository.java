package rs.raf.demo.repositories.user;

import rs.raf.demo.entities.User;

import java.util.List;

public interface UserRepository {
    User addUser(User user);
    List<User> allUsers();
    List<User> pageUsers(Integer page, Integer limit);
    User findUser(Integer id);
    User findUserByEmail(String email);
    User editUser(User user);
    void deleteUser(Integer id);
    void setUserStatus(Integer id);
}
