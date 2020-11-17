package com.maks.todoList.controller;

import com.maks.todoList.dto.OutNullParam;
import com.maks.todoList.dto.WithNullParam;
import com.maks.todoList.entity.Priority;
import com.maks.todoList.search.PrioritySearchValues;
import com.maks.todoList.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/priorities")
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


    @GetMapping("/{id}")
    public Optional<Priority> findById(@PathVariable Long id) {

        return priorityService.findById(id);

    }

    @PostMapping("/add")
    public Priority add(@Validated(WithNullParam.class) @RequestBody Priority priority) {

//        if (priority.getId() != null && priority.getId() != 0){
//            return new ResponseEntity("id MUST be null", HttpStatus.ACCEPTED);
//        }
//        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0){
//            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
//        }
//        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
//            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
//        }
        return priorityService.add(priority);
    }

    @PutMapping("/update")
 //TODO: Validation method
    public Priority update(@Validated({OutNullParam.class}) @RequestBody Priority priority) {

//        if (priority.getId() == null && priority.getId() == 0) {
//            return new ResponseEntity("id MUST be not null", HttpStatus.NOT_ACCEPTABLE);
//        }
//        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0){
//            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
//        }
//        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
//            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);

        return priorityService.update(priority);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {

//        try {
        priorityService.deleteById(id);
//    }catch (
//    EmptyResultDataAccessException e){
//        return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
//    }
//        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Priority> search(@RequestBody PrioritySearchValues prioritySearchValues) {

        //if empty - return all values
        return priorityService.search(prioritySearchValues);
    }
}
