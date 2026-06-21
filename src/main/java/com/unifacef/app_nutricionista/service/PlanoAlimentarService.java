package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.PlanoAlimentar;
import com.unifacef.app_nutricionista.repository.PlanoAlimentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoAlimentarService {

    @Autowired
    private PlanoAlimentarRepository repository;

    @Autowired
    private com.unifacef.app_nutricionista.repository.RefeicaoRepository refeicaoRepository;

    public List<PlanoAlimentar> findAll() {
        return repository.findAll();
    }

    public Optional<PlanoAlimentar> findById(Long id) {
        return repository.findById(id);
    }

    public PlanoAlimentar save(PlanoAlimentar plano) {
        if (plano.getRefeicoes() != null) {
            java.util.List<com.unifacef.app_nutricionista.model.Refeicao> managedRefeicoes = new java.util.ArrayList<>();
            for (com.unifacef.app_nutricionista.model.Refeicao ref : plano.getRefeicoes()) {
                if (ref.getId() != null) {
                    refeicaoRepository.findById(ref.getId()).ifPresent(managedRefeicoes::add);
                } else {
                    managedRefeicoes.add(ref);
                }
            }
            plano.setRefeicoes(managedRefeicoes);
        }
        return repository.save(plano);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
