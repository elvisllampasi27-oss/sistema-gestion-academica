package pe.edu.unsch.sga.business;

import pe.edu.unsch.sga.data.CalificacionRepository;
import pe.edu.unsch.sga.data.MatriculaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

/**
 * Servicio de calificaciones: contiene las reglas de negocio.
 * Capa: Lógica de Negocio.
 */
public class CalificacionService {

    private static final Set<String> TIPOS_VALIDOS = Set.of("PARCIAL", "FINAL", "PRACTICA");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final CalificacionRepository repository;
    private final MatriculaRepository matriculaRepository;

    public CalificacionService() {
        this.repository = new CalificacionRepository();
        this.matriculaRepository = new MatriculaRepository();
    }

    public void registrar(Calificacion calificacion) {
        validar(calificacion);
        List<Calificacion> calificaciones = repository.listar();
        calificaciones.add(calificacion);
        repository.guardar(calificaciones);
    }

    public List<Calificacion> listar() {
        return repository.listar();
    }

    public boolean actualizar(Calificacion calificacion) {
        validar(calificacion);
        List<Calificacion> calificaciones = repository.listar();
        for (Calificacion c : calificaciones) {
            if (c.getId() == calificacion.getId()) {
                c.setMatriculaId(calificacion.getMatriculaId());
                c.setTipo(calificacion.getTipo());
                c.setNota(calificacion.getNota());
                c.setFecha(calificacion.getFecha());
                repository.guardar(calificaciones);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        List<Calificacion> calificaciones = repository.listar();
        boolean eliminado = calificaciones.removeIf(c -> c.getId() == id);
        if (eliminado) {
            repository.guardar(calificaciones);
        }
        return eliminado;
    }

    /**
     * Calcula el promedio de notas de una matrícula.
     */
    public double promedioPorMatricula(int matriculaId) {
        List<Calificacion> notas = repository.listar().stream()
                .filter(c -> c.getMatriculaId() == matriculaId)
                .toList();
        if (notas.isEmpty()) return 0.0;
        return notas.stream().mapToDouble(Calificacion::getNota).average().orElse(0.0);
    }

    private void validar(Calificacion calificacion) {
        // 1. Validar existencia de la matrícula
        Matricula matricula = matriculaRepository.listar().stream()
                .filter(m -> m.getId() == calificacion.getMatriculaId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe una matrícula con Id " + calificacion.getMatriculaId() + "."));

        // 2. Validar que la matrícula esté ACTIVA
        if (!"ACTIVA".equalsIgnoreCase(matricula.getEstado())) {
            throw new IllegalArgumentException(
                    "Solo se pueden calificar matrículas en estado ACTIVA.");
        }

        // 3. Validar tipo
        if (calificacion.getTipo() == null ||
                !TIPOS_VALIDOS.contains(calificacion.getTipo().toUpperCase())) {
            throw new IllegalArgumentException(
                    "El tipo debe ser PARCIAL, FINAL o PRACTICA.");
        }
        calificacion.setTipo(calificacion.getTipo().toUpperCase());

        // 4. Validar nota
        if (calificacion.getNota() < 0 || calificacion.getNota() > 20) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 20.");
        }

        // 5. Validar fecha
        try {
            LocalDate.parse(calificacion.getFecha(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "La fecha debe tener el formato YYYY-MM-DD (ej: 2026-09-27).");
        }
    }
}