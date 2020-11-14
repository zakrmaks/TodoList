package com.maks.todoList.controller;


import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import com.maks.todoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<Task> findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping("/add")
    public Task add(@RequestBody Task task) {

        return taskService.add(task);
    }

    @PutMapping("/update")
    public Task update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    // search for different param taskSearchValues
    @PostMapping("/search")
    public Page<Task> search(@RequestBody TaskSearchValues taskSearchValues) {
        //exclude NullPointerException or others variables;
        String text = taskSearchValues.getText() != null ? taskSearchValues.getText() : null;
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;

        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;


        String sortColumn = taskSearchValues.getSortColumn() != null? taskSearchValues.getSortColumn() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null? taskSearchValues.getSortColumn() : null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null? taskSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().equals("Asc")? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);
        PageRequest pageRequest = PageRequest.of(pageSize, pageNumber, sort);
        Page<Task> tasks = taskService.search(text, completed, priorityId, categoryId, pageRequest);
        return tasks;
    }
}
