package com.maks.todoList.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class Priority {
    private Long id;
    private String title;
    private String color;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @Null

    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
    @NotNull
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "color")
    @NotNull
    public String getColor() {
        return color;
    }

}
