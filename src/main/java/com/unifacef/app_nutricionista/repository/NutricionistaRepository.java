package com.unifacef.app_nutricionista.repository;

import com.unifacef.app_nutricionista.model.Nutricionista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
}
