package com.simpleSBApps.webboilerplate.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.simpleSBApps.webboilerplate.models.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "Zalman", "Learn Prophecy", new Date(), false));
        todos.add(new Todo(2, "Zalman", "Leave Corinth", new Date(), false));
        todos.add(new Todo(3, "Zalman", "Kill Laios", new Date(), false));
        todos.add(new Todo(4, "Zalman", "Defeat Sphinx", new Date(), false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate,
                        boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public Todo getTodoById(int id) {
        return todos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void updateTodo(Todo todo) {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).equals(todo)) {
                todos.set(i, todo);
                break;
            }
        }
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}