package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Alimento;
import com.unifacef.app_nutricionista.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository repository;

    public List<Alimento> findAll() {
        return repository.findAll();
    }

    public Optional<Alimento> findById(Long id) {
        return repository.findById(id);
    }

    public Alimento save(Alimento alimento) {
        return repository.save(alimento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
