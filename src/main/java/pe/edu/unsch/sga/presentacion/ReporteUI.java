package pe.edu.unsch.sga.presentacion;

import pe.edu.unsch.sga.business.Calificacion;
import pe.edu.unsch.sga.business.ReporteService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Interfaz de usuario para el módulo de reportes.
 * Capa: Presentación.
 */
public class ReporteUI {
    private static final ReporteService service = new ReporteService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== REPORTES ===");
            System.out.println("1. Promedio de un estudiante");
            System.out.println("2. Promedio de un curso");
            System.out.println("3. Cursos con más matrículas");
            System.out.println("4. Resumen general del sistema");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> promedioEstudiante(sc);
                case 2 -> promedioCurso(sc);
                case 3 -> cursosConMasMatriculas();
                case 4 -> System.out.println(service.resumenGeneral());
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void promedioEstudiante(Scanner sc) {
        System.out.print("Id del estudiante: ");
        int estudianteId = leerEntero(sc);
        double promedio = service.promedioPorEstudiante(estudianteId);
        if (promedio == 0.0) {
            System.out.println("(No hay calificaciones para este estudiante)");
        } else {
            System.out.printf("📊 Promedio del estudiante: %.2f%n", promedio);
        }
    }

    private static void promedioCurso(Scanner sc) {
        System.out.print("Id del curso: ");
        int cursoId = leerEntero(sc);
        double promedio = service.promedioPorCurso(cursoId);
        if (promedio == 0.0) {
            System.out.println("(No hay calificaciones registradas para este curso)");
        } else {
            List<Calificacion> notas = service.calificacionesPorCurso(cursoId);
            System.out.println("📋 Calificaciones del curso:");
            notas.forEach(System.out::println);
            System.out.printf("📊 Promedio del curso: %.2f%n", promedio);
        }
    }

    private static void cursosConMasMatriculas() {
        Map<String, Long> ranking = service.cursosConMasMatriculas();
        if (ranking.isEmpty()) {
            System.out.println("(No hay matrículas registradas)");
            return;
        }
        System.out.println("📊 Ranking de cursos por matrículas:");
        ranking.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %-50s → %d matrículas%n",
                        entry.getKey(), entry.getValue()));
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }
}