package com.maks.todoList.controller;


import com.maks.todoList.entity.Priority;

import com.maks.todoList.search.PrioritySearchValues;
import com.maks.todoList.service.PriorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

         return  priorityService.findById(id);

    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        return priorityService.add(priority);
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {

        return priorityService.update(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
       return priorityService.deleteById(id);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues) {

        //if empty - return all values
        return priorityService.search(prioritySearchValues);
    }
}
