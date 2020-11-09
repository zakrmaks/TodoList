package com.maks.todoList.service;

import com.maks.todoList.entity.Category;
import com.maks.todoList.repository.CategoryRepository;
import com.maks.todoList.search.CategorySearchValues;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<Category>>  findAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @Override
    public ResponseEntity<Category> findById(Long id) {

        Category category = null;
        try {
            category = categoryRepository.findById(id).get();
        }catch (NoSuchElementException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(category);
    }

    @Override
    public ResponseEntity<Category> add(Category category) {

        // check getid because autoincrement of id in DB
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @Override
    public ResponseEntity<Category>  update(Category category) {
        if (category.getId() == null && category.getId() == 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null && category.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // search for different param CategorySearchValues
    @Override
    public ResponseEntity<List<Category>> search(CategorySearchValues categorySearchValues) {
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
    }
}
