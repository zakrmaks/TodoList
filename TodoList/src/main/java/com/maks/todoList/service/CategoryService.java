package com.maks.todoList.service;

import com.maks.todoList.entity.Category;
import com.maks.todoList.search.CategorySearchValues;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
     ResponseEntity<List<Category>>  findAll();
     ResponseEntity<Category> findById(Long id);
     ResponseEntity<Category> add(Category category);
     ResponseEntity<Category>  update(Category category);
     ResponseEntity deleteById(Long id);
     ResponseEntity<List<Category>> search(CategorySearchValues categorySearchValues);
}
