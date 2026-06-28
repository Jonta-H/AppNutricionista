package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.PlanoAlimentar;
import com.unifacef.app_nutricionista.service.PlanoAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/planos-alimentares")
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarService service;

    @GetMapping
    public List<PlanoAlimentar> getAllPlanosAlimentares() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> getPlanoAlimentarById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlanoAlimentar> createPlanoAlimentar(@Valid @RequestBody PlanoAlimentar plano) {
        PlanoAlimentar saved = service.save(plano);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> updatePlanoAlimentar(@PathVariable Long id, @RequestBody PlanoAlimentar plano) {
        PlanoAlimentar updated = service.update(id, plano);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanoAlimentar(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
