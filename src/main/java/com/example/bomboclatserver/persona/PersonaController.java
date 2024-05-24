package com.example.bomboclatserver.persona;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persone")
public class PersonaController {
    
    private final PersonaDao personaDao;

    public PersonaController(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }
    
    @GetMapping("{uuid}")
    public ResponseEntity<Persona> getPersona(@PathVariable UUID uuid) {
        Persona persona = personaDao.getPersona(uuid);
        return ResponseEntity.ok(persona);
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
    public ResponseEntity<?> deletePersona(@PathVariable UUID uuid) {
        if (!personaDao.existsPersona(uuid)) {
            return ResponseEntity.notFound().build();
        }
        personaDao.deletePersona(uuid);
        return ResponseEntity.noContent().build();
    }
}
