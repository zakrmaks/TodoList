package com.maks.todoList.service;

import com.maks.todoList.entity.Category;
import com.maks.todoList.repository.CategoryRepository;
import com.maks.todoList.search.CategorySearchValues;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
        public Optional<Category> findById(Long id) {


            return categoryRepository.findById(id);

        }

    @Override
    public Category add(Category category) {

        // check getid because autoincrement of id in DB
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);

    }

    // search for different param CategorySearchValues
    @Override
    public List<Category> search(CategorySearchValues categorySearchValues) {
        return categoryRepository.findByTitle(categorySearchValues.getText());
    }
}
