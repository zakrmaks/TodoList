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
    public Task    findById(Long id) {

        return taskRepository.findById(id).get();
    }

    @Override
    public Task   add(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public Task    update(Task task) {

        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {

            taskRepository.deleteById(id);


    }

    @Override
    public Page<Task> search(TaskSearchValues taskSearchValues) {
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

        return taskRepository.findByParams(text, completed, priorityId, categoryId, pageRequest);
    }
}
