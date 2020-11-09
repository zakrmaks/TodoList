package com.maks.todoList.controller;


import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import com.maks.todoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return taskService.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {

        return taskService.add(task);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Task task) {
        return  taskService.update(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        return taskService.deleteById(id);
    }

    // search for different param taskSearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {
      return   taskService.search(taskSearchValues);
    }
}
