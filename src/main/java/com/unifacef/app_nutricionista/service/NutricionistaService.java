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
        if (nutricionistaRepository.existsById(id)) {
            atual.setId(id);
            return nutricionistaRepository.save(atual);
        }
        return null;
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
