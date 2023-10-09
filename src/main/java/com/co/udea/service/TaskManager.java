package com.co.udea.service;

import com.co.udea.model.Task;
import com.co.udea.persintence.Persistence;

import java.util.*;
import java.util.stream.Stream;

public class TaskManager {

    private final Persistence persistence;

    public TaskManager(Persistence persistence) {
        this.persistence = persistence;
    }

    public boolean createTask(Task task) {

        if (persistence.getTask(task.getId()).isPresent()) {
            return false;
        }

        return persistence.createTask(task).isPresent();
    }

    public Optional<Task> getTask(Integer id) {
        return persistence.getTask(id);
    }

    public Optional<Task> completeTask(Integer id) {
        if (persistence.getTask(id).isEmpty()) {
            return Optional.empty();
        }

        return persistence.completeTask(id);
    }

    public Optional<Task> startTask(Integer id) {
        if (persistence.getTask(id).isEmpty()) {
            return Optional.empty();
        }

        return persistence.startTask(id);
    }

    public boolean deleteTask(Integer id) {
        persistence.deleteTask(id);

        return persistence.getTask(id).isPresent();
    }

    public Stream<Task> getAllTaskPending() {
         return persistence.getAllTaskPending();
    }

    public Stream<Task> getAllTaskComplete() {
        return persistence.getAllTaskComplete();
    }
}
