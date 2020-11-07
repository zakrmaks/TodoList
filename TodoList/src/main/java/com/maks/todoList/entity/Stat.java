package com.maks.todoList.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Stat {
    private Long id;
    private Long completedTotal;
    private Long uncompletedTotal;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "completed_total")
    public Long getCompletedTotal() {
        return completedTotal;
    }

    public void setCompletedTotal(Long completedTotal) {
        this.completedTotal = completedTotal;
    }

    @Basic
    @Column(name = "uncompleted_total")
    public Long getUncompletedTotal() {
        return uncompletedTotal;
    }

    public void setUncompletedTotal(Long uncompletedTotal) {
        this.uncompletedTotal = uncompletedTotal;
    }


}
