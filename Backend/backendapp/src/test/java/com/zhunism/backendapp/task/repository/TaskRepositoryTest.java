package com.zhunism.backendapp.task.repository;

import com.zhunism.backendapp.task.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTest {

    public static final int USER_ID = 1;
    public static final String TITLE = "Example";
    public static final int PRIORITY = 2;
    public static final int STATUS = 1;
    public static final LocalDate DUE_DATE = LocalDate.now();
    @Autowired
    TaskRepository taskRepository;

    @Test
    public void testUserShouldValidInDatabase() {
        Task expectedTask = createSingleTask(USER_ID, TITLE, PRIORITY, STATUS, DUE_DATE);

        Task buff = taskRepository.save(expectedTask);
        Task actualTask = taskRepository.findById(buff.getTaskId()).get();

        assertNotEquals(null,actualTask);
        assertTask(expectedTask,actualTask);
    }

    @Test
    public void testFindAllTaskShouldReturnAll() {
        List<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        createSingleTask(USER_ID, TITLE, PRIORITY, STATUS, DUE_DATE),
                        createSingleTask(USER_ID, TITLE, PRIORITY, STATUS, DUE_DATE),
                        createSingleTask(USER_ID, TITLE, PRIORITY, STATUS, DUE_DATE)
                ));
        taskRepository.saveAll(tasks);

        int actualSize = taskRepository.findAll().size();
        int expectedSize = tasks.size();

        assertNotEquals(0,actualSize);
        assertEquals(expectedSize,actualSize);
    }

    @Test
    public void testFindAllTaskByUserIdShouldReturnTaskWithAssociateUserId() {
        int associateUserId = 1;
        int regularUserId = 2;
        Task associateTask = createSingleTask(associateUserId, TITLE, PRIORITY, STATUS, DUE_DATE);
        Task regularTask = createSingleTask(regularUserId, TITLE, PRIORITY, STATUS, DUE_DATE);
        List<Task> tasks = new ArrayList<>(
                Arrays.asList(
                        associateTask,
                        regularTask
                ));
        taskRepository.saveAll(tasks);

        List<Task> actualTasks = taskRepository.findAllByUserId(associateUserId);
        int expectedSize = 1;

        assertNotEquals(null,actualTasks);
        assertEquals(expectedSize,actualTasks.size());
        assertFalse(actualTasks.contains(regularTask));
        assertTrue(actualTasks.contains(associateTask));
    }

    @Test
    public void testFindTaskByIdShouldReturnTaskWithExpectedId() {
        Task expectedTask = createSingleTask(USER_ID, TITLE, PRIORITY, STATUS, DUE_DATE);
        int id = taskRepository.save(expectedTask).getTaskId();

        Task actualTask = taskRepository.findById(id).get();

        assertNotEquals(null,actualTask);
        assertTask(expectedTask,actualTask);
    }

    @Test
    public void testDeleteTaskById() {
        Task expectedTask = createSingleTask(USER_ID, TITLE, PRIORITY, STATUS, DUE_DATE);
        int id = taskRepository.save(expectedTask).getTaskId();

        taskRepository.delete(expectedTask);
        Optional<Task> actualTask = taskRepository.findById(id);

        assertFalse(actualTask.isPresent());
    }

    private static void assertTask(Task t1,Task t2) {
        assertEquals(t1.getUserId(),t2.getUserId());
        assertEquals(t1.getTitle(),t2.getTitle());
        assertEquals(t1.getPriority(),t2.getPriority());
        assertEquals(t1.getStatus(),t2.getStatus());
        assertEquals(t1.getDueDate(),t2.getDueDate());
    }

    private static Task createSingleTask(int userId, String title, int priority, int status, LocalDate dueDate) {
        Task task = new Task();
        task.setUserId(userId);
        task.setTitle(title);
        task.setPriority(priority);
        task.setStatus(status);
        task.setDueDate(dueDate);

        return task;
    }
}
