package com.proyecto.clinica.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Importamos las anotaciones específicas para evitar el bucle
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
// Excluimos las relaciones de estos métodos automáticos para que no se apague el backend
@ToString(exclude = {"medico", "enfermedades"})
@EqualsAndHashCode(callSuper = true, exclude = {"medico", "enfermedades"})
public class Paciente extends Persona {

    @Id
    private String numeroHistorial; // Código que identifica unívocamente al paciente

    @ManyToOne
    @JoinColumn(name = "medico_id")
    // Esto evita la recursión infinita al convertir a JSON para el navegador
    @JsonIgnoreProperties("pacientes")
    private Medico medico; // Un paciente sólo puede tener asignado un médico

    @ManyToMany
    @JoinTable(
        name = "paciente_enfermedad",
        joinColumns = @JoinColumn(name = "paciente_id"),
        inverseJoinColumns = @JoinColumn(name = "enfermedad_id")
    )
    @JsonIgnoreProperties("pacientes") // Evita el bucle infinito al convertir a JSON
    private List<Enfermedad> enfermedades = new ArrayList<>(); 
}