package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Paciente;
import com.unifacef.app_nutricionista.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (pacienteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
