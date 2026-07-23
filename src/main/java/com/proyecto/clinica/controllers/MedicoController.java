package com.proyecto.clinica.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.clinica.models.Medico;
import com.proyecto.clinica.repositories.MedicoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/registrar")
public ResponseEntity<?> registrarMedico(@RequestBody Medico medico) {
    try {
        String tipo = medico.getTipoContratacion();
        
        
        if (tipo == null || (!tipo.equals("NOMBRADO") && 
                             !tipo.equals("CONTRATADO") && 
                             !tipo.equals("CONSULTOR"))) {
            return ResponseEntity.badRequest().body("Tipo de contratación inválido: " + tipo);
        }
        Medico medicoGuardado = medicoRepository.save(medico);

        return ResponseEntity.ok(medicoGuardado);

    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
    }
}
@GetMapping("/listar")
public List<Map<String, String>> listarParaSelect() {
    return medicoRepository.findAll().stream().map(m -> {
        Map<String, String> map = new java.util.HashMap<>();
        map.put("codigoIdentificativo", m.getCodigoIdentificativo());
        map.put("nombre", m.getNombre() + " " + m.getPrimerApellido());
        map.put("especialidad", m.getEspecialidad());
        return map;
    }).collect(java.util.stream.Collectors.toList());
}
}
