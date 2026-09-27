# Changelog

Todos los cambios notables de este proyecto se documentan en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/),
y este proyecto se adhiere a [Semantic Versioning](https://semver.org/lang/es/).

---

## [1.2.0] - 2026-09-27

### Agregado
- Módulo de gestión de cursos (CRUD completo).
- Validación de formato de código de curso (`XXX-###`).
- Validación de créditos (entre 1 y 10).
- Persistencia en `data/cursos.json`.

---

## [1.1.1] - 2026-09-27

### Corregido
- Implementación completa del módulo de gestión de docentes.
- Validaciones de reglas de negocio para docentes.

### Agregado
- Modelo `Docente` con atributo `especialidad`.
- `DocenteRepository` con persistencia en JSON.
- `DocenteService` con validaciones.
- `DocenteUI` con CRUD completo.

---

## [1.1.0] - 2026-09-27

### Agregado
- Estructura inicial del módulo de gestión de docentes (incompleto).

---

## [1.0.0] - 2026-09-27

### Agregado
- Estructura inicial del proyecto Maven con Java 21.
- Arquitectura de 3 capas (Presentación, Negocio, Datos).
- Módulo de gestión de estudiantes (CRUD completo).
- Persistencia en archivos JSON con Gson.
- Validaciones de reglas de negocio.
- README con documentación del proyecto.
- Licencia MIT.