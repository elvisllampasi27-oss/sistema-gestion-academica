# 🎓 Sistema de Gestión Académica

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Version](https://img.shields.io/badge/version-1.3.0-blue.svg)](https://github.com/elvisllampasi27-oss/sistema-gestion-academica/releases)

Sistema de gestión académica desarrollado como parte del curso **IS-388 Construcción y Evolución de Software** de la **Universidad Nacional de San Cristóbal de Huamanga (UNSCH)**.

El proyecto implementa una **arquitectura de 3 capas** que permite gestionar estudiantes, docentes, cursos y matrículas.

---

## 📚 Tabla de contenidos

- [Arquitectura](#-arquitectura)
- [Tecnologías](#-tecnologías)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Cómo ejecutar](#-cómo-ejecutar)
- [Módulos](#-módulos)
- [Historial de versiones](#-historial-de-versiones)
- [Autor](#-autor)
- [Licencia](#-licencia)

---

## 🏗️ Arquitectura

El sistema sigue una **arquitectura de 3 capas** con separación estricta de responsabilidades:

```
┌─────────────────────────────────────┐
│          PRESENTACIÓN               │  ← Interfaz de usuario (consola)
│  (EstudianteUI, DocenteUI, ...)     │
├─────────────────────────────────────┤
│      LÓGICA DE NEGOCIO              │  ← Reglas y validaciones
│  (Services + Modelos)               │
├─────────────────────────────────────┤
│      ACCESO A DATOS                 │  ← Persistencia
│  (Repositories con JSON)            │
└─────────────────────────────────────┘
              │
              ▼
        data/*.json
```

| Capa | Responsabilidad | Paquete |
|---|---|---|
| **Presentación** | Interacción con el usuario | `pe.edu.unsch.sga.presentacion` |
| **Negocio** | Reglas y validaciones | `pe.edu.unsch.sga.business` |
| **Datos** | Persistencia en JSON | `pe.edu.unsch.sga.data` |

> 📖 Ver [docs/ARQUITECTURA.md](docs/ARQUITECTURA.md) para detalles sobre la propuesta de N capas.

---

## 🛠️ Tecnologías

- **Java 21** (LTS)
- **Maven** (gestión de dependencias)
- **Gson 2.11.0** (serialización JSON)
- **JUnit 5** (pruebas unitarias)

---

## 📁 Estructura del proyecto

```
sistema-gestion-academica/
├── data/                          # Almacenamiento JSON (base de datos)
│   ├── estudiantes.json
│   ├── docentes.json
│   ├── cursos.json
│   └── matriculas.json
├── docs/
│   └── ARQUITECTURA.md            # Documento de diseño N capas
├── src/
│   └── main/
│       └── java/
│           └── pe/edu/unsch/sga/
│               ├── Main.java
│               ├── presentacion/  # Capa de presentación
│               ├── business/      # Capa de negocio
│               └── data/          # Capa de acceso a datos
├── CHANGELOG.md
├── LICENSE
├── pom.xml
└── README.md
```

---

## 🚀 Cómo ejecutar

### Requisitos

- **JDK 21** o superior
- **Maven 3.9+**

### Pasos

```bash
git clone https://github.com/elvisllampasi27-oss/sistema-gestion-academica.git
cd sistema-gestion-academica
mvn clean compile
mvn exec:java -Dexec.mainClass="pe.edu.unsch.sga.Main"
```

O desde IntelliJ IDEA: **Run → Main**

---

## 📦 Módulos

| Módulo | Estado | Versión |
|---|---|---|
| Gestión de estudiantes | ✅ Completado | `v1.0.0` |
| Gestión de docentes | ✅ Completado | `v1.1.1` |
| Gestión de cursos | ✅ Completado | `v1.2.0` |
| Gestión de matrículas | ✅ Completado | `v1.3.0` |
| Gestión de calificaciones | ⏳ Próximamente | — |
| Reportes | ⏳ Próximamente | — |

Cada módulo implementa:

- ✅ Modelo de dominio
- ✅ Repositorio con persistencia JSON
- ✅ Servicio con reglas de negocio
- ✅ Interfaz de usuario (CRUD)

---

## 📜 Historial de versiones

| Versión | Fecha | Descripción |
|---|---|---|
| `v1.3.0` | 2026-09-27 | Módulo de matrículas |
| `v1.2.0` | 2026-09-27 | Módulo de cursos |
| `v1.1.1` | 2026-09-27 | Fix del módulo de docentes |
| `v1.1.0` | 2026-09-27 | Módulo de docentes (incompleto) |
| `v1.0.0` | 2026-09-27 | Versión inicial: estudiantes |

Ver [CHANGELOG.md](CHANGELOG.md) para más detalles.

---

## 👤 Autor

**Elvis Brayan Llampasi**

- 📧 elvis.llampasi.27@unsch.edu.pe
- 🎓 Universidad Nacional de San Cristóbal de Huamanga
- 🐙 [@elvisllampasi27-oss](https://github.com/elvisllampasi27-oss)

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver [LICENSE](LICENSE) para más detalles.