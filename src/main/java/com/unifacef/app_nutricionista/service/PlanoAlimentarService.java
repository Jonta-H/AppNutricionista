package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.PlanoAlimentar;
import com.unifacef.app_nutricionista.model.Refeicao;
import com.unifacef.app_nutricionista.repository.PlanoAlimentarRepository;
import com.unifacef.app_nutricionista.repository.RefeicaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoAlimentarService {

    @Autowired
    private PlanoAlimentarRepository repository;

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    public List<PlanoAlimentar> findAll() {
        return repository.findAll();
    }

    public Optional<PlanoAlimentar> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public PlanoAlimentar save(PlanoAlimentar plano) {
        if (plano.getRefeicoes() != null) {
            List<Refeicao> managedRefeicoes = new ArrayList<>();
            for (Refeicao ref : plano.getRefeicoes()) {
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

    @Transactional
    public PlanoAlimentar update(Long id, PlanoAlimentar atual) {
        return repository.findById(id).map(existente -> {
            if (atual.getTitulo() != null) existente.setTitulo(atual.getTitulo());
            if (atual.getDataCriacao() != null) existente.setDataCriacao(atual.getDataCriacao());
            if (atual.getObjetivo() != null) existente.setObjetivo(atual.getObjetivo());
            if (atual.isAtivo() != null) existente.setAtivo(atual.isAtivo());
            if (atual.getDataValidade() != null) existente.setDataValidade(atual.getDataValidade());
            if (atual.getPaciente() != null) existente.setPaciente(atual.getPaciente());
            if (atual.getRefeicoes() != null && !atual.getRefeicoes().isEmpty()) {
                List<Refeicao> managedRefeicoes = new ArrayList<>();
                for (Refeicao ref : atual.getRefeicoes()) {
                    if (ref.getId() != null) {
                        refeicaoRepository.findById(ref.getId()).ifPresent(managedRefeicoes::add);
                    } else {
                        managedRefeicoes.add(ref);
                    }
                }
                existente.getRefeicoes().clear();
                existente.getRefeicoes().addAll(managedRefeicoes);
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
