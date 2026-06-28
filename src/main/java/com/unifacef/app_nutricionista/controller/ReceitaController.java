package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Receita;
import com.unifacef.app_nutricionista.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService service;

    @GetMapping
    public List<Receita> getAllReceitas() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Receita> createReceita(@Valid @RequestBody Receita receita) {
        Receita saved = service.save(receita);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody Receita receita) {
        Receita updated = service.update(id, receita);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
