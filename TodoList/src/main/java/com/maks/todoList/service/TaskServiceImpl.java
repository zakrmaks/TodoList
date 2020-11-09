package com.maks.todoList.service;

import com.maks.todoList.entity.Task;
import com.maks.todoList.repository.TaskRepository;
import com.maks.todoList.search.TaskSearchValues;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public ResponseEntity<Task>  findById(Long id) {

        Task task = null;
        try {

            task = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task> add(Task task) {
        // check getid because autoincrement of id in DB
        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @Override
    public ResponseEntity<Task>  update(Task task) {
        if (task.getId() == null && task.getId() == 0) {
            return new ResponseEntity("id CAN'T be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null && task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @Override
    public ResponseEntity deleteById(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Page<Task>> search(TaskSearchValues taskSearchValues) {
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

        return ResponseEntity.ok(taskRepository.findByParams(text, completed, priorityId, categoryId, pageRequest));
    }
}
