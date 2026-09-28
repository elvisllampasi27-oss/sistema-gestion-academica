# Changelog

Todos los cambios notables de este proyecto se documentan en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/),
y este proyecto se adhiere a [Semantic Versioning](https://semver.org/lang/es/).
---
## [1.5.0] - 2026-09-27

### Agregado
- Módulo de reportes con consultas agregadas.
- Reporte de promedio por estudiante.
- Reporte de promedio por curso con listado de calificaciones.
- Ranking de cursos por número de matrículas.
- Resumen general del sistema.
- Integración en menú principal.

---
## [1.4.0] - 2026-09-27

### Agregado
- Módulo de gestión de calificaciones (CRUD completo).
- Validación cruzada: la matrícula debe existir y estar ACTIVA.
- Validación de tipo (`PARCIAL`, `FINAL`, `PRACTICA`).
- Validación de nota entre 0 y 20.
- Validación de formato de fecha (`YYYY-MM-DD`).
- Cálculo de promedio de notas por matrícula.
- Persistencia en `data/calificaciones.json`.

---
## [1.3.0] - 2026-09-27

### Agregado
- Módulo de gestión de matrículas (CRUD completo).
- Validación cruzada: el estudiante y el curso deben existir.
- Validación de no duplicidad: un estudiante no puede tener dos matrículas ACTIVAS en el mismo curso.
- Validación de formato de fecha (`YYYY-MM-DD`).
- Validación de estado (`ACTIVA`, `RETIRADA`, `COMPLETADA`).
- Persistencia en `data/matriculas.json`.

## [1.2.0] - 2026-09-27
---
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