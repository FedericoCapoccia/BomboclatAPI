package com.example.bomboclatserver.persona;

import java.time.LocalDate;

public record PersonaUpdateRequest(String nome, String cognome, LocalDate dataNascita) {
}
