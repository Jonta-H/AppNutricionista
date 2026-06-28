package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Alimento;
import com.unifacef.app_nutricionista.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService service;

    @GetMapping
    public List<Alimento> getAllAlimentos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alimento> createAlimento(@Valid @RequestBody Alimento alimento) {
        Alimento saved = service.save(alimento);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alimento> updateAlimento(@PathVariable Long id, @RequestBody Alimento alimento) {
        Alimento updated = service.update(id, alimento);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimento(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

