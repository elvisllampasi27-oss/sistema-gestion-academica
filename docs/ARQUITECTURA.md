# 🏗️ Arquitectura del Sistema

Documento de diseño arquitectónico del Sistema de Gestión Académica.

---

## 📐 Propuesta 1: Arquitectura de 3 capas (implementada)

Esta es la arquitectura que se encuentra actualmente implementada en el proyecto.

### Diagrama

```
┌──────────────────────────────────────────────┐
│              CAPA DE PRESENTACIÓN            │
│  ┌────────────────────────────────────────┐  │
│  │  EstudianteUI  │  DocenteUI            │  │
│  │  CursoUI       │  MatriculaUI          │  │
│  └────────────────────────────────────────┘  │
└──────────────────┬───────────────────────────┘
                   │ (solicita operaciones)
                   ▼
┌──────────────────────────────────────────────┐
│            CAPA DE LÓGICA DE NEGOCIO         │
│  ┌────────────────────────────────────────┐  │
│  │  EstudianteService │ DocenteService   │  │
│  │  CursoService      │ MatriculaService │  │
│  │                                        │  │
│  │  Modelos: Estudiante, Docente,         │  │
│  │           Curso, Matricula             │  │
│  └────────────────────────────────────────┘  │
└──────────────────┬───────────────────────────┘
                   │ (solicita persistencia)
                   ▼
┌──────────────────────────────────────────────┐
│            CAPA DE ACCESO A DATOS            │
│  ┌────────────────────────────────────────┐  │
│  │  EstudianteRepository                  │  │
│  │  DocenteRepository                     │  │
│  │  CursoRepository                       │  │
│  │  MatriculaRepository                   │  │
│  └────────────────────────────────────────┘  │
└──────────────────┬───────────────────────────┘
                   │ (lee/escribe)
                   ▼
            ┌──────────────┐
            │  data/*.json │
            └──────────────┘
```

### Responsabilidades de cada capa

#### 🎨 Capa de Presentación (`presentacion`)

- Interacción con el usuario
- Menús de consola
- Lectura de datos por `Scanner`
- Presentación de resultados
- **NO** aplica reglas de negocio
- **NO** accede directamente a datos

#### 🧠 Capa de Lógica de Negocio (`business`)

- Contiene los modelos de dominio
- Implementa las reglas de negocio
- Realiza validaciones
- Coordina las operaciones
- Es el "cerebro" del sistema

#### 💾 Capa de Acceso a Datos (`data`)

- Persistencia en archivos JSON
- Serialización/deserialización con Gson
- **NO** aplica reglas de negocio
- Expone operaciones CRUD básicas

### Dependencias entre capas

```
Presentación → Negocio → Datos → data/*.json
```

- La capa superior depende de la inferior
- La capa inferior **NUNCA** depende de la superior
- Esto permite cambiar la presentación sin afectar la lógica
- Y cambiar el almacenamiento sin afectar la lógica

---

## 🚀 Propuesta 2: Arquitectura de N capas (diseño alternativo)

Como parte del análisis arquitectónico, se propone una **arquitectura de N capas** que refina la separación de responsabilidades.

### Diagrama

```
┌──────────────────────────────────────────────┐
│                PRESENTACIÓN                  │  ← Interfaz de usuario
│         (Vistas, Formularios, UI)            │
└──────────────────┬───────────────────────────┘
                   │
                   ▼
┌──────────────────────────────────────────────┐
│               CONTROLADORES                  │  ← Manejo de solicitudes
│      (Validación de entrada, ruteo)          │
└──────────────────┬───────────────────────────┘
                   │
                   ▼
┌──────────────────────────────────────────────┐
│                SERVICIOS                     │  ← Casos de uso
│      (Orquestación de operaciones)           │
└──────────────────┬───────────────────────────┘
                   │
                   ▼
┌──────────────────────────────────────────────┐
│                DOMINIO / NEGOCIO             │  ← Modelos y reglas
│         (Entidades, Reglas de negocio)       │
└──────────────────┬───────────────────────────┘
                   │
                   ▼
┌──────────────────────────────────────────────┐
│               REPOSITORIOS                   │  ← Contratos de datos
│        (Interfaces de persistencia)          │
└──────────────────┬───────────────────────────┘
                   │
                   ▼
┌──────────────────────────────────────────────┐
│            ACCESO A DATOS                    │  ← Implementación
│        (DAOs, conexión con BD)               │
└──────────────────┬───────────────────────────┘
                   │
                   ▼
            ┌──────────────┐
            │ Base de Datos│
            └──────────────┘
```

### Responsabilidades de cada capa

| Capa | Responsabilidad |
|---|---|
| **Presentación** | Renderizar UI, capturar entrada de usuario |
| **Controladores** | Recibir requests, validar formato, delegar a servicios |
| **Servicios** | Orquestar casos de uso del sistema |
| **Dominio / Negocio** | Contener modelos y reglas de negocio puras |
| **Repositorios** | Definir contratos de acceso a datos |
| **Acceso a Datos** | Implementar persistencia concreta (JSON, BD, etc.) |
| **Base de Datos** | Almacenamiento físico |

### Ventajas de N capas sobre 3 capas

- ✅ **Mayor separación de responsabilidades**: cada capa tiene una única tarea
- ✅ **Más testable**: las capas intermedias se pueden probar aisladas
- ✅ **Más flexible**: se puede cambiar la BD sin tocar servicios
- ✅ **Mejor para proyectos grandes**: el código es más mantenible
- ✅ **Facilita trabajo en equipo**: cada dev puede trabajar en su capa

### Desventajas

- ❌ **Más complejidad inicial**: más archivos y abstracciones
- ❌ **Más código boilerplate**: interfaces + implementaciones
- ❌ **Overkill para proyectos pequeños**: agrega complejidad innecesaria

---

## ⚖️ Comparación 3 capas vs N capas

| Criterio | 3 capas | N capas |
|---|---|---|
| **Número de capas** | 3 | 5-7 |
| **Separación de responsabilidades** | Buena | Excelente |
| **Complejidad** | Baja | Media-Alta |
| **Mantenimiento** | Bueno | Excelente |
| **Testabilidad** | Media | Alta |
| **Curva de aprendizaje** | Baja | Media |
| **Apropiado para** | Proyectos pequeños/medianos | Proyectos grandes/empresariales |
| **Aplicabilidad al caso** | ✅ Adecuada para este laboratorio | ✅ Escalable si el sistema crece |

---

## 🎯 Decisión arquitectónica

Para este proyecto se decidió implementar la **arquitectura de 3 capas** por:

1. **Simplicidad**: el alcance del laboratorio lo permite
2. **Claridad**: la separación es evidente y suficiente
3. **Suficiente para el dominio**: el sistema no requiere más abstracciones
4. **Aprendizaje**: cubre los conceptos fundamentales sin sobrecarga

Sin embargo, se documenta la **propuesta de N capas** como alternativa viable para escalar el sistema en futuras iteraciones.

---

## 🔄 Evolución futura

Si el sistema creciera (más módulos, más complejidad), la migración a N capas sería natural:

```
Presentación actual → Presentación + Controladores
Services actuales   → Services + Dominio separado
Repositories actuales → Repositorios (interfaces) + DAOs (implementación)
```

Esto permite:

- Cambiar el almacenamiento de JSON a SQL sin tocar servicios
- Agregar API REST reutilizando la capa de servicios
- Integrar tests unitarios por capa