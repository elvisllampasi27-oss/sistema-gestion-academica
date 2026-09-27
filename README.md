# 🎓 Sistema de Gestión Académica

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

Sistema de gestión académica desarrollado como parte del curso **IS-388 Construcción y Evolución de Software** de la **Universidad Nacional de San Cristóbal de Huamanga (UNSCH)**.

El proyecto implementa una **arquitectura de 3 capas** que permite gestionar estudiantes, cursos, docentes, matrículas y calificaciones.

---

## 📚 Tabla de contenidos

- [Arquitectura](#-arquitectura)
- [Tecnologías](#-tecnologías)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Cómo ejecutar](#-cómo-ejecutar)
- [Módulos](#-módulos)
- [Autor](#-autor)
- [Licencia](#-licencia)

---

## 🏗️ Arquitectura

El sistema sigue una **arquitectura de 3 capas**:

```
┌─────────────────────────────┐
│      PRESENTACIÓN           │  ← Interfaz de usuario (consola)
├─────────────────────────────┤
│      LÓGICA DE NEGOCIO      │  ← Reglas y validaciones
├─────────────────────────────┤
│      ACCESO A DATOS         │  ← Persistencia (JSON)
└─────────────────────────────┘
              │
              ▼
         data/*.json
```

| Capa | Responsabilidad | Paquete |
|---|---|---|
| **Presentación** | Interacción con el usuario | `pe.edu.unsch.sga.presentacion` |
| **Negocio** | Reglas y validaciones | `pe.edu.unsch.sga.business` |
| **Datos** | Persistencia en JSON | `pe.edu.unsch.sga.data` |

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
│   └── estudiantes.json
├── src/
│   └── main/
│       └── java/
│           └── pe/edu/unsch/sga/
│               ├── Main.java
│               ├── presentacion/
│               ├── business/
│               └── data/
├── pom.xml
└── README.md
```

---

## 🚀 Cómo ejecutar

### Requisitos
- JDK 21
- Maven 3.9+

### Pasos
```bash
git clone https://github.com/elvisllampasi27-oss/sistema-gestion-academica.git
cd sistema-gestion-academica
mvn clean compile
mvn exec:java -Dexec.mainClass="pe.edu.unsch.sga.Main"
```

O desde IntelliJ: **Run → Main**

---

## 📦 Módulos

- [x] Gestión de estudiantes
- [ ] Gestión de docentes
- [ ] Gestión de cursos
- [ ] Matrícula
- [ ] Calificaciones
- [ ] Reportes

---

## 👤 Autor

**Elvis Brayan Llampasi**
- 📧 elvis.llampasi.27@unsch.edu.pe
- 🎓 Universidad Nacional de San Cristóbal de Huamanga
- 🐙 [@elvisllampasi27-oss](https://github.com/elvisllampasi27-oss)

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver [LICENSE](LICENSE) para más detalles.