package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Consulta;
import com.unifacef.app_nutricionista.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@Valid @RequestBody Consulta consulta) {
        Consulta saved = service.save(consulta);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        Consulta updated = service.update(id, consulta);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
