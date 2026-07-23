package com.proyecto.clinica.controllers;

import com.proyecto.clinica.models.Enfermedad;
import com.proyecto.clinica.models.Paciente;
import com.proyecto.clinica.repositories.EnfermedadRepository;
import com.proyecto.clinica.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/enfermedades")
public class EnfermedadController {

    @Autowired
    private EnfermedadRepository enfermedadRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping("/registrar")
    public Enfermedad registrar(@RequestBody Enfermedad enfermedad) {
        return enfermedadRepository.save(enfermedad);
    }

    @PutMapping("/{codigoEnfermedad}/asignar-a/{numeroHistorial}")
    public Paciente asignarEnfermedad(@PathVariable String codigoEnfermedad, @PathVariable String numeroHistorial) {
        Paciente paciente = pacienteRepository.findById(numeroHistorial)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Enfermedad enfermedad = enfermedadRepository.findById(codigoEnfermedad)
            .orElseThrow(() -> new RuntimeException("Enfermedad no encontrada"));

        paciente.getEnfermedades().add(enfermedad); 
        return pacienteRepository.save(paciente);
    }
}
