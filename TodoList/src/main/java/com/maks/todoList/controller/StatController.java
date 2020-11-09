package com.maks.todoList.controller;

import com.maks.todoList.entity.Stat;
import com.maks.todoList.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatController {
    StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }
    @GetMapping("/findAll")
    public List<Stat> findAll(){
        return statService.findAll();
    }

    }
