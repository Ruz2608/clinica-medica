package com.proyecto.clinica.repositories;

import com.proyecto.clinica.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Aquí podrías agregar búsquedas personalizadas más adelante si lo deseas
}