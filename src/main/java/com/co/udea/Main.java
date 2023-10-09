package com.co.udea;
import com.co.udea.vista.ConfigurationTask;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConfigurationTask configurationTask = new ConfigurationTask();
        Scanner read = new Scanner(System.in);
        int opcion;

        do {
            configurationTask.imprimirMenu();
            opcion = read.nextInt();

            switch (opcion) {
                case 1 -> configurationTask.saveData();
                case 2 -> configurationTask.init();
                case 3 -> configurationTask.close();
                case 4 -> configurationTask.getTaskPending();
                case 5 -> configurationTask.getTaskComplete();
                case 6 -> configurationTask.delete();
                case 7 -> configurationTask.get();
                default -> configurationTask.retry();
            }

        }while(opcion!=8);

    }
}