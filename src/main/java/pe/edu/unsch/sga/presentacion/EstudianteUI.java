package pe.edu.unsch.sga.presentacion;

import pe.edu.unsch.sga.business.Estudiante;
import pe.edu.unsch.sga.business.EstudianteService;

import java.util.Scanner;

/**
 * Interfaz de usuario para la gestión de estudiantes.
 * Capa: Presentación.
 */
public class EstudianteUI {
    private static final EstudianteService service = new EstudianteService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
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
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();

        try {
            service.registrar(new Estudiante(id, nombre, correo));
            System.out.println("✅ Estudiante registrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void listar() {
        var estudiantes = service.listar();
        if (estudiantes.isEmpty()) {
            System.out.println("(No hay estudiantes registrados)");
            return;
        }
        estudiantes.forEach(System.out::println);
    }

    private static void actualizar(Scanner sc) {
        System.out.print("Id del estudiante a actualizar: ");
        int id = leerEntero(sc);
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo correo: ");
        String correo = sc.nextLine();

        try {
            if (service.actualizar(new Estudiante(id, nombre, correo))) {
                System.out.println("✅ Estudiante actualizado.");
            } else {
                System.out.println("❌ No existe un estudiante con ese Id.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void eliminar(Scanner sc) {
        System.out.print("Id del estudiante a eliminar: ");
        int id = leerEntero(sc);
        if (service.eliminar(id)) {
            System.out.println("✅ Estudiante eliminado.");
        } else {
            System.out.println("❌ No existe un estudiante con ese Id.");
        }
    }

    /** Lee un entero de forma segura, evitando excepciones si el usuario ingresa texto. */
    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine(); // limpiar buffer
        return valor;
    }
}