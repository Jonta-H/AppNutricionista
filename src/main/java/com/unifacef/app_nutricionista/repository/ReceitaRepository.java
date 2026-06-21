package com.unifacef.app_nutricionista.repository;

import com.unifacef.app_nutricionista.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
