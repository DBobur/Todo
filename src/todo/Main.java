package todo;

import todo.model.Todo;
import todo.model.TodoStatus;
import todo.model.User;
import todo.model.UserRole;
import todo.service.TodoService;
import todo.service.TodoServiceImpl;
import todo.service.UserService;
import todo.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scNum = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);

    static UserService userService = new UserServiceImpl();
    static TodoService todoService = new TodoServiceImpl();

    static User currentUser = null;

    public static void main(String[] args) {
        startTodo();
    }

    private static void startTodo() {
        while (true){
            System.out.print("""
                    1.login
                    2.register
                    0.exact
               """);

            System.out.print("Enter your choice: ");
            switch(scNum.nextInt()){
                case 0 -> {return;}
                case 1 -> {myLogin();}
                case 2 -> {myRegister();}
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void myRegister() {
        //todo: register method
        System.out.print("Enter your full name: ");
        String fullName = scStr.nextLine();
        System.out.print("Enter your username: ");
        String username = scStr.nextLine();
        System.out.print("Enter your password: ");
        String password = scStr.nextLine();

        User user = new User(fullName, username, password, UserRole.USER_ROLE);
        if(userService.register(user) == 1){
            System.out.println("You have successfully registered!");
        }else{
            System.out.println("You have unsuccessfully registered!");
        }
    }

    private static void myLogin() {
        //todo: login method
        System.out.print("Enter your username: ");
        String username = scStr.nextLine();
        System.out.print("Enter your password: ");
        String password = scStr.nextLine();
        currentUser = userService.login(username, password);
        if(currentUser == null){ System.out.println("Invalid username or password");return;}
        System.out.println("You have successfully logged in!\nWelcome " + currentUser.getFullName());

        switch(currentUser.getRole()){
            case USER_ROLE -> {userMenu();}
            case ADMIN_ROLE -> {adminMenu();}
        }
    }

    private static void adminMenu() {
        //todo: admin menu
        while(true){
            System.out.print("""
                    1.show users
                    2.block users
                    3.unblock users
                    4.delete users
                    0.logout
               """);
            System.out.print("Enter your choice: ");
            switch(scNum.nextInt()){
                case 1 -> {showUsers();}
                case 2 -> {blockUsers();}
                case 3 -> {unblockUsers();}
                case 4 -> {deleteUsers();}
                case 0 -> {return;}
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void deleteUsers() {
        //todo delete users
    }

    private static void unblockUsers() {
        //todo: un block users
    }

    private static void blockUsers() {
        //todo: block users
    }

    private static void showUsers() {
        //todo: show users
    }

    private static void userMenu() {
        //todo: user menu
        while(true){
            System.out.print("""
                    1.add todo
                    2.show todos
                    3.update todos
                    4.show history
                    0.logout
               """);
            System.out.print("Enter your choice: ");
            switch(scNum.nextInt()){
                case 0 -> {return;}
                case 1 -> {addTodo();}
                case 2 -> {showTodos();}
                case 3 -> {updateTodos();}
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void updateTodos() {
        //todo: update todos
        ArrayList<Todo> todos = showTodos();
        if(todos.isEmpty()){
            System.out.println("Empty");
            return;
        }

        System.out.print("Enter todo number: ");
        int index = scNum.nextInt()-1;
        Todo todo = todos.get(index);
        System.out.print("1/0 -> " +
                (todo.getStatus() == TodoStatus.CREATED ? "Make Beginning / Back":"Make Completing / Back"));
        int choice = scNum.nextInt();
        if(choice == 1){
            if(todo.getStatus() == TodoStatus.CREATED) todo.setStatus(TodoStatus.BEGINNING);
            else todo.setStatus(TodoStatus.COMPLETED);
        }
        else if(choice == 0){return;}
        else System.out.println("Invalid choice");

    }

    private static ArrayList<Todo> showTodos() {
        //todo: show todos
        int i = 1;
        ArrayList<Todo> todosByUserId = todoService.getTodosByUserId(currentUser.getId());
        for (Todo todo : todosByUserId) {
            System.out.println(i++ + ". " + todo.todoInfo());
        }
        return todosByUserId;
    }

    private static void addTodo() {
        //todo: add todo
        System.out.print("Enter title: ");
        String title = scStr.nextLine();
        System.out.print("Enter description: ");
        String description = scStr.nextLine();
        Todo todo = new Todo(currentUser.getId(),title,description);
        if(todoService.addTodo(todo) == 1){
            System.out.println("added successfully!");
        }else {
            System.out.println("add failed!");
        }
    }
}