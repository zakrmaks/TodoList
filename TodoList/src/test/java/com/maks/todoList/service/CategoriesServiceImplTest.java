package com.maks.todoList.service;

import com.maks.todoList.entity.Category;
import com.maks.todoList.repository.CategoryRepository;
import com.maks.todoList.search.CategorySearchValues;
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
class CategoriesServiceImplTest {
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    CategoryServiceImpl categoriesService;
    @Mock
    CategorySearchValues categoriesSearchValues;
    //Created tests class for TDD
    @Mock
    Category category;
    @Mock
    Category category2;
    @Mock
    Category category3;
    @Mock
    List<Category> categories;
    @Mock
    List<Category> categories2;





    @Test
    void findAll() {
        when(categoryRepository.findAll()).thenReturn(categories);

        assertEquals(categories, categoriesService.findAll());

    }

    @Test
    void findById() {
        when(categoriesService.findById(any(Long.class))).thenReturn(Optional.of(category));
        assertEquals(Optional.of(category), categoriesService.findById(1L));
    }

    @Test
    void add() {
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category, categoriesService.add(category));
    }

    @Test
    void update() {
        when(categoryRepository.save(category)).thenReturn(category);
        assertEquals(category, categoriesService.update(category));
    }


    @Test
    void search() {
        when(categoryRepository.findByTitle(categoriesSearchValues.getText())).thenReturn(categories);
        assertEquals(categories, categoriesService.search(categoriesSearchValues));
    }
}