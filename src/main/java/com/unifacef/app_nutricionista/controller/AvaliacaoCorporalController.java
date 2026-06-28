package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.AvaliacaoCorporal;
import com.unifacef.app_nutricionista.service.AvaliacaoCorporalService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoCorporalController {

    @Autowired
    private AvaliacaoCorporalService service;

    @GetMapping
    public List<AvaliacaoCorporal> getAllAvaliacoes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoCorporal> getAvaliacaoById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoCorporal> createAvaliacao(@Valid @RequestBody AvaliacaoCorporal avaliacao) {
        AvaliacaoCorporal saved = service.save(avaliacao);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AvaliacaoCorporal> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoCorporal avaliacao) {
        AvaliacaoCorporal updated = service.update(id, avaliacao);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
