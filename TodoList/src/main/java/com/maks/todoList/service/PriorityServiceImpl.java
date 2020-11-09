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

@Service
public class PriorityServiceImpl implements PriorityService{
    private PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByIdAsc();
    }

    @Override
    public ResponseEntity<Priority> findById(Long id) {
        Priority priority = null;
        try {
            priority = priorityRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priority);
    }

    @Override
    public ResponseEntity<Priority>  add(Priority priority) {
        // check getid because autoincrement of id in DB
        if (priority.getId() != null && priority.getId() != 0){
            return new ResponseEntity("id MUST be null", HttpStatus.ACCEPTED);
        }
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priorityRepository.save(priority));

    }

    @Override
    public ResponseEntity<Priority> update(Priority priority) {

        if (priority.getId() == null && priority.getId() == 0) {
            return new ResponseEntity("id MUST be not null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getTitle() == null && priority.getTitle().trim().length() == 0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        if (priority.getColor() == null || priority.getColor().trim().length() == 0){
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @Override
    public ResponseEntity deleteById(Long id) {

        try {
            priorityRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Priority>> search(PrioritySearchValues prioritySearchValues) {
        return ResponseEntity.ok(priorityRepository.findByTitle(prioritySearchValues.getPriorityParam()));
    }
}
