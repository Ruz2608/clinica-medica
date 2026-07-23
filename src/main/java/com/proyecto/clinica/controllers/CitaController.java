package com.proyecto.clinica.controllers;

import com.proyecto.clinica.models.Cita;
import com.proyecto.clinica.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*") // Para que tu HTML se conecte sin problemas
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCita(@RequestBody Cita cita) {
        try {
            cita.setEstado("PENDIENTE"); // Toda cita nueva empieza como pendiente
            citaRepository.save(cita);
            return ResponseEntity.ok().build(); // Respuesta vacía y segura
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al agendar: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Map<String, Object>> listarCitas() {
        // Mapeamos manualmente para enviar un JSON limpio al Front sin relaciones pesadas
        return citaRepository.findAll().stream().map(c -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("fecha", c.getFecha().toString());
            map.put("hora", c.getHora().toString());
            map.put("motivo", c.getMotivo());
            map.put("estado", c.getEstado());
            map.put("paciente", c.getPaciente().getNombre() + " " + c.getPaciente().getPrimerApellido());
            map.put("medico", c.getMedico().getNombre() + " " + c.getMedico().getPrimerApellido());
            return map;
        }).collect(Collectors.toList());
    }
    // --- COMPLETAR CITA ---
    @PutMapping("/{id}/completar")
    public ResponseEntity<?> completarCita(@PathVariable Long id) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado("COMPLETADA");
            citaRepository.save(cita);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // --- CANCELAR CITA ---
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        return citaRepository.findById(id).map(cita -> {
            cita.setEstado("CANCELADA");
            citaRepository.save(cita);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
