package com.proyecto.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.proyecto.clinica.models.Paciente;
import com.proyecto.clinica.repositories.PacienteRepository;
import java.util.Map;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private RestTemplate restTemplate;

    // El token ya NO va escrito aquí. Se lee desde application.properties,
    // que a su vez lo toma de la variable de entorno APISPERU_TOKEN.
    @Value("${apisperu.token:}")
    private String apiToken;

    public Paciente registrarConDni(String dni, String numeroHistorial) {
        String url = "https://dniruc.apisperu.com/api/v1/dni/" + dni + "?token=" + apiToken;

        try {
            // Hacemos la petición y recibimos la respuesta en un Mapa (llave: valor)
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            Paciente nuevo = new Paciente();
            nuevo.setNumeroHistorial(numeroHistorial); // El número de historial es único 

            if (response != null) {
                // Extraemos los datos según los nombres que usa la API
                nuevo.setNombre((String) response.get("nombres"));
                nuevo.setPrimerApellido((String) response.get("apellidoPaterno"));
                // Puedes agregar dirección si la API la proporciona 
            }

            return pacienteRepository.save(nuevo);
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la API de DNI: " + e.getMessage());
        }
    }
    
}