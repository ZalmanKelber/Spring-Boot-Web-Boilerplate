package com.simpleSBApps.webboilerplate.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.simpleSBApps.webboilerplate.models.Todo;
import com.simpleSBApps.webboilerplate.services.TodoRepository;
import com.simpleSBApps.webboilerplate.services.TodoService;
import com.simpleSBApps.webboilerplate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String getListTodos(ModelMap model) {
        String name = userService.getCurrentUser();
        List<Todo> todos = todoService.retrieveTodos(name);
        System.out.println(todos);
        model.put("todos", todos);
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String getAddTodo(ModelMap model) {
        model.addAttribute("todo", new Todo(UUID.randomUUID(),
                                                        userService.getCurrentUser(),
                                                        "Add description",
                                                        new Date(),
                                                        false));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String getUpdateTodo(ModelMap model, @RequestParam UUID id) {
        System.out.println("todo to update has id " + id);
        model.addAttribute("todo", todoService.getTodoById(id));
        System.out.println("found todo to update: " + todoService.getTodoById(id));
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String getDeleteTodo(@RequestParam UUID id) {

        System.out.println("todo to delete has id " + id);
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String postAddTodo(@Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }

        Todo saved = todoRepository.save(todo);
        System.out.println("saved todo with id " + saved.getId());
//        todoService.addTodo(userService.getCurrentUser(), todo.getDesc(), new Date(),
//                false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String postUpdateTodo(@Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }
        todo.setUser(userService.getCurrentUser());
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }
}
