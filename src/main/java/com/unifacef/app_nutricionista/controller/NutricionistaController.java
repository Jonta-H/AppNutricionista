package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Nutricionista;
import com.unifacef.app_nutricionista.service.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {

    @Autowired
    private NutricionistaService nutricionistaService;

    @GetMapping
    public List<Nutricionista> getAllNutricionistas() {
        return nutricionistaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricionista> getNutricionistaById(@PathVariable Long id) {
        return nutricionistaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nutricionista> createNutricionista(@Valid @RequestBody Nutricionista nutricionista) {
        Nutricionista saved = nutricionistaService.save(nutricionista);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Nutricionista> updateNutricionista(@PathVariable Long id, @RequestBody Nutricionista nutricionista) {
        Nutricionista updated = nutricionistaService.update(id, nutricionista);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        if (nutricionistaService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
