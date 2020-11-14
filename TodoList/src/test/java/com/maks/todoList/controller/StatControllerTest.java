package com.maks.todoList.controller;

import com.maks.todoList.entity.Stat;
import com.maks.todoList.service.StatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatControllerTest {
    @Mock
    StatService statService;
    @InjectMocks
    StatController statController;
    @Mock
    List<Stat> list;
    @Mock
    List<Stat> list2;



    @Test
    void findAll() {
        when(statService.findAll()).thenReturn(list);
        assertEquals(list, statController.findAll());
    }
}