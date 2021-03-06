package com.maks.todoList.service;

import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> findAll();
    Optional<Task> findById(Long id);
    Task   add(Task task);
    Task    update(Task task);
    void deleteById(Long id);
    Page<Task> search(String text,Integer completed ,
                      Long priorityId ,Long categoryId,
                      PageRequest pageRequest);
}
