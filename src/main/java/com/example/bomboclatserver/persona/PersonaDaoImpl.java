package com.example.bomboclatserver.persona;

import com.example.bomboclatserver.exceptions.ResourceNotFoundException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PersonaDaoImpl implements PersonaDao {
    
    private final JdbcClient jdbcClient;

    public PersonaDaoImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Persona getPersona(UUID uuid) {
        return jdbcClient.sql("SELECT * FROM persona WHERE uuid = ? LIMIT 1")
                .param(uuid)
                .query(Persona.class).optional()
                .orElseThrow(() -> new ResourceNotFoundException("Persona con id: [" + uuid + "] non trovata"   ));
    }

    @Override
    public List<Persona> getPersone() {
        return jdbcClient.sql("SELECT * FROM persona").query(Persona.class).list();
    }

    @Override
    public void addPersona(Persona persona) {
        jdbcClient.sql("INSERT INTO persona VALUES (?,?,?,?)")
                .param(persona.getUuid())
                .param(persona.getNome())
                .param(persona.getCognome())
                .param(persona.getDataNascita())
                .update();
    }

    @Override
    public void updatePersona(Persona persona) {
        jdbcClient.sql("UPDATE persona SET nome = ?, cognome = ?, data_nascita = ? WHERE uuid = ?")
                .param(persona.getNome())
                .param(persona.getCognome())
                .param(persona.getDataNascita())
                .param(persona.getUuid())
                .update();
    }

    @Override
    public void deletePersona(UUID uuid) {
        jdbcClient.sql("DELETE FROM persona WHERE uuid = ?").param(uuid).update();
    }

    @Override
    public boolean existsPersona(UUID uuid) {
        return jdbcClient.sql("SELECT EXISTS(SELECT 1 from persona WHERE uuid = ?)").param(uuid).query(Boolean.class).single();
    }
}
