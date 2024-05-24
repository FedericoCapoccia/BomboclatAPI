package com.example.bomboclatserver.persona;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonaDao {
    Optional<Persona> getPersona(UUID uuid);
    List<Persona> getPersone();
    void addPersona(Persona persona);
    void updatePersona(Persona persona);
    void deletePersona(UUID uuid);
    boolean existsPersona(UUID uuid);
}
