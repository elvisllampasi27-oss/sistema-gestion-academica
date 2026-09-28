package pe.edu.unsch.sga.presentacion;

import pe.edu.unsch.sga.business.Calificacion;
import pe.edu.unsch.sga.business.CalificacionService;
import pe.edu.unsch.sga.business.MatriculaService;

import java.util.Scanner;

/**
 * Interfaz de usuario para la gestión de calificaciones.
 * Capa: Presentación.
 */
public class CalificacionUI {
    private static final CalificacionService service = new CalificacionService();
    private static final MatriculaService matriculaService = new MatriculaService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CALIFICACIONES ===");
            System.out.println("1. Registrar calificación");
            System.out.println("2. Listar calificaciones");
            System.out.println("3. Actualizar calificación");
            System.out.println("4. Eliminar calificación");
            System.out.println("5. Promedio por matrícula");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> registrar(sc);
                case 2 -> listar();
                case 3 -> actualizar(sc);
                case 4 -> eliminar(sc);
                case 5 -> promedio(sc);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        System.out.println("\n-- Matrículas disponibles --");
        matriculaService.listar().forEach(m ->
                System.out.printf("  [%d] Estudiante %d → Curso %d (%s)%n",
                        m.getId(), m.getEstudianteId(), m.getCursoId(), m.getEstado()));

        System.out.print("\nId de la calificación: ");
        int id = leerEntero(sc);
        System.out.print("Id de la matrícula: ");
        int matriculaId = leerEntero(sc);
        System.out.print("Tipo (PARCIAL/FINAL/PRACTICA): ");
        String tipo = sc.nextLine();
        System.out.print("Nota (0-20): ");
        double nota = leerDouble(sc);
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();

        try {
            service.registrar(new Calificacion(id, matriculaId, tipo, nota, fecha));
            System.out.println("✅ Calificación registrada.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void listar() {
        var calificaciones = service.listar();
        if (calificaciones.isEmpty()) {
            System.out.println("(No hay calificaciones registradas)");
            return;
        }
        calificaciones.forEach(System.out::println);
    }

    private static void actualizar(Scanner sc) {
        System.out.print("Id de la calificación a actualizar: ");
        int id = leerEntero(sc);
        System.out.print("Nuevo id de matrícula: ");
        int matriculaId = leerEntero(sc);
        System.out.print("Nuevo tipo: ");
        String tipo = sc.nextLine();
        System.out.print("Nueva nota: ");
        double nota = leerDouble(sc);
        System.out.print("Nueva fecha: ");
        String fecha = sc.nextLine();

        try {
            if (service.actualizar(new Calificacion(id, matriculaId, tipo, nota, fecha))) {
                System.out.println("✅ Calificación actualizada.");
            } else {
                System.out.println("❌ No existe una calificación con ese Id.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void eliminar(Scanner sc) {
        System.out.print("Id de la calificación a eliminar: ");
        int id = leerEntero(sc);
        if (service.eliminar(id)) {
            System.out.println("✅ Calificación eliminada.");
        } else {
            System.out.println("❌ No existe una calificación con ese Id.");
        }
    }

    private static void promedio(Scanner sc) {
        System.out.print("Id de la matrícula: ");
        int matriculaId = leerEntero(sc);
        double promedio = service.promedioPorMatricula(matriculaId);
        System.out.printf("📊 Promedio: %.2f%n", promedio);
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

    private static double leerDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un número decimal válido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}