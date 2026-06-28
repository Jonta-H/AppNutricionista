package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Paciente;
import com.unifacef.app_nutricionista.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Transactional
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente update(Long id, Paciente atual) {
        return pacienteRepository.findById(id).map(existente -> {
            // Campos de Usuario
            if (atual.getNomeCompleto() != null) existente.setNomeCompleto(atual.getNomeCompleto());
            if (atual.getEmail() != null) existente.setEmail(atual.getEmail());
            if (atual.getSenhaHash() != null) existente.setSenhaHash(atual.getSenhaHash());
            if (atual.getTelefone() != null) existente.setTelefone(atual.getTelefone());
            if (atual.getDataNascimento() != null) existente.setDataNascimento(atual.getDataNascimento());
            if (atual.getGenero() != null) existente.setGenero(atual.getGenero());
            if (atual.getFoto() != null) existente.setFoto(atual.getFoto());
            // Campos de Paciente
            if (atual.getEndereco() != null) existente.setEndereco(atual.getEndereco());
            if (atual.getObservacoesGerais() != null) existente.setObservacoesGerais(atual.getObservacoesGerais());
            if (atual.isAtivo() != null) existente.setAtivo(atual.isAtivo());
            if (atual.getNutricionista() != null) existente.setNutricionista(atual.getNutricionista());
            return pacienteRepository.save(existente);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
