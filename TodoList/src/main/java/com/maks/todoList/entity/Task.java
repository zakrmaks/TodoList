package com.maks.todoList.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Task {
    private Long id;
    private String title;
    private Integer completed;
    private Date date;
    private Priority priority;
    private Category category;

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
    @Column(name = "completed")
    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priorityByPriorityId) {
        this.priority = priorityByPriorityId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categoryByCategoryId) {
        this.category = categoryByCategoryId;
    }
}
