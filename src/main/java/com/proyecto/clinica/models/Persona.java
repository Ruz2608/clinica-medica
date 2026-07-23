package com.proyecto.clinica.models;


import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@MappedSuperclass 
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
// ELIMINAMOS @Data para que no genere métodos circulares
public abstract class Persona {
    private String dni;
    private String nombre;
    private String primerApellido;
    private LocalDate fechaNacimiento;
    private String direccion;
}