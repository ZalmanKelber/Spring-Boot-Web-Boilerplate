package com.simpleSBApps.webboilerplate.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import com.simpleSBApps.webboilerplate.models.Todo;
import com.simpleSBApps.webboilerplate.services.TodoService;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String getListTodos(ModelMap model) {
        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String getAddTodo(ModelMap model) {
        model.addAttribute("todo", new Todo(0, (String) model.get("name"), "Default Desc",
                new Date(), false));
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String getUpdateTodo(ModelMap model, @RequestParam int id) {
        model.addAttribute("todo", todoService.getTodoById(id));
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String getDeleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String postAddTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }

        todoService.addTodo((String) model.get("name"), todo.getDesc(), new Date(),
                false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String postUpdateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if(result.hasErrors()){
            return "todo";
        }
        todo.setUser((String) model.get("name"));
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }
}
