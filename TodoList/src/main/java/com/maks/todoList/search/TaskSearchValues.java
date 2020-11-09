package com.maks.todoList.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TaskSearchValues {
    private String text;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;


    //search by Pages param
    private Integer pageNumber;
    private Integer pageSize;

    ///sort by page param
    private String sortColumn;
    private String sortDirection;
}
