package com.co.udea.persintence;

import com.co.udea.model.Task;

import java.util.Optional;
import java.util.stream.Stream;

public interface Persistence {
    Optional<Task> createTask(Task task);
    Optional<Task> getTask(Integer id);
    Optional<Task> completeTask(Integer id);
    Optional<Task> startTask(Integer id);
    void deleteTask(Integer id);
    Stream<Task> getAllTaskPending();
    Stream<Task> getAllTaskComplete();
}
