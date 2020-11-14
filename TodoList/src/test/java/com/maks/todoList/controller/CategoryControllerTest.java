package com.maks.todoList.controller;

import com.maks.todoList.entity.Category;
import com.maks.todoList.search.CategorySearchValues;
import com.maks.todoList.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;


    //test's fields for TDD
@Mock
    List<Category> categories;
@Mock
    List<Category> categories1;
@Mock
    Category category;
@Mock
    Category category1;
@Mock
    CategorySearchValues categorySearchValues;

    @Test
    void findAll() {
        when(categoryService.findAll()).thenReturn(categories);
        assertEquals(categories, categoryController.findAll());

    }

    @Test
    void findById() {
        when(categoryService.findById(any(Long.class))).thenReturn(Optional.of(category));
        assertEquals(Optional.of(category), categoryController.findById(13L));
    }

    @Test
    void add() {
        when(categoryService.add(any(Category.class))).thenReturn(category);
        assertEquals(category, categoryController.add(category));
    }

    @Test
    void update() {
        when(categoryService.update(any(Category.class))).thenReturn(category);
        assertEquals(category, categoryController.update(category));
    }



    @Test
    void search() {
        when(categoryService.search(categorySearchValues)).thenReturn(categories);
        assertEquals(categories, categoryController.search(categorySearchValues));
    }
}