package com.proyecto.clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.clinica.models.Paciente;
import com.proyecto.clinica.models.Medico;
import com.proyecto.clinica.repositories.PacienteRepository;
import com.proyecto.clinica.repositories.MedicoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    // --- MÉTODO CORREGIDO PARA REGISTRO MANUAL ---
    // Recibe el objeto Paciente completo desde el Frontend
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Paciente paciente) {
        pacienteRepository.save(paciente);
        return ResponseEntity.ok().build(); // Devolver un 200 OK vacío es mucho más seguro

        }
    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listarPacientes() {
    try {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return ResponseEntity.ok(pacientes);
    } catch (Exception e) {
        return ResponseEntity.internalServerError().build();
    }
}

    // --- ASIGNAR MÉDICO ---
    @PutMapping("/{numeroHistorial}/asignar-medico/{codigoMedico}")
    public Paciente asignarMedico(
            @PathVariable String numeroHistorial, 
            @PathVariable String codigoMedico) {
        
        Paciente paciente = pacienteRepository.findById(numeroHistorial)
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
            
        Medico medico = medicoRepository.findById(codigoMedico)
            .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        paciente.setMedico(medico);
        return pacienteRepository.save(paciente);
    }

    // --- OBTENER HISTORIAL ---
    @GetMapping("/historial/{numeroHistorial}")
public ResponseEntity<?> obtenerHistorial(@PathVariable String numeroHistorial) {
    return pacienteRepository.findById(numeroHistorial)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}
}

