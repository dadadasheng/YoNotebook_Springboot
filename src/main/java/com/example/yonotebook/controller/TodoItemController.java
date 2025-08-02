package com.example.yonotebook.controller;

import com.example.yonotebook.entity.TodoItem;
import com.example.yonotebook.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin("*")
@Controller // This means that this class is a Controller
@RequestMapping(path="/todoItem")
public class TodoItemController {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addTodoItem(@RequestParam String todo_text, @RequestParam int status) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTodoText(todo_text);
        todoItem.setStatus(status);
        Date date = new Date();
        todoItem.setcreateTime(date);
        todoItemRepository.save(todoItem);
        return "save";
    }
    //edit todo item
    //del todo item
    //update todo item Status
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }
    @GetMapping(path = "/create_time")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsByCreateTime(@RequestParam String year, @RequestParam String month, @RequestParam String day) throws ParseException {
        Date d = new Date();
        return todoItemRepository.findTodoItemsByCreateTimeBetween(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(year+"-"+month+"-"+day+" 00:00:00.000000"),new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(year+"-"+month+"-"+(Integer.parseInt(day)+1)+" 00:00:00.000000"));
    }

    @GetMapping(path="/updateToDoItemContent")
    public @ResponseBody String updateToDoItemContent(@RequestParam String todo_text, @RequestParam int Id) {
        TodoItem todoItem = todoItemRepository.findById(Id).orElseThrow();
        todoItem.setTodoText(todo_text);
        todoItemRepository.save(todoItem);
        return "update content";
    }

    @GetMapping(path="/updateToDoItemStatus")
    public @ResponseBody String updateToDoItemStatus(@RequestParam int status, @RequestParam int Id) {
        TodoItem todoItem = todoItemRepository.findById(Id).orElseThrow();
        todoItem.setStatus(status);
        todoItemRepository.save(todoItem);
        return "update status";
    }
}
