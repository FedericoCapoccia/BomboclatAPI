package com.example.bomboclatserver.persona;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController @RequestMapping("/api/persone")
public class PersonaController {
    
    private final PersonaDao personaDao;

    public PersonaController(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }
    
    @GetMapping("{uuid}")
    public ResponseEntity<?> getPersona(@PathVariable String uuid) {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("UUID non valido");
        }
        Optional<Persona> persona = personaDao.getPersona(id);
        return persona.isPresent() ?
                ResponseEntity.ok(persona) :
                ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<Persona>> getPersone() {
        List<Persona> persone =  personaDao.getPersone();
        return ResponseEntity.ok(persone);
    }
    
    @PostMapping
    public ResponseEntity<?> addPersona(@Valid @RequestBody PersonaAddRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        UUID uuid = UUID.randomUUID();
        Persona persona = new Persona(uuid, request.nome(), request.cognome(), request.dataNascita());
        System.out.println("ADD: ["+persona+"]");
        personaDao.addPersona(persona);
        return ResponseEntity.created(URI.create("/api/persone/"+uuid)).build();
    }
    
    @DeleteMapping("{uuid}")
    public ResponseEntity<?> deletePersona(@PathVariable String uuid) {
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("UUID non valido");
        }
        
        if (!personaDao.existsPersona(id)) {
            return ResponseEntity.notFound().build();
        }
        personaDao.deletePersona(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{uuid}")
    public ResponseEntity<?> updatePersona(@PathVariable String uuid, @RequestBody PersonaUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }
        UUID id;
        try {
            id = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("UUID non valido");
        }
        
        Persona persona = new Persona(id, request.nome(), request.cognome(), request.dataNascita());
        personaDao.updatePersona(persona);
        return ResponseEntity.noContent().build();
    }
}
