package com.maks.todoList.service;

import com.maks.todoList.entity.Priority;
import com.maks.todoList.repository.PriorityRepository;
import com.maks.todoList.search.PrioritySearchValues;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PriorityServiceImpl implements PriorityService {
    private PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Optional<Priority> findById(Long id) {

        return priorityRepository.findById(id);

    }

    @Override
    public Priority add(Priority priority) {
        // check getid because autoincrement of id in DB

        return priorityRepository.save(priority);

    }

    @Override
    public Priority update(Priority priority) {

        return priorityRepository.save(priority);
    }

    @Override
    public void deleteById(Long id) {
        this.priorityRepository.deleteById(id);

    }

    @Override
    public List<Priority> search(PrioritySearchValues prioritySearchValues) {
        return priorityRepository.findByTitle(prioritySearchValues.getPriorityParam());
    }
}
