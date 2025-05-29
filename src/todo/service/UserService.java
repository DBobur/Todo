package todo.service;

import todo.model.User;
import todo.model.UserRole;

import java.util.ArrayList;

public interface UserService {
    int register(User user);
    User login(String username, String password);
    ArrayList<User> getUsersByRole(UserRole role);
    ArrayList<User> getUsers();
}
