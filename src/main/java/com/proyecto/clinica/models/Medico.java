package com.proyecto.clinica.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


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
@ToString(exclude = "pacientes")
@EqualsAndHashCode(callSuper = true, exclude = "pacientes")
public class Medico extends Persona {
    
    @Id
    private String codigoIdentificativo;
    
    private String centroEstudios;
    
    private String especialidad; 
    
    private String tipoContratacion;

    @OneToMany(mappedBy = "medico")
    @JsonIgnore 
    private List<Paciente> pacientes;
}
