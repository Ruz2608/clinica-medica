# ClinicaMedica

Proyecto de gestión de clínica médica (Spring Boot + MySQL).

## Configuración

Este proyecto no incluye credenciales reales en el repositorio. Antes de ejecutarlo:

1. Copia la plantilla de configuración:
   ```
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
2. Edita `src/main/resources/application.properties` con tus propios datos, o define estas variables de entorno:
   - `DB_URL`
   - `DB_USER`
   - `DB_PASSWORD`
   - `APISPERU_TOKEN` (token de https://apisperu.com para la consulta de DNI)

## Ejecutar

```
./mvnw spring-boot:run
```
