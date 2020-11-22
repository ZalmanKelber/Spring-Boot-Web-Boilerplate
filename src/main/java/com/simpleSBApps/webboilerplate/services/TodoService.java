package com.simpleSBApps.webboilerplate.services;

import java.util.*;
import java.util.stream.Collectors;

import com.simpleSBApps.webboilerplate.models.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(UUID.randomUUID(), "Zalman", "Learn Prophecy", new Date(), false));
        todos.add(new Todo(UUID.randomUUID(), "Zalman", "Leave Corinth", new Date(), false));
        todos.add(new Todo(UUID.randomUUID(), "Zalman", "Kill Laios", new Date(), false));
        todos.add(new Todo(UUID.randomUUID(), "Zalman", "Defeat Sphinx", new Date(), false));
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
        todos.add(new Todo(name, desc, targetDate, isDone));
    }

    public Todo getTodoById(UUID id) {
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public void updateTodo(Todo todo) {
        System.out.println("todos before updateTodo: " + todos);
        todos = todos.stream()
                .map(t -> t.getId().equals(todo.getId()) ? todo : t)
                .collect(Collectors.toList());
        System.out.println("todos after updateTodo: " + todos);
    }

    public void deleteTodo(UUID id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId().equals(id)) {
                iterator.remove();
            }
        }
    }
}