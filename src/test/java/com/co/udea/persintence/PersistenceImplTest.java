package com.co.udea.persintence;

import com.co.udea.datamock.DataMock;
import com.co.udea.model.Status;
import com.co.udea.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@DisplayName("Persistence Tasks")
class PersistenceImplTest {

    PersistenceImpl persistence = new PersistenceImpl(false);
    DataMock data = new DataMock();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create a task")
    void createTask() {

        Optional<Task> task = persistence.createTask(data.get(1));

        assertTrue(task.isPresent());
        assertEquals(task.get().getId(), data.get(1).getId());
        assertEquals(task.get().getTitle(), data.get(1).getTitle());
        assertEquals(task.get().getDescription(), data.get(1).getDescription());
        assertEquals(task.get().getStatus(), data.get(1).getStatus());
    }

    @Test
    @DisplayName("get a task")
    void getTask() {
        persistence.createTask(data.get(3));
        Optional<Task> task = persistence.getTask(3);

        assertTrue(task.isPresent());
        assertEquals(task.get().getId(), data.get(3).getId());
    }

    @Test
    @DisplayName("Complete a task")
    void completeTask() {
        persistence.createTask(data.get(10));
        Optional<Task> task = persistence.completeTask(10);

        assertTrue(task.isPresent());
        assertEquals(Status.CLOSE, task.get().getStatus());
        assertEquals(Status.CLOSE.getStatusTask(), task.get().getStatus().getStatusTask());
    }

    @Test
    @DisplayName("Start a task")
    void startTask() {
        persistence.createTask(data.get(11));
        Optional<Task> task = persistence.startTask(11);

        assertTrue(task.isPresent());
        assertEquals(Status.PROGRESS, task.get().getStatus());
    }

    @Test
    @DisplayName("Delete a task")
    void deleteTask() {
        persistence.createTask(data.get(5));
        assertDoesNotThrow(() -> persistence.deleteTask(5));
    }

    @Test
    @DisplayName("Get all task pending")
    void getAllTaskPending() {
        data.getAll().forEach(task -> persistence.createTask(task));

        assertTrue(persistence.getAllTaskPending().findAny().isPresent());
    }

    @Test
    @DisplayName("Get all task complete")
    void getAllTaskComplete() {
        data.getAll().forEach(task -> persistence.createTask(task));

        Stream<Task> stream = persistence.getAllTaskPending();

        for (Task task : stream.toList()) {
            persistence.completeTask(task.getId());
        }

        assertTrue(persistence.getAllTaskComplete().findAny().isPresent());
    }
}