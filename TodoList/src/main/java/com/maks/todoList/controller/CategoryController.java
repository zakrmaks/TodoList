package com.maks.todoList.controller;

import com.maks.todoList.entity.Category;
import com.maks.todoList.entity.Priority;
import com.maks.todoList.repository.CategoryRepository;
import com.maks.todoList.search.CategorySearchValues;
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
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    return list of categories
    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }



    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
Category category = null;
        try {
            category = categoryRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        // check getid because autoincrement of id in DB
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {

        try {
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // search for different param CategorySearchValues
@PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){

        //if empty - return all values
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
}
}
