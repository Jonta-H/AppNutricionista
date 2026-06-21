package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.PlanoAlimentar;
import com.unifacef.app_nutricionista.service.PlanoAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos-alimentares")
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarService service;

    @GetMapping
    public List<PlanoAlimentar> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlanoAlimentar> create(@RequestBody PlanoAlimentar plano) {
        PlanoAlimentar saved = service.save(plano);
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
