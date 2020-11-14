package com.maks.todoList.service;

import com.maks.todoList.entity.Task;
import com.maks.todoList.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Task>    findById(Long id) {

        return taskRepository.findById(id);
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
    public Page<Task> search(String text,Integer completed ,
                             Long priorityId ,Long categoryId,
                              PageRequest pageRequest  ) {

        Page<Task> tasks = taskRepository.findByParams(text, completed, priorityId, categoryId, pageRequest);

        return tasks;
    }
}
