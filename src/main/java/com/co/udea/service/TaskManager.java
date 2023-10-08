package com.co.udea.service;

import com.co.udea.model.Task;
import com.co.udea.model.Status;

import java.util.*;

public class TaskManager {

    TreeMap<Integer, Task> tasks = new TreeMap<>();
    Set<Task> completeTask = new HashSet<>();
    public boolean createTask(Integer id, String title, String description) {
        Task task = new Task(id, title, description);
        tasks.put(id, task);
        return true;
    }

    public Task getTask(Integer id) {
        return tasks.get(id);
    }

    public boolean completeTask(Integer id) {
        Task task = tasks.get(id);
        task.setStatus(Status.CLOSE);
        completeTask.add(task);
        tasks.remove(id);

        return true;
    }

    public boolean startTask(Integer id) {
        tasks.get(id).setStatus(Status.PROGRESS);
        return true;
    }

    public boolean deleteTask(Integer id) {
        tasks.remove(id);
        return true;
    }

    public List<Task> getAllTaskPending() {
         return tasks.values().stream().toList();
    }

    public List<Task> getAllTaskComplete() {
        return completeTask.stream().toList();
    }
}
