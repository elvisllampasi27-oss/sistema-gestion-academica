package pe.edu.unsch.sga.business;

import pe.edu.unsch.sga.data.CalificacionRepository;
import pe.edu.unsch.sga.data.CursoRepository;
import pe.edu.unsch.sga.data.EstudianteRepository;
import pe.edu.unsch.sga.data.MatriculaRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Servicio de reportes: genera reportes agregados a partir de los datos
 * de los módulos de estudiantes, cursos, matrículas y calificaciones.
 * Capa: Lógica de Negocio.
 *
 * Este servicio solo LEE información, no persiste nada.
 */
public class ReporteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    private final MatriculaRepository matriculaRepository;
    private final CalificacionRepository calificacionRepository;

    public ReporteService() {
        this.estudianteRepository = new EstudianteRepository();
        this.cursoRepository = new CursoRepository();
        this.matriculaRepository = new MatriculaRepository();
        this.calificacionRepository = new CalificacionRepository();
    }

    /**
     * Calcula el promedio general de un estudiante considerando
     * todas sus matrículas y calificaciones.
     */
    public double promedioPorEstudiante(int estudianteId) {
        List<Matricula> matriculas = matriculaRepository.listar().stream()
                .filter(m -> m.getEstudianteId() == estudianteId)
                .toList();

        if (matriculas.isEmpty()) return 0.0;

        List<Integer> idsMatriculas = matriculas.stream()
                .map(Matricula::getId)
                .toList();

        List<Calificacion> notas = calificacionRepository.listar().stream()
                .filter(c -> idsMatriculas.contains(c.getMatriculaId()))
                .toList();

        if (notas.isEmpty()) return 0.0;

        return notas.stream()
                .mapToDouble(Calificacion::getNota)
                .average()
                .orElse(0.0);
    }

    /**
     * Retorna todas las calificaciones asociadas a un curso.
     */
    public List<Calificacion> calificacionesPorCurso(int cursoId) {
        List<Integer> idsMatriculas = matriculaRepository.listar().stream()
                .filter(m -> m.getCursoId() == cursoId)
                .map(Matricula::getId)
                .toList();

        return calificacionRepository.listar().stream()
                .filter(c -> idsMatriculas.contains(c.getMatriculaId()))
                .toList();
    }

    /**
     * Calcula el promedio general de un curso.
     */
    public double promedioPorCurso(int cursoId) {
        List<Calificacion> notas = calificacionesPorCurso(cursoId);
        if (notas.isEmpty()) return 0.0;
        return notas.stream()
                .mapToDouble(Calificacion::getNota)
                .average()
                .orElse(0.0);
    }

    /**
     * Retorna un ranking de cursos por número de matrículas.
     */
    public Map<String, Long> cursosConMasMatriculas() {
        Map<Integer, Long> conteoPorCurso = matriculaRepository.listar().stream()
                .collect(Collectors.groupingBy(Matricula::getCursoId, Collectors.counting()));

        Map<String, Long> resultado = new HashMap<>();
        conteoPorCurso.forEach((cursoId, cantidad) ->
                cursoRepository.listar().stream()
                        .filter(c -> c.getId() == cursoId)
                        .findFirst()
                        .ifPresent(curso -> resultado.put(
                                curso.getCodigo() + " - " + curso.getNombre(),
                                cantidad)));
        return resultado;
    }

    /**
     * Genera un resumen general del sistema.
     */
    public String resumenGeneral() {
        int estudiantes = estudianteRepository.listar().size();
        int cursos = cursoRepository.listar().size();
        int matriculas = matriculaRepository.listar().size();
        int calificaciones = calificacionRepository.listar().size();

        return String.format("""
                ╔══════════════════════════════════════╗
                ║      RESUMEN GENERAL DEL SISTEMA     ║
                ╠══════════════════════════════════════╣
                ║  Estudiantes registrados:  %-8d     ║
                ║  Cursos registrados:       %-8d     ║
                ║  Matrículas registradas:   %-8d     ║
                ║  Calificaciones:           %-8d     ║
                ╚══════════════════════════════════════╝
                """, estudiantes, cursos, matriculas, calificaciones);
    }
}