package com.unifacef.app_nutricionista.controller;

import com.unifacef.app_nutricionista.model.Nutricionista;
import com.unifacef.app_nutricionista.service.NutricionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Nutricionista> createNutricionista(@RequestBody Nutricionista nutricionista) {
        Nutricionista savedNutricionista = nutricionistaService.save(nutricionista);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNutricionista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutricionista(@PathVariable Long id) {
        if (nutricionistaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        nutricionistaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
