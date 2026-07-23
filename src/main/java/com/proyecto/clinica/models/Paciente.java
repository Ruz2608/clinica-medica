package com.proyecto.clinica.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@ToString(exclude = {"medico", "enfermedades"})
@EqualsAndHashCode(callSuper = true, exclude = {"medico", "enfermedades"})
public class Paciente extends Persona {

    @Id
    private String numeroHistorial; 

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @JsonIgnoreProperties("pacientes")
    private Medico medico; 

    @ManyToMany
    @JoinTable(
        name = "paciente_enfermedad",
        joinColumns = @JoinColumn(name = "paciente_id"),
        inverseJoinColumns = @JoinColumn(name = "enfermedad_id")
    )
    @JsonIgnoreProperties("pacientes") 
    private List<Enfermedad> enfermedades = new ArrayList<>(); 
}
