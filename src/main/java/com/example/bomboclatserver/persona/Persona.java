package com.example.bomboclatserver.persona;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Persona {
    
    private UUID uuid;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;

    public Persona() {
    }

    public Persona(UUID uuid, String nome, String cognome, LocalDate dataNascita) {
        this.uuid = uuid;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;
        return Objects.equals(uuid, persona.uuid) && Objects.equals(nome, persona.nome) && Objects.equals(cognome, persona.cognome) && Objects.equals(dataNascita, persona.dataNascita);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(uuid);
        result = 31 * result + Objects.hashCode(nome);
        result = 31 * result + Objects.hashCode(cognome);
        result = 31 * result + Objects.hashCode(dataNascita);
        return result;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "uuid=" + uuid +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                '}';
    }
}
