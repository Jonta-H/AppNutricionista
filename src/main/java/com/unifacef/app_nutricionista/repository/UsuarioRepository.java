package com.unifacef.app_nutricionista.repository;

import com.unifacef.app_nutricionista.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
