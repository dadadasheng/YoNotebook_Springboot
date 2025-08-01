package com.example.yonotebook.controller;

import com.example.yonotebook.entity.TodoItem;
import com.example.yonotebook.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller // This means that this class is a Controller
@RequestMapping(path="/todoItem")
public class TodoItemController {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addTodoItem(@RequestParam String todo_text, @RequestParam int staus) {
        return "save";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }
}
