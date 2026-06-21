package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Refeicao;
import com.unifacef.app_nutricionista.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refeicoes")
public class RefeicaoController {

    @Autowired
    private RefeicaoService service;

    @GetMapping
    public List<Refeicao> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refeicao> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Refeicao> create(@RequestBody Refeicao refeicao) {
        Refeicao saved = service.save(refeicao);
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
