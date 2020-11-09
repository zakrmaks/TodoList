package com.maks.todoList.repository;

import com.maks.todoList.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query("select c from Task  c where " +
            "(:title is null or :title='' or lower(c.title) like lower(concat('%', :title, '%') ) ) and " +
            "(:completed is null or c.completed=:completed) and " +
            "(:priorityId is null or c.priority.id=:priorityId) and " +
            "(:categoryId is null or c.category.id=:categoryId)")
    Page<Task> findByParams(@Param("title") String title, @Param("completed") Integer completed, @Param("priorityId") Long priorityId, @Param("categoryId") Long categoryId, Pageable pageable);

}
