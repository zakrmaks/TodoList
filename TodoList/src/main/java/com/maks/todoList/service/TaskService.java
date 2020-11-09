package com.maks.todoList.service;

import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<Task> findAll();
    ResponseEntity<Task>  findById(Long id);
    ResponseEntity<Task> add(Task task);
    ResponseEntity<Task>  update(Task task);
    ResponseEntity deleteById(Long id);
    ResponseEntity<Page<Task>> search(TaskSearchValues taskSearchValues);
}
