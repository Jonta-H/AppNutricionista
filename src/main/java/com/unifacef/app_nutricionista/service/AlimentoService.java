package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Alimento;
import com.unifacef.app_nutricionista.repository.AlimentoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Alimento save(Alimento alimento) {
        return repository.save(alimento);
    }

    @Transactional
    public Alimento update(Long id, Alimento atual) {
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
