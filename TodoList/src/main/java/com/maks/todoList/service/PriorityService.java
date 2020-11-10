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
    Priority  findById(Long id);
    Priority  add(Priority priority);
    Priority  update(Priority priority);
    void deleteById(Long id);
    List<Priority> search(PrioritySearchValues prioritySearchValues);
}
