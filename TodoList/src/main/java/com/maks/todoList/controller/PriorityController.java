package com.maks.todoList.controller;

import com.maks.todoList.entity.Priority;
import com.maks.todoList.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    private PriorityRepository priorityRepository;

    @Autowired
    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/test")
    public List<Priority> test(){
List<Priority> priorities = priorityRepository.findAll();
return priorities;
    }

    @PostMapping("/add")
    public Priority add(@RequestBody Priority priority){
      return  priorityRepository.save(priority);
    }
}
