package com.maks.todoList.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Category {
    private Long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "completed_count")
    public Long getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Long completedCount) {
        this.completedCount = completedCount;
    }

    @Basic
    @Column(name = "uncompleted_count")
    public Long getUncompletedCount() {
        return uncompletedCount;
    }

    public void setUncompletedCount(Long uncompletedCount) {
        this.uncompletedCount = uncompletedCount;
    }

}
