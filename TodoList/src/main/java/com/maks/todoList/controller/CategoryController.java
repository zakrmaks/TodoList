package com.maks.todoList.controller;

import com.maks.todoList.entity.Category;
import com.maks.todoList.search.CategorySearchValues;
import com.maks.todoList.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //    return list of categories
    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        return categoryService.findAll();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        return categoryService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {

        return categoryService.deleteById(id);
    }

    // search for different param CategorySearchValues
    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {

        //if empty - return all values
        return categoryService.search(categorySearchValues);
    }
}
