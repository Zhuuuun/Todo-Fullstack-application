package com.zhunism.backendapp.service;

import com.zhunism.backendapp.dto.TaskResponseDTO;
import com.zhunism.backendapp.dto.request.NewTaskRequestDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(NewTaskRequestDTO newTaskRequestDTO);

    List<TaskResponseDTO> findAllTask();

    List<TaskResponseDTO> findAllTaskByUserName(String userName);

    TaskResponseDTO increaseStatus(int taskId);

    String deleteTaskById(int taskId);
}
