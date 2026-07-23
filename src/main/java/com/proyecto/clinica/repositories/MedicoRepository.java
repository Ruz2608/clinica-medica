package com.proyecto.clinica.repositories;
import com.proyecto.clinica.models.Medico;
import com.proyecto.clinica.models.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> { 
    // Spring Data JPA lo hace por ti con el nombre del método
List<Paciente> findByCodigoIdentificativo(String codigoIdentificativo);
}
