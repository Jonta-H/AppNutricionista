package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Alimento;
import com.unifacef.app_nutricionista.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService service;

    @GetMapping
    public List<Alimento> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alimento> create(@RequestBody Alimento alimento) {
        Alimento saved = service.save(alimento);
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
