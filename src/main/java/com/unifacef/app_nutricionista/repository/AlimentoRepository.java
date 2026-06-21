package com.unifacef.app_nutricionista.repository;

import com.unifacef.app_nutricionista.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
}
