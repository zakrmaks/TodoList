package com.maks.todoList.controller;


import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import com.maks.todoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {

        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Task task) {
        return  ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
         taskService.deleteById(id);
       return ResponseEntity.ok(HttpStatus.OK);
    }

    // search for different param taskSearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
      return   ResponseEntity.ok(taskService.search(taskSearchValues));
    }
}
