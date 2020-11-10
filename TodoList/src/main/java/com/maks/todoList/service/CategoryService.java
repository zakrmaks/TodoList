package com.maks.todoList.service;

import com.maks.todoList.entity.Category;
import com.maks.todoList.search.CategorySearchValues;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
     List<Category>  findAll();
     Category findById(Long id);
     Category add(Category category);
     Category update(Category category);
     void deleteById(Long id);
     List<Category> search(CategorySearchValues categorySearchValues);
}
