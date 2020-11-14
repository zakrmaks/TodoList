package com.maks.todoList.service;


import com.maks.todoList.entity.Priority;
import com.maks.todoList.repository.PriorityRepository;
import com.maks.todoList.search.PrioritySearchValues;
import org.junit.jupiter.api.BeforeEach;
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
class PriorityServiceImplTest {
    @Mock
    PriorityRepository priorityRepository;
    @InjectMocks
    PriorityServiceImpl priorityService;

    //Create tests Class fot TDD
    @Mock
    Priority priorityTest1;
    @Mock
    Priority priorityTest2;
    @Mock

    List<Priority> testList;
    @Mock
    List<Priority> testList2;
    @Mock
    PrioritySearchValues prioritySearchValues;
    @BeforeEach
    void setUp() {

    }

    @Test
    void findAll() {
        when(priorityRepository.findAllByOrderByIdAsc()).thenReturn(testList);
        assertEquals(testList, priorityService.findAll());
    }

    @Test
    void findById() {
        when(priorityRepository.findById(any(Long.class))).thenReturn(Optional.of(priorityTest1));
        assertEquals(Optional.of(priorityTest1), priorityRepository.findById(16L));
    }

    @Test
    void add() {
        when(priorityRepository.save(any(Priority.class))).thenReturn(priorityTest1);
        assertEquals(priorityTest1, priorityService.add(priorityTest1));
    }

    @Test
    void update() {
        when(priorityRepository.save(any(Priority.class))).thenReturn(priorityTest1);
        assertEquals(priorityTest1, priorityService.update(priorityTest1));
    }

    @Test
    void search() {
        when(priorityRepository.findByTitle(prioritySearchValues.getPriorityParam())).thenReturn(testList);
        assertEquals(testList , priorityService.search(prioritySearchValues));
    }
}