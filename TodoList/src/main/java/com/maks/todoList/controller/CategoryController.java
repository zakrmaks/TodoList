package com.maks.todoList.controller;


import com.maks.todoList.entity.Category;
import com.maks.todoList.search.CategorySearchValues;
import com.maks.todoList.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")

public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController( CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //    return list of categories
    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryService.findAll();
    }


    @GetMapping("/{id}")

    public Optional<Category> findById (@PathVariable Long id)  {

        return categoryService.findById(id);
    }


    @PostMapping("/add")
    public Category add( @RequestBody Category category) {
        // check getid because autoincrement of id in DB
        return categoryService.add(category);
    }

    @PutMapping("/update")
    public Category update(@Valid @RequestBody Category category) {

        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")

    public void deleteById(@PathVariable Long id) {

        categoryService.deleteById(id);



    }

    // search for different param CategorySearchValues
    @PostMapping("/search")
    public List<Category> search(@RequestBody CategorySearchValues categorySearchValues) {

        return categoryService.search(categorySearchValues);
    }
}
