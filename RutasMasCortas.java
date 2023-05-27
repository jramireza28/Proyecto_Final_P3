/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinalp3;
import java.util.*;
/**
 *
 * @author Jairo Fernando
 */
public class RutasMasCortas {
  
    private static final int NUM_DEPARTAMENTOS = 14;

    private static int[][] matrizAdyacencia = {
        { 0, 81, 63, 108, 95, 99, 64, 72, 98, 64, 86, 95, 75, 60 },   // San Salvador
        { 81, 0, 49, 76, 55, 72, 69, 21, 25, 82, 27, 20, 77, 60 },    // Santa Ana
        { 63, 49, 0, 37, 19, 33, 52, 28, 38, 70, 22, 29, 57, 52 },    // San Miguel
        { 108, 76, 37, 0, 20, 8, 88, 58, 75, 103, 58, 66, 43, 63 },   // La Libertad
        { 95, 55, 19, 20, 0, 20, 66, 40, 53, 89, 40, 49, 61, 58 },    // Sonsonate
        { 99, 72, 33, 8, 20, 0, 74, 51, 68, 97, 50, 58, 38, 58 },     // Usulután
        { 64, 69, 52, 88, 66, 74, 0, 35, 63, 19, 56, 75, 16, 11 },     // Ahuachapán
        { 72, 21, 28, 58, 40, 51, 35, 0, 27, 61, 14, 28, 53, 40 },     // La Paz
        { 98, 25, 38, 75, 53, 68, 63, 27, 0, 65, 15, 13, 70, 52 },     // Chalatenango
        { 64, 82, 70, 103, 89, 97, 19, 61, 65, 0, 57, 77, 25, 30 },    // San Vicente
        { 86, 27, 22, 58, 40, 50, 56, 14, 15, 57, 0, 18, 58, 41 },     // Cabañas
        { 95, 20, 29, 66, 49, 58, 75, 28, 13, 77, 18, 0, 70, 53 },     // La Unión
        { 75, 77, 57, 43, 61, 38, 16, 53, 70, 77, 25, 30, 0, 25 },     // Morazán
        { 60, 60, 52, 63, 58, 58, 11, 40, 52, 30, 41, 53, 25, 0 }      // La Paz
    };

    public static void Buscar(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Departamentos en El Salvador:");
        System.out.println("1. San Salvador");
        System.out.println("2. Santa Ana");
        System.out.println("3. San Miguel");
        System.out.println("4. La Libertad");
        System.out.println("5. Sonsonate");
        System.out.println("6. Usulután");
        System.out.println("7. Ahuachapán");
        System.out.println("8. La Paz");
        System.out.println("9. Chalatenango");
        System.out.println("10. San Vicente");
        System.out.println("11. Cabañas");
        System.out.println("12. La Unión");
        System.out.println("13. Morazán");
        System.out.println("14. La Paz");

    
        System.out.print("Ingrese el número del departamento de origen: ");
        int origen = scanner.nextInt();

        if (origen < 1 || origen > NUM_DEPARTAMENTOS) {
            System.out.println("Número de departamento no válido.");
            return;
        }

        System.out.print("Ingrese el número del departamento de destino: ");
        int destino = scanner.nextInt();

        if (destino < 1 || destino > NUM_DEPARTAMENTOS) {
            System.out.println("Número de departamento no válido.");
            return;
        }

        dijkstra(origen - 1, destino - 1);
    }

    private static void dijkstra(int origen, int destino) {
        int[] distancias = new int[NUM_DEPARTAMENTOS];
        boolean[] visitado = new boolean[NUM_DEPARTAMENTOS];

        for (int i = 0; i < NUM_DEPARTAMENTOS; i++) {
            distancias[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }

        distancias[origen] = 0;

        for (int i = 0; i < NUM_DEPARTAMENTOS - 1; i++) {
            int departamentoActual = obtenerMinimaDistancia(distancias, visitado);
            visitado[departamentoActual] = true;

            for (int j = 0; j < NUM_DEPARTAMENTOS; j++) {
                if (!visitado[j] && matrizAdyacencia[departamentoActual][j] != 0 &&
                        distancias[departamentoActual] != Integer.MAX_VALUE &&
                        distancias[departamentoActual] + matrizAdyacencia[departamentoActual][j] < distancias[j]) {
                    distancias[j] = distancias[departamentoActual] + matrizAdyacencia[departamentoActual][j];
                }
            }
        }

        mostrarRutaMasCorta(origen, destino, distancias);
    }

    private static int obtenerMinimaDistancia(int[] distancias, boolean[] visitado) {
        int minimaDistancia = Integer.MAX_VALUE;
        int minimoIndice = -1;

        for (int i = 0; i < NUM_DEPARTAMENTOS; i++) {
            if (!visitado[i] && distancias[i] <= minimaDistancia) {
                minimaDistancia = distancias[i];
                minimoIndice = i;
            }
        }

        return minimoIndice;
    }

    private static void mostrarRutaMasCorta(int origen, int destino, int[] distancias) {
        String[] nombresDepartamentos = {"San Salvador", "Santa Ana", "San Miguel", "La Libertad", "Sonsonate", "Usulután",
            "Ahuachapán", "La Paz", "Chalatenango", "San Vicente", "Cabañas", "La Unión", "Morazán", "La Paz"};

        System.out.println("La ruta más corta desde " + nombresDepartamentos[origen] + " hasta " + nombresDepartamentos[destino] + " es:");

        int distancia = distancias[destino];
        System.out.println("Distancia: " + distancia + " km");

        System.out.print("Ruta: ");
        int departamentoActual = destino;
        String ruta = nombresDepartamentos[destino] + " ";

        while (departamentoActual != origen) {
            for (int i = 0; i < NUM_DEPARTAMENTOS; i++) {
                if (matrizAdyacencia[i][departamentoActual] != 0 &&
                        distancias[departamentoActual] == distancias[i] + matrizAdyacencia[i][departamentoActual]) {
                    departamentoActual = i;
                    ruta = nombresDepartamentos[i] + " -> " + ruta;
                    break;
                }
            }
        }

        System.out.println(ruta);
    }
    
    
           

    
    
    
}///////Fin principal//////////