/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalp3;
import java.util.Scanner;
/**
 *
 * @author Jairo Fernando
 */
public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una opción:");
        System.out.println("1. Mostrar distancia de departamentos");
        System.out.println("2. Encontrar ruta más corta");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();

        ejecutarOpcion(opcion);
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
            {
                
                RutaMasCorta.Distancias(null);
            }
               
                break;

            case 2:
                RutasMasCortas.Buscar(null);
                
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}
