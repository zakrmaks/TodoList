package com.maks.todoList.controller;

import com.maks.todoList.entity.Priority;
import com.maks.todoList.search.PrioritySearchValues;
import com.maks.todoList.service.PriorityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriorityControllerTest {
    @Mock
    PriorityService priorityService;
    @InjectMocks
    PriorityController priorityController;
    @Mock
    Priority priority;
    @Mock
    Priority priority2;
    @Mock
    List<Priority> priorityList;
    @Mock
    List<Priority> priorityList2;
    @Mock
    PrioritySearchValues prioritySearchValues;

    @Test
    void findAll() {
        when(priorityService.findAll()).thenReturn(priorityList);
        assertEquals(priorityList, priorityController.findAll());
    }

    @Test
    void findById() {
        when(priorityService.findById(any(Long.class))).thenReturn(Optional.of(priority));
        assertEquals(Optional.of(priority), priorityController.findById(12L));
    }

    @Test
    void add() {
        when(priorityService.add(any(Priority.class))).thenReturn(priority);
        assertEquals(priority, priorityController.add(priority));
    }

    @Test
    void update() {
        when(priorityService.update(any(Priority.class))).thenReturn(priority);
        assertEquals(priority, priorityController.update(priority));
    }


    @Test
    void search() {
        when(priorityService.search(any(PrioritySearchValues.class))).thenReturn(priorityList);
        assertEquals(priorityList, priorityController.search(prioritySearchValues));
    }
}