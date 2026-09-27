package pe.edu.unsch.sga.presentacion;

import pe.edu.unsch.sga.business.Curso;
import pe.edu.unsch.sga.business.CursoService;

import java.util.Scanner;

/**
 * Interfaz de usuario para la gestión de cursos.
 * Capa: Presentación.
 */
public class CursoUI {
    private static final CursoService service = new CursoService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE CURSOS ===");
            System.out.println("1. Registrar curso");
            System.out.println("2. Listar cursos");
            System.out.println("3. Actualizar curso");
            System.out.println("4. Eliminar curso");
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
        System.out.print("Id: ");
        int id = leerEntero(sc);
        System.out.print("Código (ej: IS-388): ");
        String codigo = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Créditos: ");
        int creditos = leerEntero(sc);

        try {
            service.registrar(new Curso(id, codigo, nombre, creditos));
            System.out.println("✅ Curso registrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void listar() {
        var cursos = service.listar();
        if (cursos.isEmpty()) {
            System.out.println("(No hay cursos registrados)");
            return;
        }
        cursos.forEach(System.out::println);
    }

    private static void actualizar(Scanner sc) {
        System.out.print("Id del curso a actualizar: ");
        int id = leerEntero(sc);
        System.out.print("Nuevo código: ");
        String codigo = sc.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevos créditos: ");
        int creditos = leerEntero(sc);

        try {
            if (service.actualizar(new Curso(id, codigo, nombre, creditos))) {
                System.out.println("✅ Curso actualizado.");
            } else {
                System.out.println("❌ No existe un curso con ese Id.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void eliminar(Scanner sc) {
        System.out.print("Id del curso a eliminar: ");
        int id = leerEntero(sc);
        if (service.eliminar(id)) {
            System.out.println("✅ Curso eliminado.");
        } else {
            System.out.println("❌ No existe un curso con ese Id.");
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