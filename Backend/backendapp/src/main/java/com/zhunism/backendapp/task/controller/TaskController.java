package com.zhunism.backendapp.task.controller;

import com.zhunism.backendapp.task.dto.response.TaskResponseDTO;
import com.zhunism.backendapp.task.dto.request.NewTaskRequestDTO;
import com.zhunism.backendapp.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<?> findAllTask() {
        return new ResponseEntity<>(taskService.findAllTask(),HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<?> createTask(@RequestBody @Valid NewTaskRequestDTO newTaskRequestDTO) {
        TaskResponseDTO createdTask = taskService.createTask(newTaskRequestDTO);

        if(createdTask == null) return new ResponseEntity<>("Task isn't created, Try again later.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdTask,HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{userName}")
    public ResponseEntity<?> findAllTaskByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(taskService.findAllTaskByUserName(userName),HttpStatus.OK);
    }

    @PutMapping("/task/status/{taskId}")
    public ResponseEntity<?> increaseStatus(@PathVariable int taskId) {
        return new ResponseEntity<>(taskService.increaseStatus(taskId),HttpStatus.OK);
    }

    @DeleteMapping("task/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable int taskId) {
        return new ResponseEntity<>(taskService.deleteTaskById(taskId),HttpStatus.OK);
    }


}
