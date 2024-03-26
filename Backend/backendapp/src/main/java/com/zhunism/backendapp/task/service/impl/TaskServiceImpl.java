package com.zhunism.backendapp.task.service.impl;

import com.zhunism.backendapp.task.dto.response.TaskResponseDTO;
import com.zhunism.backendapp.task.dto.request.NewTaskRequestDTO;
import com.zhunism.backendapp.task.entity.Task;
import com.zhunism.backendapp.task.exception.InvalidOperationException;
import com.zhunism.backendapp.task.exception.ElementNotFoundException;
import com.zhunism.backendapp.task.repository.TaskRepository;
import com.zhunism.backendapp.task.service.TaskService;
import com.zhunism.backendapp.authentication.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    private TaskResponseDTO mapToDTO(Task task) {
        return new TaskResponseDTO(
                task.getTaskId(),
                task.getUserId(),
                task.getTitle(),
                task.getPriority(),
                task.getStatus(),
                task.getDueDate()
        );
    }

    @Override
    public TaskResponseDTO createTask(NewTaskRequestDTO newTaskRequestDTO) {

        if(newTaskRequestDTO.getDueDate().isBefore(LocalDate.now())) throw new InvalidOperationException("Due date can't be history date.");

        Task task = new Task();
        task.setUserId(userService.findUserIdByUserName(newTaskRequestDTO.getUserName()));
        task.setTitle(newTaskRequestDTO.getTitle());
        task.setPriority(newTaskRequestDTO.getPriority());
        task.setStatus(1);
        task.setDueDate(newTaskRequestDTO.getDueDate());

        Task createdTask = taskRepository.save(task);
        return mapToDTO(createdTask);
    }

    @Override
    public List<TaskResponseDTO> findAllTask() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDTO> findAllTaskByUserName(String userName) {
        int userId = userService.findUserIdByUserName(userName);

        List<Task> tasks = taskRepository.findAllByUserId(userId);
        return tasks.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO increaseStatus(int taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ElementNotFoundException("Task not found with id : " + taskId));

        if(task.getStatus() == 3) throw new InvalidOperationException("Task reach highest status level, can't increase status level");
        task.setStatus(task.getStatus() + 1);

        taskRepository.save(task);
        return mapToDTO(task);
    }

    @Override
    public String deleteTaskById(int taskId) {
        if(!taskRepository.existsById(taskId)) throw new ElementNotFoundException("Task not found with id : " + taskId);

        taskRepository.deleteById(taskId);
        return "Task id : " + taskId + " was deleted";
    }


}
