package com.maks.todoList.service;

import com.maks.todoList.entity.Stat;
import com.maks.todoList.repository.StatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    private StatRepository statRepository;

    public StatServiceImpl(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @Override
    public List<Stat> findAll() {
        return statRepository.findAllByOrderById();
    }

}
