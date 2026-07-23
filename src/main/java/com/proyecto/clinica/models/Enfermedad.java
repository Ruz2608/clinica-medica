package com.proyecto.clinica.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enfermedad {
    @Id
    @JsonProperty("codigo")
    private String codigo; 
    
    private String nombre; 
    private String sistemaCorporal; 
    private String descripcion; 
    
    @JsonIgnore
    @ManyToMany(mappedBy = "enfermedades")
    private List<Paciente> pacientes;
}
