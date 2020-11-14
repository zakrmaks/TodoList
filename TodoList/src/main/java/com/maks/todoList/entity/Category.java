package com.maks.todoList.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.spec.ECField;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter

public class Category {
    private Long id;
    private String title;
    private Long completedCount;
    private Long uncompletedCount;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @NotNull(message = "Can't be null")
    @Min(value = 1, message = "Not null")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
    @NotNull

    @NotNull
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "completed_count")
    public Long getCompletedCount() {
        return completedCount;
    }

    @Basic
    @Column(name = "uncompleted_count")
    public Long getUncompletedCount() {
        return uncompletedCount;
    }

}
