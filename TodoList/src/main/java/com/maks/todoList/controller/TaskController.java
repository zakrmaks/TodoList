package com.maks.todoList.controller;


import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import com.maks.todoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //    return list of categories sorted by title
    @GetMapping("/all")
    public List<Task> findAll() {
        return taskService.findAll();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        Task task = null;
        try {
            task = taskService.findById(id);
        }  catch (
    NoSuchElementException e) {
        return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        // check getid because autoincrement of id in DB
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Task task) {
        if (task.getId() == null && task.getId() == 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return  ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // search for different param taskSearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
        //exclude NullPointerException or others variables;

        return   ResponseEntity.ok(taskService.search(taskSearchValues));
    }
}
