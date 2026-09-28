package pe.edu.unsch.sga;

import pe.edu.unsch.sga.presentacion.CalificacionUI;
import pe.edu.unsch.sga.presentacion.CursoUI;
import pe.edu.unsch.sga.presentacion.DocenteUI;
import pe.edu.unsch.sga.presentacion.EstudianteUI;
import pe.edu.unsch.sga.presentacion.MatriculaUI;
// import pe.edu.unsch.sga.presentacion.ReporteUI;  // TODO: activar al crear Reportes

import java.util.Scanner;

/**
 * Punto de entrada del Sistema de Gestión Académica.
 * Arquitectura: 3 capas (Presentación → Negocio → Datos).
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTIÓN ACADÉMICA =====");
            System.out.println("1. Gestión de estudiantes");
            System.out.println("2. Gestión de docentes");
            System.out.println("3. Gestión de cursos");
            System.out.println("4. Gestión de matrículas");
            System.out.println("5. Gestión de calificaciones");
            System.out.println("6. Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> EstudianteUI.mostrarMenu(sc);
                case 2 -> DocenteUI.mostrarMenu(sc);
                case 3 -> CursoUI.mostrarMenu(sc);
                case 4 -> MatriculaUI.mostrarMenu(sc);
                case 5 -> CalificacionUI.mostrarMenu(sc);
                case 6 -> System.out.println("⏳ Módulo en construcción...");
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
        sc.close();
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