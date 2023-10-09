package com.co.udea.service;

import com.co.udea.datamock.DataMock;
import com.co.udea.model.Status;
import com.co.udea.model.Task;
import com.co.udea.persintence.Persistence;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DisplayName("Task Manager validation")
class TaskManagerTest {

    @InjectMocks
    TaskManager taskManager;
    @Mock
    Persistence persistence;
    private final DataMock data = new DataMock();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create task without problems")
    void createTaskOk() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.empty());
        when(persistence.createTask(any(Task.class))).thenReturn(Optional.of(data.get(1)));

        assertTrue(taskManager.createTask(data.get(1)));
    }

    @Test
    @DisplayName("Create task but fails because already exists")
    void createTaskFails() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.of(data.get(1)));

        assertFalse(taskManager.createTask(data.get(1)));
    }

    @Test
    @DisplayName("Get a Task")
    void getTask() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.of(data.get(1)));

        assertTrue(taskManager.getTask(1).isPresent());
    }

    @Test
    @DisplayName("Get a Task but is empty")
    void getTaskEmpty() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.empty());

        assertTrue(taskManager.getTask(1).isEmpty());
    }

    @Test
    @DisplayName("Completing a task successfully")
    void completeTaskSuccessfully() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.of(data.get(1)));
        data.get(1).setStatus(Status.CLOSE);
        when(persistence.completeTask(anyInt())).thenReturn(Optional.of(data.get(1)));

        Optional<Task> task = taskManager.completeTask(1);

        assertTrue(task.isPresent());
        assertEquals(Status.CLOSE, task.get().getStatus());
    }

    @Test
    @DisplayName("Failed to complete task")
    void failToCompleteTask() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.empty());

        assertTrue(taskManager.completeTask(1).isEmpty());
    }

    @Test
    @DisplayName("Start a task successfully")
    void startTaskSuccessfully() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.of(data.get(1)));
        data.get(1).setStatus(Status.PROGRESS);
        when(persistence.startTask(anyInt())).thenReturn(Optional.of(data.get(1)));

        Optional<Task> task = taskManager.startTask(1);

        assertTrue(task.isPresent());
        assertEquals(Status.PROGRESS, task.get().getStatus());
    }

    @Test
    @DisplayName("Failed to start task")
    void failToStartTask() {
        when(persistence.getTask(anyInt())).thenReturn(Optional.empty());

        assertTrue(taskManager.startTask(1).isEmpty());
    }

    @Test
    @DisplayName("Delete task successfully")
    void deleteTaskOk() {
        doNothing().when(persistence).deleteTask(anyInt());
        when(persistence.getTask(anyInt())).thenReturn(Optional.empty());

        assertFalse(taskManager.deleteTask(1));
    }

    @Test
    @DisplayName("Delete task fail")
    void deleteTaskFail() {
        doNothing().when(persistence).deleteTask(anyInt());
        when(persistence.getTask(anyInt())).thenReturn(Optional.of(data.get(1)));

        assertTrue(taskManager.deleteTask(1));
    }

    @Test
    @DisplayName("Get all task pending")
    void getAllTaskPending() {
        when(persistence.getAllTaskPending()).thenReturn(data.getAll());

        assertTrue(taskManager.getAllTaskPending().findAny().isPresent());
    }

    @Test
    @DisplayName("Get all task pending empty")
    void getAllTaskPendingEmpty() {
        when(persistence.getAllTaskPending()).thenReturn(Stream.empty());

        assertTrue(taskManager.getAllTaskPending().findAny().isEmpty());
    }

    @Test
    @DisplayName("Get all task closed")
    void getAllTaskClosed() {
        when(persistence.getAllTaskComplete()).thenReturn(data.getAll());

        assertTrue(taskManager.getAllTaskComplete().findAny().isPresent());
    }

    @Test
    @DisplayName("Get all task closed empty")
    void getAllTaskClosedEmpty() {
        when(persistence.getAllTaskComplete()).thenReturn(Stream.empty());

        assertTrue(taskManager.getAllTaskComplete().findAny().isEmpty());
    }

  
}