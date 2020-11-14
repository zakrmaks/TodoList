package com.maks.todoList.service;

import com.maks.todoList.entity.Task;
import com.maks.todoList.repository.TaskRepository;
import com.maks.todoList.search.TaskSearchValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    TaskRepository taskRepository;
    @InjectMocks
    TaskServiceImpl taskService;

    //Create class for TDD
    @Mock
    Task testTask;
    @Mock
    Task testTask2;

    @Mock
    List<Task> taskList;
    @Mock
    List<Task> taskList2;
    @Mock
    TaskSearchValues taskSearchValues;
    @Mock
    Page<Task> tasks;
    @Mock
    Page<Task> tasks2;

    @Test
    void findAll() {
        when(taskRepository.findAll()).thenReturn(taskList);
        assertEquals(taskList, taskService.findAll());
    }

    @Test
    void findById() {
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(testTask));
        assertEquals(Optional.of(testTask), taskService.findById(15L));
    }

    @Test
    void add() {
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);
        assertEquals(testTask, taskService.add(testTask));
    }

    @Test
    void update() {
        when(taskRepository.save(any(Task.class))).thenReturn(testTask);
        assertEquals(testTask, taskService.update(testTask));
    }


    @Test
    void search() {
        when(taskRepository.findByParams(any(String.class), any(Integer.class),
                any(Long.class), any(Long.class),
                any(PageRequest.class))).
                thenReturn(tasks);
        assertEquals(tasks, taskService.search("15", 19,
                13L, 12L,
                PageRequest.of(14,16, Sort.by("14", "12"))));
    }
}