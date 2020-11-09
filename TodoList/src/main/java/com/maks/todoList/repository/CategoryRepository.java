package com.maks.todoList.repository;

import com.maks.todoList.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // if title == null or =='' return all value
    @Query("select c from Category  c where " +
    "(:title is null or :title='' or lower(c.title) like lower(concat('%', :title, '%') ) ) " +
    "order by c.title asc")
    List<Category> findByTitle(@Param("title") String title);


    List<Category> findAllByOrderByTitleAsc();
}
