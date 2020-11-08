package com.maks.todoList.repository;

import com.maks.todoList.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
    List<Stat> findAllByOrderById();

}
