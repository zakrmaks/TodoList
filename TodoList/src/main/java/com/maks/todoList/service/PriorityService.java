package com.maks.todoList.service;


import com.maks.todoList.entity.Category;
import com.maks.todoList.entity.Priority;
import com.maks.todoList.search.CategorySearchValues;
import com.maks.todoList.search.PrioritySearchValues;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PriorityService {
    List<Priority> findAll();
    ResponseEntity<Priority> findById(Long id);
    ResponseEntity<Priority> add(Priority priority);
    ResponseEntity<Priority> update(Priority priority);
    ResponseEntity deleteById(Long id);
    ResponseEntity<List<Priority>> search(PrioritySearchValues prioritySearchValues);
}
