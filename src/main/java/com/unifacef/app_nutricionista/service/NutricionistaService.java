package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Nutricionista;
import com.unifacef.app_nutricionista.repository.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public List<Nutricionista> findAll() {
        return nutricionistaRepository.findAll();
    }

    public Optional<Nutricionista> findById(Long id) {
        return nutricionistaRepository.findById(id);
    }

    public Nutricionista save(Nutricionista nutricionista) {
        return nutricionistaRepository.save(nutricionista);
    }

    public void deleteById(Long id) {
        nutricionistaRepository.deleteById(id);
    }
}
