package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.AvaliacaoCorporal;
import com.unifacef.app_nutricionista.repository.AvaliacaoCorporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoCorporalService {

    @Autowired
    private AvaliacaoCorporalRepository repository;

    public List<AvaliacaoCorporal> findAll() {
        return repository.findAll();
    }

    public Optional<AvaliacaoCorporal> findById(Long id) {
        return repository.findById(id);
    }

    public AvaliacaoCorporal save(AvaliacaoCorporal avaliacao) {
        return repository.save(avaliacao);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
