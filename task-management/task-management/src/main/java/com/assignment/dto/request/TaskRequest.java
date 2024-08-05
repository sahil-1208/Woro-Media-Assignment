package com.assignment.dto.request;

import com.assignment.constants.TaskPriority;
import com.assignment.constants.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskRequest {

    private String title;

    private String description;

    private LocalDate dueDate;

    private TaskPriority priority;

    private Long userId;

    private TaskStatus taskStatus;
}
