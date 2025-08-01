package com.example.yonotebook.repository;
import com.example.yonotebook.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;
public interface TodoItemRepository extends CrudRepository<TodoItem, Integer>{
}
