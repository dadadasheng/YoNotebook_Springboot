package com.example.yonotebook.repository;
import com.example.yonotebook.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer>{
    List<TodoItem> findTodoItemsByCreateTime(Date createTime);
    List<TodoItem> findTodoItemsByCreateTimeBetween(Date startDate, Date endDate);
    TodoItem findTodoItemById(int id);
}
