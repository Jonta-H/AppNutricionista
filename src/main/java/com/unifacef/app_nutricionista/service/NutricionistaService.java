package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.exception.BusinessRuleException;
import com.unifacef.app_nutricionista.model.Nutricionista;
import com.unifacef.app_nutricionista.model.Paciente;
import com.unifacef.app_nutricionista.repository.NutricionistaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutricionistaService {

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    public List<Nutricionista> findAll() {
        return nutricionistaRepository.findAll();
    }

    public Optional<Nutricionista> findById(Long id) {
        return nutricionistaRepository.findById(id);
    }

    @Transactional
    public Nutricionista save(Nutricionista nutricionista) {
        return nutricionistaRepository.save(nutricionista);
    }

    @Transactional
    public Nutricionista update(Long id, Nutricionista atual) {
        return nutricionistaRepository.findById(id).map(existente -> {
            // Campos de Usuario
            if (atual.getNomeCompleto() != null) existente.setNomeCompleto(atual.getNomeCompleto());
            if (atual.getEmail() != null) existente.setEmail(atual.getEmail());
            if (atual.getSenhaHash() != null) existente.setSenhaHash(atual.getSenhaHash());
            if (atual.getTelefone() != null) existente.setTelefone(atual.getTelefone());
            if (atual.getDataNascimento() != null) existente.setDataNascimento(atual.getDataNascimento());
            if (atual.getGenero() != null) existente.setGenero(atual.getGenero());
            if (atual.getFoto() != null) existente.setFoto(atual.getFoto());
            // Campos de Nutricionista
            if (atual.getCrn() != null) existente.setCrn(atual.getCrn());
            if (atual.getNomeClinica() != null) existente.setNomeClinica(atual.getNomeClinica());
            if (atual.getLogoTipoClinica() != null) existente.setLogoTipoClinica(atual.getLogoTipoClinica());
            return nutricionistaRepository.save(existente);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteById(Long id) {
        Optional<Nutricionista> opt = nutricionistaRepository.findById(id);
        if (opt.isPresent()) {
            Nutricionista nutricionista = opt.get();
            boolean hasActivePatients = nutricionista.getPacientes().stream()
                    .anyMatch(Paciente::isAtivo);
            if (hasActivePatients) {
                throw new BusinessRuleException("Não é possível excluir um nutricionista com pacientes ativos.");
            }
            nutricionistaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
