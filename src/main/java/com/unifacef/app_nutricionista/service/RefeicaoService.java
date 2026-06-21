package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Refeicao;
import com.unifacef.app_nutricionista.repository.RefeicaoRepository;
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

    public Refeicao save(Refeicao refeicao) {
        return repository.save(refeicao);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
