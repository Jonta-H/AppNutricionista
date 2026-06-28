package com.unifacef.app_nutricionista.service;

import com.unifacef.app_nutricionista.model.Usuario;
import com.unifacef.app_nutricionista.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Long id, Usuario atual) {
        return usuarioRepository.findById(id).map(existente -> {
            if (atual.getNomeCompleto() != null) existente.setNomeCompleto(atual.getNomeCompleto());
            if (atual.getEmail() != null) existente.setEmail(atual.getEmail());
            if (atual.getSenhaHash() != null) existente.setSenhaHash(atual.getSenhaHash());
            if (atual.getTelefone() != null) existente.setTelefone(atual.getTelefone());
            if (atual.getDataNascimento() != null) existente.setDataNascimento(atual.getDataNascimento());
            if (atual.getGenero() != null) existente.setGenero(atual.getGenero());
            if (atual.getFoto() != null) existente.setFoto(atual.getFoto());
            return usuarioRepository.save(existente);
        }).orElse(null);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
