package com.co.udea.vista;

import com.co.udea.model.Task;
import com.co.udea.persintence.Persistence;
import com.co.udea.persintence.PersistenceImpl;
import com.co.udea.service.TaskManager;

import java.util.Optional;
import java.util.Scanner;

public class ConfigurationTask {
    private final TaskManager taskManager;
    private final Scanner read = new Scanner(System.in);
    private int id;
    private static final String ID = "Porfavor ingrese el ID de la tarea:";

    public ConfigurationTask() {
        Persistence persistence = new PersistenceImpl(true);
        taskManager = new TaskManager(persistence);
    }

    public void imprimirMenu() {
        System.out.println("""
                Porfavor escoja una de las siguientes opciones:
                (1). Crear una nueva tarea.
                (2). Iniciar una tarea.
                (3). Terminar una tarea.
                (4). Ver las tareas pendientes.
                (5). Ver las tareas completadas.
                (6). Eliminar una tarea.
                (7). Ver una tarea en especifico.
                (8). Salir""");
    }

    public void saveData() {
        System.out.println(ID);
        id = read.nextInt();
        System.out.println("Porfavor ingrese el titulo de la tarea:");
        String title = read.nextLine();
        System.out.println("Porfavor ingrese la descripcion de la tarea:");
        String description = read.nextLine();

        if (taskManager.createTask(new Task(id, title, description))) {
            System.out.println("tarea creada con exito");
        }
        else{
            System.out.println("La Tarea no se pudo crear");
        }
    }

    public void init() {
        System.out.println(ID);
        id = read.nextInt();
        if (taskManager.startTask(id).isPresent()) {
            System.out.println("Tarea Iniciada");
        } else {
            System.out.println("La Tarea no se pudo iniciar");
        }
    }

    public void close() {
        System.out.println(ID);
        id = read.nextInt();
        if (taskManager.completeTask(id).isPresent()) {
            System.out.println("Tarea Fue terminda");
        } else {
            System.out.println("La Tarea no se pudo Terminar");
        }
    }

    public void getTaskPending() {
        taskManager.getAllTaskPending()
                .forEach(task -> System.out.println("{\n" +
                        "  ID:           " + task.getId() + "\n" +
                        "  Titulo:       " + task.getTitle() + "\n" +
                        "  Descripcion:  " + task.getDescription() + "\n" +
                        "  Estado:       " + task.getStatus().getStatusTask() + "\n" +
                        "}\n"));
    }

    public void getTaskComplete() {
        taskManager.getAllTaskComplete()
                .forEach(task -> System.out.println("{\n" +
                        "  ID:           " + task.getId() + "\n" +
                        "  Titulo:       " + task.getTitle() + "\n" +
                        "  Descripcion:  " + task.getDescription() + "\n" +
                        "  Estado:       " + task.getStatus().getStatusTask() + "\n" +
                        "}\n"));
    }

    public void delete() {
        System.out.println(ID);
        id = read.nextInt();
        if (taskManager.deleteTask(id)) {
            System.out.println("Tarea eliminada");
        } else {
            System.out.println("La tarea no se pudo eliminar");
        }
    }

    public void get() {
        System.out.println(ID);
        id = read.nextInt();
        Optional<Task> task = taskManager.getTask(id);
        if (task.isEmpty()) {
            System.out.println("No se encontro la tarea");
        }
        else {
            System.out.println("{\n" +
                    "  ID:           " + task.get().getId() + "\n" +
                    "  Titulo:       " + task.get().getTitle() + "\n" +
                    "  Descripcion:  " + task.get().getDescription() + "\n" +
                    "  Estado:       " + task.get().getStatus() + "\n" +
                    "}\n");
        }
    }

    public void retry() {
        System.out.println("Opcion invalida intente denuevo");
    }
}
