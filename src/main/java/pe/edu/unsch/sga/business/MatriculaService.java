package pe.edu.unsch.sga.business;

import pe.edu.unsch.sga.data.CursoRepository;
import pe.edu.unsch.sga.data.EstudianteRepository;
import pe.edu.unsch.sga.data.MatriculaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

/**
 * Servicio de matrículas: contiene las reglas de negocio.
 * Capa: Lógica de Negocio.
 *
 * Reglas:
 * - El estudiante debe existir.
 * - El curso debe existir.
 * - No se permite matrícula duplicada ACTIVA del mismo estudiante en el mismo curso.
 * - La fecha debe tener formato YYYY-MM-DD.
 * - El estado debe ser ACTIVA, RETIRADA o COMPLETADA.
 */
public class MatriculaService {

    private static final Set<String> ESTADOS_VALIDOS = Set.of("ACTIVA", "RETIRADA", "COMPLETADA");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final MatriculaRepository repository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public MatriculaService() {
        this.repository = new MatriculaRepository();
        this.estudianteRepository = new EstudianteRepository();
        this.cursoRepository = new CursoRepository();
    }

    public void registrar(Matricula matricula) {
        validar(matricula);
        List<Matricula> matriculas = repository.listar();
        matriculas.add(matricula);
        repository.guardar(matriculas);
    }

    public List<Matricula> listar() {
        return repository.listar();
    }

    public boolean actualizar(Matricula matricula) {
        validar(matricula);
        List<Matricula> matriculas = repository.listar();
        for (Matricula m : matriculas) {
            if (m.getId() == matricula.getId()) {
                m.setEstudianteId(matricula.getEstudianteId());
                m.setCursoId(matricula.getCursoId());
                m.setFecha(matricula.getFecha());
                m.setEstado(matricula.getEstado());
                repository.guardar(matriculas);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        List<Matricula> matriculas = repository.listar();
        boolean eliminado = matriculas.removeIf(m -> m.getId() == id);
        if (eliminado) {
            repository.guardar(matriculas);
        }
        return eliminado;
    }

    /**
     * Cuenta cuántas matrículas ACTIVAS tiene un estudiante.
     */
    public long contarActivasPorEstudiante(int estudianteId) {
        return repository.listar().stream()
                .filter(m -> m.getEstudianteId() == estudianteId)
                .filter(m -> "ACTIVA".equalsIgnoreCase(m.getEstado()))
                .count();
    }

    private void validar(Matricula matricula) {
        // 1. Validar existencia del estudiante
        boolean estudianteExiste = estudianteRepository.listar().stream()
                .anyMatch(e -> e.getId() == matricula.getEstudianteId());
        if (!estudianteExiste) {
            throw new IllegalArgumentException(
                    "No existe un estudiante con Id " + matricula.getEstudianteId() + ".");
        }

        // 2. Validar existencia del curso
        boolean cursoExiste = cursoRepository.listar().stream()
                .anyMatch(c -> c.getId() == matricula.getCursoId());
        if (!cursoExiste) {
            throw new IllegalArgumentException(
                    "No existe un curso con Id " + matricula.getCursoId() + ".");
        }

        // 3. Validar formato de fecha
        try {
            LocalDate.parse(matricula.getFecha(), FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "La fecha debe tener el formato YYYY-MM-DD (ej: 2026-09-27).");
        }

        // 4. Validar estado permitido
        if (matricula.getEstado() == null ||
                !ESTADOS_VALIDOS.contains(matricula.getEstado().toUpperCase())) {
            throw new IllegalArgumentException(
                    "El estado debe ser ACTIVA, RETIRADA o COMPLETADA.");
        }
        matricula.setEstado(matricula.getEstado().toUpperCase());

        // 5. Validar no duplicado (mismo estudiante + curso + estado ACTIVA)
        if ("ACTIVA".equals(matricula.getEstado())) {
            boolean duplicada = repository.listar().stream()
                    .anyMatch(m -> m.getId() != matricula.getId()
                            && m.getEstudianteId() == matricula.getEstudianteId()
                            && m.getCursoId() == matricula.getCursoId()
                            && "ACTIVA".equalsIgnoreCase(m.getEstado()));
            if (duplicada) {
                throw new IllegalArgumentException(
                        "El estudiante ya tiene una matrícula ACTIVA en este curso.");
            }
        }
    }
}