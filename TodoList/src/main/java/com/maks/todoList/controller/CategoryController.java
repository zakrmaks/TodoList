package com.maks.todoList.controller;

import com.maks.todoList.entity.Category;

import com.maks.todoList.search.CategorySearchValues;
import com.maks.todoList.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        return ResponseEntity.ok(categoryService.findAll());
    }



    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = null;
        try {
            category = categoryService.findById(id);
        }catch (NoSuchElementException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        // check getid because autoincrement of id in DB
return  ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
    }catch (
    EmptyResultDataAccessException e){
        return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
    }
        return new ResponseEntity(HttpStatus.OK);

    }

    // search for different param CategorySearchValues
@PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){

      return  ResponseEntity.ok(categoryService.search(categorySearchValues));
}
}
