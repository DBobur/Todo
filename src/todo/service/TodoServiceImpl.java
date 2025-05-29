package todo.service;

import todo.model.Todo;
import todo.model.TodoStatus;

import java.util.ArrayList;
import java.util.UUID;

public class TodoServiceImpl implements TodoService{

    ArrayList<Todo> todos = new ArrayList<>();

    @Override
    public int addTodo(Todo todo) {
        if(checkTodo(todo)) return -1;
        todos.add(todo);
        return 1;
    }

    private boolean checkTodo(Todo todo) {
        for (Todo todo1 : todos) {
            if(todo1.getTitle().equals(todo.getTitle())
            && todo1.getUserId().equals(todo.getUserId())) return true;
        }
        return false;
    }

    @Override
    public Todo getTodoById(UUID id) {
        for (Todo todo : todos) {
            if (todo.getId().equals(id)) return todo;
        }
        return null;
    }

    @Override
    public ArrayList<Todo> getTodosByUserId(UUID userId) {
        ArrayList<Todo> userTodos = new ArrayList<>();
        for (Todo todo : todos) {
            if(todo.getUserId().equals(userId)
            && todo.getStatus() != TodoStatus.COMPLETED) userTodos.add(todo);
        }
        return userTodos;
    }

    @Override
    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
