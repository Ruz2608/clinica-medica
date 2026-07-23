package com.proyecto.clinica.repositories;
import com.proyecto.clinica.models.Medico;
import com.proyecto.clinica.models.Paciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> { 
    
List<Paciente> findByCodigoIdentificativo(String codigoIdentificativo);
}
