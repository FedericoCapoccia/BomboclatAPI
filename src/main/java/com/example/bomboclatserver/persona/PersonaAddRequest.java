package com.example.bomboclatserver.persona;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PersonaAddRequest(
        @NotNull(message = "Nessun nome inserito") String nome,
        @NotNull(message = "Nessun cognome inserito") String cognome,
        @NotNull(message = "Nessuna data di nascita inserita") LocalDate dataNascita) {
}
