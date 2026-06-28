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
        return repository.findById(id).map(existente -> {
            if (atual.getDataAvaliacao() != null) existente.setDataAvaliacao(atual.getDataAvaliacao());
            if (atual.getPeso() != 0) existente.setPeso(atual.getPeso());
            if (atual.getAltura() != 0) existente.setAltura(atual.getAltura());
            if (atual.getIdadePacienteNaData() != 0) existente.setIdadePacienteNaData(atual.getIdadePacienteNaData());
            if (atual.getMedidas() != null) existente.setMedidas(atual.getMedidas());
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
