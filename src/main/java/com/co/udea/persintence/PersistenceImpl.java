package com.co.udea.persintence;

import com.co.udea.model.Status;
import com.co.udea.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class PersistenceImpl implements Persistence{

    private final TreeMap<Integer, Task> tasks = new TreeMap<>();
    private final Map<Integer, Task> completeTasks = new HashMap<>();
    private static final String PATH = "src/main/resources/data.txt";

    public PersistenceImpl(boolean load) {
        if (load) {
            System.out.println("Cargando datos......\n");
            loadData();
            System.out.println("Carga finalizada\n");
        }
    }


    @Override
    public Optional<Task> createTask(Task task) {
        tasks.put(task.getId(), task);

        return Optional.of(tasks.get(task.getId()));
    }

    @Override
    public Optional<Task> getTask(Integer id) {
        return Optional.of(tasks.get(id));
    }

    @Override
    public Optional<Task> completeTask(Integer id) {
        tasks.get(id).setStatus(Status.CLOSE);
        completeTasks.put(id, tasks.get(id));
        tasks.remove(id);

        return Optional.of(completeTasks.get(id));
    }

    @Override
    public Optional<Task> startTask(Integer id) {
        tasks.get(id).setStatus(Status.PROGRESS);

        return Optional.of(tasks.get(id));
    }

    @Override
    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    @Override
    public Stream<Task> getAllTaskPending() {
        return tasks.values().parallelStream();
    }

    @Override
    public Stream<Task> getAllTaskComplete() {
        return completeTasks.values().parallelStream();
    }

    private void loadData() {
        try{
            File file = new File(PATH);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                Task task = new Task(Integer.parseInt(line[0]), line[1], line[2]);
                createTask(task);
            }

            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo \n" + e);
        }
    }
}
