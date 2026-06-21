package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Receita;
import com.unifacef.app_nutricionista.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository repository;

    public List<Receita> findAll() {
        return repository.findAll();
    }

    public Optional<Receita> findById(Long id) {
        return repository.findById(id);
    }

    public Receita save(Receita receita) {
        return repository.save(receita);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
