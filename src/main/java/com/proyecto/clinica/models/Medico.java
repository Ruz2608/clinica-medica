package com.proyecto.clinica.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Importaciones necesarias para el blindaje
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
// CLAVE: Excluimos 'pacientes' para evitar el bucle infinito y el apagado
@ToString(exclude = "pacientes")
@EqualsAndHashCode(callSuper = true, exclude = "pacientes")
public class Medico extends Persona {
    
    @Id
    private String codigoIdentificativo;
    
    private String centroEstudios;
    
    private String especialidad; // Asegúrate de tener esto si lo usas en el Front
    
    private String tipoContratacion;

    @OneToMany(mappedBy = "medico")
    @JsonIgnore 
    private List<Paciente> pacientes;
}