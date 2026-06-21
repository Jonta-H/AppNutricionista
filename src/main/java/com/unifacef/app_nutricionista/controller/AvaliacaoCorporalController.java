package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.AvaliacaoCorporal;
import com.unifacef.app_nutricionista.service.AvaliacaoCorporalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes-corporais")
public class AvaliacaoCorporalController {

    @Autowired
    private AvaliacaoCorporalService service;

    @GetMapping
    public List<AvaliacaoCorporal> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoCorporal> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoCorporal> create(@RequestBody AvaliacaoCorporal avaliacao) {
        AvaliacaoCorporal saved = service.save(avaliacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
