package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Receita;
import com.unifacef.app_nutricionista.repository.ReceitaRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Receita save(Receita receita) {
        return repository.save(receita);
    }

    @Transactional
    public Receita update(Long id, Receita atual) {
        return repository.findById(id).map(existente -> {
            if (atual.getNome() != null) existente.setNome(atual.getNome());
            if (atual.getCategoria() != null) existente.setCategoria(atual.getCategoria());
            if (atual.getModoPreparo() != null) existente.setModoPreparo(atual.getModoPreparo());
            if (atual.getRendimento() != 0) existente.setRendimento(atual.getRendimento());
            if (atual.getIngredientes() != null && !atual.getIngredientes().isEmpty()) {
                existente.getIngredientes().clear();
                existente.getIngredientes().addAll(atual.getIngredientes());
            }
            return repository.save(existente);
        }).orElse(null);
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
