package todo.service;

import todo.model.Todo;

import java.util.ArrayList;
import java.util.UUID;

public interface TodoService {
    int addTodo(Todo todo);
    Todo getTodoById(UUID id);
    ArrayList<Todo> getTodosByUserId(UUID userId);
    ArrayList<Todo> getTodos();
}
