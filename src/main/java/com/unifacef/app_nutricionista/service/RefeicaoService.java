package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Refeicao;
import com.unifacef.app_nutricionista.repository.RefeicaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefeicaoService {

    @Autowired
    private RefeicaoRepository repository;

    public List<Refeicao> findAll() {
        return repository.findAll();
    }

    public Optional<Refeicao> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Refeicao save(Refeicao refeicao) {
        return repository.save(refeicao);
    }

    @Transactional
    public Refeicao update(Long id, Refeicao atual) {
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
