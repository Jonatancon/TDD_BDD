package com.co.udea;

import com.co.udea.model.Task;
import com.co.udea.service.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String path = "src/main/resources/data.txt";
        TaskManager taskManager = new TaskManager();
        Scanner read = new Scanner(System.in);
        int id;

        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");

                taskManager.createTask(Integer.parseInt(line[0]), line[1], line[2]);
            }

            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        }

        while(true) {
            System.out.println("Porfavor escoja una de las siguientes opciones:\n" +
                    "(1). Crear una nueva tarea.\n" +
                    "(2). Iniciar una tarea.\n" +
                    "(3). Terminar una tarea.\n" +
                    "(4). Ver las tareas pendientes.\n" +
                    "(5). Ver las tareas completadas.\n" +
                    "(6). Eliminar una tarea.\n" +
                    "(7). Ver una tarea en especifico.\n" +
                    "(8). Salir");

            int opcion = read.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Porfavor ingrese el ID de la tarea:");
                    id = read.nextInt();
                    System.out.println("Porfavor ingrese el titulo de la tarea:");
                    String title = read.nextLine();
                    System.out.println("Porfavor ingrese la descripcion de la tarea:");
                    String description = read.nextLine();
                    
                    if (taskManager.createTask(id, title, description)) {
                        System.out.println("tarea creada con exito");
                        break;
                    }
                    else{
                        System.out.println("La Tarea no se pudo crear");
                        break;                        
                    }
                case 2:
                    System.out.println("Porfavor ingrese el ID de la tarea:");
                    id = read.nextInt();
                    if (taskManager.startTask(id)) {
                        System.out.println("Tarea Iniciada");
                        break;
                    }
                    else {
                        System.out.println("La Tarea no se pudo iniciar");
                        break;
                    }
                case 3:
                    System.out.println("Porfavor ingrese el ID de la tarea:");
                    id = read.nextInt();
                    if (taskManager.completeTask(id)) {
                        System.out.println("Tarea Fue terminda");
                        break;
                    }
                    else {
                        System.out.println("La Tarea no se pudo Terminar");
                        break;
                    }
                case 4:
                    taskManager.getAllTaskPending().stream()
                            .forEach(task -> System.out.println("{\n" +
                                    "  ID:           " + task.getId() + "\n" +
                                    "  Titulo:       " + task.getTitle() + "\n" +
                                    "  Descripcion:  " + task.getDescription() + "\n" +
                                    "  Estado:       " + task.getStatus().getStatusTask() + "\n" +
                                    "}\n"));
                    break;
                case 5:
                    taskManager.getAllTaskComplete().stream()
                            .forEach(task -> System.out.println("{\n" +
                                    "  ID:           " + task.getId() + "\n" +
                                    "  Titulo:       " + task.getTitle() + "\n" +
                                    "  Descripcion:  " + task.getDescription() + "\n" +
                                    "  Estado:       " + task.getStatus().getStatusTask() + "\n" +
                                    "}\n"));
                    break;
                case 6:
                    System.out.println("Porfavor ingrese el ID de la tarea:");
                    id = read.nextInt();
                    
                    if (taskManager.deleteTask(id)) {
                        System.out.println("Tarea eliminada");
                        break;
                    }
                    else {
                        System.out.println("La tarea no se pudo eliminar");
                        break;
                    }
                case 7:
                    System.out.println("Porfavor ingrese el ID de la tarea:");
                    id = read.nextInt();

                    Task task = taskManager.getTask(id);
                    System.out.println("{\n" +
                            "  ID:           " + task.getId() + "\n" +
                            "  Titulo:       " + task.getTitle() + "\n" +
                            "  Descripcion:  " + task.getDescription() + "\n" +
                            "  Estado:       " + task.getStatus() + "\n" +
                            "}\n");
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }

    }
}