package com.maks.todoList.service;

import com.maks.todoList.entity.Stat;
import com.maks.todoList.repository.StatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class StatServiceImplTest {
    @Mock
    StatRepository statRepository;

    @InjectMocks
    StatServiceImpl statService;
    @Mock
    List<Stat> stats;


    @Test
    void findAll() {
        when(statRepository.findAllByOrderById()).thenReturn(stats);
        assertNotNull(statService.findAll());
    }
}