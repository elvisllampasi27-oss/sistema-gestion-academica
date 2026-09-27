package pe.edu.unsch.sga.presentacion;

import pe.edu.unsch.sga.business.Docente;
import pe.edu.unsch.sga.business.DocenteService;

import java.util.Scanner;

/**
 * Interfaz de usuario para la gestión de docentes.
 * Capa: Presentación.
 */
public class DocenteUI {
    private static final DocenteService service = new DocenteService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE DOCENTES ===");
            System.out.println("1. Registrar docente");
            System.out.println("2. Listar docentes");
            System.out.println("3. Actualizar docente");
            System.out.println("4. Eliminar docente");
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
        System.out.print("Especialidad: ");
        String especialidad = sc.nextLine();

        try {
            service.registrar(new Docente(id, nombre, correo, especialidad));
            System.out.println("✅ Docente registrado.");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void listar() {
        var docentes = service.listar();
        if (docentes.isEmpty()) {
            System.out.println("(No hay docentes registrados)");
            return;
        }
        docentes.forEach(System.out::println);
    }

    private static void actualizar(Scanner sc) {
        System.out.print("Id del docente a actualizar: ");
        int id = leerEntero(sc);
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo correo: ");
        String correo = sc.nextLine();
        System.out.print("Nueva especialidad: ");
        String especialidad = sc.nextLine();

        try {
            if (service.actualizar(new Docente(id, nombre, correo, especialidad))) {
                System.out.println("✅ Docente actualizado.");
            } else {
                System.out.println("❌ No existe un docente con ese Id.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void eliminar(Scanner sc) {
        System.out.print("Id del docente a eliminar: ");
        int id = leerEntero(sc);
        if (service.eliminar(id)) {
            System.out.println("✅ Docente eliminado.");
        } else {
            System.out.println("❌ No existe un docente con ese Id.");
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