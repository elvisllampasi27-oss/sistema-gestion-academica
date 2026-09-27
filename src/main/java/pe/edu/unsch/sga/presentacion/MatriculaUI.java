package pe.edu.unsch.sga.presentacion;

import pe.edu.unsch.sga.business.CursoService;
import pe.edu.unsch.sga.business.EstudianteService;
import pe.edu.unsch.sga.business.Matricula;
import pe.edu.unsch.sga.business.MatriculaService;

import java.util.Scanner;

/**
 * Interfaz de usuario para la gestión de matrículas.
 * Capa: Presentación.
 */
public class MatriculaUI {
    private static final MatriculaService service = new MatriculaService();
    private static final EstudianteService estudianteService = new EstudianteService();
    private static final CursoService cursoService = new CursoService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE MATRÍCULAS ===");
            System.out.println("1. Registrar matrícula");
            System.out.println("2. Listar matrículas");
            System.out.println("3. Actualizar matrícula");
            System.out.println("4. Eliminar matrícula");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> registrar(sc);
                case 2 -> listar();
                case 3 -> actualizar(sc);
                case 4 -> eliminar(sc);
                case 0 -> System.out.println("Regresando...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        System.out.println("\n-- Estudiantes disponibles --");
        estudianteService.listar().forEach(e ->
                System.out.printf("  [%d] %s%n", e.getId(), e.getNombre()));
        System.out.println("-- Cursos disponibles --");
        cursoService.listar().forEach(c ->
                System.out.printf("  [%d] %s (%s)%n", c.getId(), c.getNombre(), c.getCodigo()));

        System.out.print("\nId de la matrícula: ");
        int id = leerEntero(sc);
        System.out.print("Id del estudiante: ");
        int estudianteId = leerEntero(sc);
        System.out.print("Id del curso: ");
        int cursoId = leerEntero(sc);
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Estado (ACTIVA/RETIRADA/COMPLETADA): ");
        String estado = sc.nextLine();

        try {
            service.registrar(new Matricula(id, estudianteId, cursoId, fecha, estado));
            System.out.println("✅ Matrícula registrada.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void listar() {
        var matriculas = service.listar();
        if (matriculas.isEmpty()) {
            System.out.println("(No hay matrículas registradas)");
            return;
        }
        matriculas.forEach(System.out::println);
    }

    private static void actualizar(Scanner sc) {
        System.out.print("Id de la matrícula a actualizar: ");
        int id = leerEntero(sc);
        System.out.print("Nuevo id del estudiante: ");
        int estudianteId = leerEntero(sc);
        System.out.print("Nuevo id del curso: ");
        int cursoId = leerEntero(sc);
        System.out.print("Nueva fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        System.out.print("Nuevo estado (ACTIVA/RETIRADA/COMPLETADA): ");
        String estado = sc.nextLine();

        try {
            if (service.actualizar(new Matricula(id, estudianteId, cursoId, fecha, estado))) {
                System.out.println("✅ Matrícula actualizada.");
            } else {
                System.out.println("❌ No existe una matrícula con ese Id.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void eliminar(Scanner sc) {
        System.out.print("Id de la matrícula a eliminar: ");
        int id = leerEntero(sc);
        if (service.eliminar(id)) {
            System.out.println("✅ Matrícula eliminada.");
        } else {
            System.out.println("❌ No existe una matrícula con ese Id.");
        }
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