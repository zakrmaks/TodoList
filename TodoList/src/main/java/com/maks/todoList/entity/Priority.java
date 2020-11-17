package com.maks.todoList.entity;

import com.maks.todoList.dto.OutNullParam;
import com.maks.todoList.dto.WithNullParam;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Null(groups = {WithNullParam.class})
    @NotNull(groups = {OutNullParam.class})

    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title")
//    @Null(groups = {OutNullParam.class})
    @NotNull(groups = {WithNullParam.class, OutNullParam.class})
    @NotEmpty(groups = {OutNullParam.class, OutNullParam.class}, message = " title can't be empty")

    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "color")
//    @Null(groups = {OutNullParam.class})
    @NotNull(groups = {WithNullParam.class, OutNullParam.class})
    @NotEmpty(groups = {OutNullParam.class, OutNullParam.class}, message = "title can't be empty")
    public String getColor() {
        return color;
    }

}
