package com.zhunism.backendapp.task.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskRequestDTO {
    @NotBlank(message = "Username is mandatory")
    String userName;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 50, message = "Whoops! Your title is a bit too long. Please keep title under 50 characters")
    String title;

    @NotNull(message = "priority is mandatory")
    @Min(value = 1, message = "Minimum of priority number is 1")
    @Max(value = 4, message = "Maximum of priority number is 4")
    int priority;

    @NotNull(message = "Due date is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    LocalDate dueDate;
}
