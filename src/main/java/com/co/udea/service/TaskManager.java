package com.co.udea.service;

import com.co.udea.model.Note;

import java.util.LinkedList;
import java.util.Queue;

public class TaskManager {

    Queue<Note> cola = new LinkedList<>();
    public void createNote(int id, String title, String description) {
        Note note = new Note(id, title, description);
        cola.offer(note);
    }

    public Note getLastNoteCreate() {
        return cola.peek();
    }
}
