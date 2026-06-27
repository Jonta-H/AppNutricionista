package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.AvaliacaoCorporal;
import com.unifacef.app_nutricionista.repository.AvaliacaoCorporalRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public AvaliacaoCorporal save(AvaliacaoCorporal avaliacao) {
        return repository.save(avaliacao);
    }

    @Transactional
    public AvaliacaoCorporal update(Long id, AvaliacaoCorporal atual) {
        if (repository.existsById(id)) {
            atual.setId(id);
            return repository.save(atual);
        }
        return null;
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
