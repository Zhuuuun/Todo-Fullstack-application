package com.zhunism.backendapp.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    int taskId;
    int userId;
    String title;
    int priority;
    int status;
    LocalDate dueDate;
}
