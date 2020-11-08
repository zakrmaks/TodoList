package com.maks.todoList.controller;

import com.maks.todoList.entity.Stat;
import com.maks.todoList.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatController {
    StatRepository statRepository;

    @Autowired
    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }
    @GetMapping("findAll")
    public List<Stat> findAll(){
        return statRepository.findAllByOrderById();
    }

}
