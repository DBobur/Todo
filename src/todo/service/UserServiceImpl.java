package todo.service;

import todo.model.User;
import todo.model.UserRole;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    ArrayList<User> users = new ArrayList<>();


    @Override
    public int register(User user) {
        if(checkUsername(user.getUsername())) return -1;
        users.add(user);
        return 1;
    }

    private boolean checkUsername(String username) {
        for (User user : users) {
            if(user.getUsername().equals(username)) return true;
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        for (User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

    @Override
    public ArrayList<User> getUsersByRole(UserRole role) {
        ArrayList<User> usersByRole = new ArrayList<>();
        for (User user : users) {
            if(user.getRole() == role) usersByRole.add(user);
        }
        return usersByRole;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

}
