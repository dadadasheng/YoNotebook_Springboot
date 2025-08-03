package com.example.yonotebook.controller;

import com.example.yonotebook.entity.TodoItem;
import com.example.yonotebook.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@CrossOrigin("*")
@Controller // This means that this class is a Controller
@RequestMapping(path="/todoItem")
public class TodoItemController {
    @Autowired
    private TodoItemRepository todoItemRepository;

    @PostMapping(path = "/add")
    public @ResponseBody TodoItem addTodoItem(@RequestParam String todo_text, @RequestParam int status, @RequestParam int year, @RequestParam int month, @RequestParam int day) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTodoText(todo_text);
        todoItem.setStatus(status);
        LocalDate localDate = LocalDate.of(year, month, day);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        todoItem.setcreateTime(date);
        todoItemRepository.save(todoItem);
        return todoItem;
    }
    //edit todo item
    //del todo item
    //update todo item Status
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }
    @GetMapping(path = "/create_time")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsByCreateTime(@RequestParam int year, @RequestParam int month, @RequestParam int day) throws ParseException {
        String startStr = String.format("%04d-%02d-%02d 00:00:00.000", year, month, day);
        String endStr = String.format("%04d-%02d-%02d 00:00:00.000", year, month, day + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date start = sdf.parse(startStr);
        Date end = sdf.parse(endStr);
        return todoItemRepository.findTodoItemsByCreateTimeBetween(start, end);}

    @PostMapping(path="/updateToDoItemContent")
    public @ResponseBody String updateToDoItemContent(@RequestParam String todo_text, @RequestParam int Id) {
        TodoItem todoItem = todoItemRepository.findById(Id).orElseThrow();
        todoItem.setTodoText(todo_text);
        todoItemRepository.save(todoItem);
        return "update content";
    }

    @PostMapping(path="/updateToDoItemStatus")
    public @ResponseBody String updateToDoItemStatus(@RequestParam int status, @RequestParam int Id) {
        TodoItem todoItem = todoItemRepository.findById(Id).orElseThrow();
        todoItem.setStatus(status);
        todoItemRepository.save(todoItem);
        return "update status";
    }

}
