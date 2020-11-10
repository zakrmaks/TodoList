package com.maks.todoList.controller;

import com.maks.todoList.entity.Priority;

import com.maks.todoList.search.PrioritySearchValues;
import com.maks.todoList.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/priority")
public class PriorityController {
    private PriorityService priorityService;


    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    //return list of priorities sorted by id
    @GetMapping("/all")
    public List<Priority> findAll() {
        return priorityService.findAll();

    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        Priority priority = null;
        try {
         priorityService.findById(id);
    } catch (
    NoSuchElementException e) {
        return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }
        return ResponseEntity.ok(priority);
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority){
        if (priority.getId() != null && priority.getId() != 0){
            return new ResponseEntity("id MUST be null", HttpStatus.ACCEPTED);
        }
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }
return ResponseEntity.ok(priorityService.add(priority));
    }
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Priority priority) {

        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("id MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }return ResponseEntity.ok(priorityService.update(priority));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {

        try {
         priorityService.deleteById(id);
    }catch (
    EmptyResultDataAccessException e){
        return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
    }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues){

        //if empty - return all values
        return ResponseEntity.ok(priorityService.search(prioritySearchValues));
    }
}
