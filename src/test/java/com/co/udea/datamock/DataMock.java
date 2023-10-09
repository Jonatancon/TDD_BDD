package com.co.udea.datamock;

import com.co.udea.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class DataMock {
    private final TreeMap<Integer, Task> tasks = new TreeMap<>();
    private static final String PATH = "src/main/resources/data.txt";

    public DataMock() {
        loadData();
    }

    public void loadData() {
        try{
            File file = new File(PATH);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");

                Task task = new Task(Integer.parseInt(line[0]), line[1], line[2]);

                tasks.put(task.getId(), task);
            }

            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        }
    }

    public Task get(Integer id){
        return tasks.get(id);
    }

    public Stream<Task> getAll() {
        return tasks.values().stream();
    }
}
