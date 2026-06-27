package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Refeicao;
import com.unifacef.app_nutricionista.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/refeicoes")
public class RefeicaoController {

    @Autowired
    private RefeicaoService service;

    @GetMapping
    public List<Refeicao> getAllRefeicoes() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refeicao> getRefeicaoById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Refeicao> createRefeicao(@RequestBody Refeicao refeicao) {
        Refeicao saved = service.save(refeicao);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Refeicao> updateRefeicao(@PathVariable Long id, @RequestBody Refeicao refeicao) {
        Refeicao updated = service.update(id, refeicao);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefeicao(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
