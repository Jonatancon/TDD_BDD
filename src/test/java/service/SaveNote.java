package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.co.udea.service.TaskManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SaveNote {

    TaskManager task;

    @Given("that I am a user on the notes page")
    public void que_soy_un_usuario_en_la_pagina_de_notas() {
        task = new TaskManager();
    }

    @When("I create a new note with the following details: Id {int}, Title {string}, Description {string}")
    public void creoUnaNuevaNotaConLosSiguientesDetallesIdTituloDescripcion(int id, String title, String description) {
        task.createNote(id, title, description);
    }

    @Then("the note is successfully created")
    public void laNotaSeCreaConExito() {
        assertNotNull(task.getLastNoteCreate());
    }

    @And("the status of the note is {string}")
    public void elEstadoDeLaNotaEs(String status) {
        assertEquals(status, task.getLastNoteCreate().getStatus().getStatusTask());
    }
}
