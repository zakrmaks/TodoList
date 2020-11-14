package com.maks.todoList.controller;

import com.maks.todoList.entity.Task;
import com.maks.todoList.search.TaskSearchValues;
import com.maks.todoList.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;

import javax.print.attribute.standard.PageRanges;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    TaskService taskService;
    @InjectMocks
    TaskController taskController;

    TaskSearchValues taskSearchValues;
    @Mock
    List<Task> taskList;
    @Mock
    List<Task> taskList1;
    @Mock
    Task task;
    @Mock
    Task task1;
    @Mock
    Page<Task> tasksPages;

    @BeforeEach
    void setUp(){
        taskSearchValues= new TaskSearchValues("test", 12, 21L,13L,10,5,"12","12");
    }

    @Test
    void findAll() {
        when(taskService.findAll()).thenReturn(taskList);
        assertEquals(taskList, taskController.findAll());
    }

    @Test
    void findById() {
        when(taskService.findById(any(Long.class))).thenReturn(Optional.of(task));
        assertEquals(Optional.of(task), taskController.findById(12L));
    }

    @Test
    void add() {
        when(taskService.add(task)).thenReturn(task);
        assertEquals(task, taskController.add(task));
    }

    @Test
    void update() {
        when(taskService.update(task)).thenReturn(task);
        assertEquals(task, taskController.update(task));
    }



    @Test
    void search() {
        when(taskService.search(any(String.class), any(Integer.class),
                any(Long.class), any(Long.class),
                any(PageRequest.class))).thenReturn(tasksPages);
        assertEquals(tasksPages, taskController.search(taskSearchValues));
    }
}