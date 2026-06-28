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
        return repository.findById(id).map(existente -> {
            if (atual.getNome() != null) existente.setNome(atual.getNome());
            if (atual.getCalorias() != 0) existente.setCalorias(atual.getCalorias());
            if (atual.getCarboidratos() != 0) existente.setCarboidratos(atual.getCarboidratos());
            if (atual.getProteinas() != 0) existente.setProteinas(atual.getProteinas());
            if (atual.getGorduras() != 0) existente.setGorduras(atual.getGorduras());
            if (atual.getFibras() != 0) existente.setFibras(atual.getFibras());
            if (atual.getPorcaoReferencia() != 0) existente.setPorcaoReferencia(atual.getPorcaoReferencia());
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
