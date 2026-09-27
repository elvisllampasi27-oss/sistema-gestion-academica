package pe.edu.unsch.sga;

import pe.edu.unsch.sga.presentacion.EstudianteUI;

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
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 -> EstudianteUI.mostrarMenu(sc);
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