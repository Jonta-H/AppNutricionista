package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Consulta;
import com.unifacef.app_nutricionista.repository.ConsultaRepository;
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

    public Consulta save(Consulta consulta) {
        return repository.save(consulta);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
