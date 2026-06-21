package com.unifacef.app_nutricionista.repository;

import com.unifacef.app_nutricionista.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
