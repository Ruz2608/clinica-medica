package com.proyecto.clinica.repositories;

import com.proyecto.clinica.models.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, String> {
}
