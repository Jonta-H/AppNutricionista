package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Consulta;
import com.unifacef.app_nutricionista.repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    public List<Consulta> findAll() {
        return repository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Consulta save(Consulta consulta) {
        return repository.save(consulta);
    }

    @Transactional
    public Consulta update(Long id, Consulta atual) {
        return repository.findById(id).map(existente -> {
            if (atual.getDataHora() != null) existente.setDataHora(atual.getDataHora());
            if (atual.getStatus() != null) existente.setStatus(atual.getStatus());
            if (atual.getNutricionista() != null) existente.setNutricionista(atual.getNutricionista());
            if (atual.getPaciente() != null) existente.setPaciente(atual.getPaciente());
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
