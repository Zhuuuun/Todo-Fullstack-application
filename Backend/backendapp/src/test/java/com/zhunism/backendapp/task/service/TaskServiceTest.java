package com.zhunism.backendapp.task.service;

import com.zhunism.backendapp.authentication.service.UserService;
import com.zhunism.backendapp.task.dto.request.NewTaskRequestDTO;
import com.zhunism.backendapp.task.dto.response.TaskResponseDTO;
import com.zhunism.backendapp.task.entity.Task;
import com.zhunism.backendapp.task.exception.InvalidOperationException;
import com.zhunism.backendapp.task.repository.TaskRepository;
import com.zhunism.backendapp.task.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    public static final String USER_NAME = "gojosatoru";
    public static final String TITLE = "real iki tenkai";
    public static final int PRIORITY = 2;
    public static final LocalDate DUE_DATE = LocalDate.now();
    public static final int TASK_ID = 1;
    public static final int USER_ID = 1;
    public static final int NORMAL_STATUS = 1;
    public static final int MAX_STATUS = 3;
    @Mock private TaskRepository taskRepository;
    @Mock private UserService userService;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(
                taskRepository,
                userService
        );
    }

    @Test
    void testCreateValidTaskShouldSuccess() {
        NewTaskRequestDTO taskRequestDTO = new NewTaskRequestDTO(
                USER_NAME,
                TITLE,
                PRIORITY,
                DUE_DATE
        );
        Task expected = convertNewTaskRequestDTOToTask(taskRequestDTO);
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(expected);
        when(userService.findUserIdByUserName(Mockito.contains(USER_NAME))).thenReturn(1);

        taskService.createTask(taskRequestDTO);

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository)
                .save(taskArgumentCaptor.capture());
        verify(userService)
                .findUserIdByUserName(USER_NAME);
        Task actual = taskArgumentCaptor.getValue();

        assertTask(expected,actual);
    }

    @Test
    void testCreateTaskWithInvalidDateShouldThrowInvalidOperationException() {
        NewTaskRequestDTO newTaskRequestDTO = new NewTaskRequestDTO();
        newTaskRequestDTO.setUserName(USER_NAME);
        newTaskRequestDTO.setPriority(PRIORITY);
        newTaskRequestDTO.setTitle(TITLE);
        newTaskRequestDTO.setDueDate(LocalDate.now().minusDays(1));

        assertThrows(InvalidOperationException.class, () -> taskService.createTask(newTaskRequestDTO));
    }

    @Test
    void testFindAllTaskShouldReturnAll() {
        List<Task> expect = new ArrayList<>();
        for (int i = 1; i <= 3; i++) expect.add(generateTask(i));
        when(userService.findUserIdByUserName(USER_NAME)).thenReturn(USER_ID);
        when(taskRepository.findAllByUserId(USER_ID)).thenReturn(expect);

        List<TaskResponseDTO> actual = taskService.findAllTaskByUserName(USER_NAME);

        assertEquals(actual.size(), expect.size());
        actual.forEach(Assertions::assertNotNull);
        actual.forEach(t -> {
            assertEquals(t.getTitle(),TITLE);
            assertEquals(t.getPriority(),PRIORITY);
            assertEquals(t.getStatus(), NORMAL_STATUS);
            assertEquals(t.getUserId(),USER_ID);
            assertEquals(t.getDueDate(),DUE_DATE);
        });
    }

    @Test
    void testIncreaseStatusTwoShouldIncreaseByOne() {
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(generateTask(TASK_ID)));
        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(generateTask(TASK_ID));

        TaskResponseDTO taskResponseDTO = taskService.increaseStatus(TASK_ID);

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository)
                .save(taskArgumentCaptor.capture());

        Task actual = taskArgumentCaptor.getValue();
        assertEquals(NORMAL_STATUS + 1,actual.getStatus());
    }

    @Test
    void testIncreaseStatusThreeShouldThrowInvalidOperationException() {
        Task task = generateTask(TASK_ID);
        task.setStatus(MAX_STATUS);
        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(task));

        assertThrows(InvalidOperationException.class, () -> taskService.increaseStatus(TASK_ID));
    }

    @Test
    void testDeleteTaskByIdWithValidIdShouldSuccess() {
        when(taskRepository.existsById(TASK_ID)).thenReturn(true);

        taskService.deleteTaskById(TASK_ID);

        ArgumentCaptor<Integer> idCapture = ArgumentCaptor.forClass(Integer.class);
        verify(taskRepository)
                .deleteById(idCapture.capture());

        int actual = idCapture.getValue();
        assertEquals(TASK_ID,actual);
    }

    private static Task convertNewTaskRequestDTOToTask(NewTaskRequestDTO newTaskRequestDTO) {
        Task task = new Task();
        task.setTaskId(TASK_ID);
        task.setUserId(USER_ID);
        task.setTitle(newTaskRequestDTO.getTitle());
        task.setDueDate(newTaskRequestDTO.getDueDate());
        task.setPriority(newTaskRequestDTO.getPriority());
        task.setStatus(NORMAL_STATUS);

        return task;
    }

    private static Task generateTask(int tid) {
        return new Task(
                tid,
                USER_ID,
                TITLE,
                PRIORITY,
                NORMAL_STATUS,
                DUE_DATE
        );
    }

    private static void assertTask(Task t1, Task t2) {
        assertThat(t1.getTitle()).isEqualTo(t2.getTitle());
        assertThat(t1.getDueDate()).isEqualTo(t2.getDueDate());
        assertThat(t1.getPriority()).isEqualTo(t2.getPriority());
        assertThat(t1.getStatus()).isEqualTo(t2.getStatus());
        assertThat(t1.getUserId()).isEqualTo(t2.getUserId());
    }


}
