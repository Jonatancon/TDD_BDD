package com.co.udea.model;

public enum Status {
    INIT("Creada"),
    PROGRESS("En progreso"),
    CLOSE("Cerrada");

    private final String statusTask;

    Status(String statusTask) {
        this.statusTask = statusTask;
    }

    public String getStatusTask() {
        return statusTask;
    }
}
