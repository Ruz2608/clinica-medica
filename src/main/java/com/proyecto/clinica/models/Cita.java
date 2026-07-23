package com.proyecto.clinica.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "cita")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String estado; 

    
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "numeroHistorial")
    private Paciente paciente;

    
    @ManyToOne
    @JoinColumn(name = "medico_id", referencedColumnName = "codigoIdentificativo")
    private Medico medico;
}
