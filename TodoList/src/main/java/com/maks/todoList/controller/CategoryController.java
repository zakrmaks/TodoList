package com.maks.todoList.controller;

import com.maks.todoList.entity.Category;
import com.maks.todoList.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryRepository categoryRepository;

@Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<Category> test() {

        List<Category> list = categoryRepository.findAll();
        return list;
    }

    @PostMapping("/add")
    public Category add(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
