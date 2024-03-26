package com.zhunism.backendapp.task.service;

import com.zhunism.backendapp.task.dto.response.TaskResponseDTO;
import com.zhunism.backendapp.task.dto.request.NewTaskRequestDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(NewTaskRequestDTO newTaskRequestDTO);

    List<TaskResponseDTO> findAllTask();

    List<TaskResponseDTO> findAllTaskByUserName(String userName);

    TaskResponseDTO increaseStatus(int taskId);

    String deleteTaskById(int taskId);
}
