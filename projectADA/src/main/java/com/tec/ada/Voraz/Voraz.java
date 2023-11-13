
package com.tec.ada.Voraz;
import java.time.Instant;


public class Voraz {

    static int comparaciones = 0;
    static int asignaciones = 0;
    static int instrucciones = 0;
    
    public static void main() {
        long startTime = Instant.now().toEpochMilli();
        Grafo grafo = new Grafo(60); // Ajusta el número de vértices según sea necesario
        

        int verticeInicio = 0; // Vértice de origen
        int verticeFinal = 9; // Vértice final

        voraz(grafo, verticeInicio, verticeFinal);
        // Registro del tiempo de finalización de la ejecución.
        long endTime = Instant.now().toEpochMilli();
        // Cálculo del tiempo transcurrido en segundos y muestra en pantalla.
        long timeElapsed = endTime - startTime;
        System.out.println("\nExecution time in milliseconds: " + timeElapsed);
        System.out.println("\rComparaciones " + comparaciones);
        System.out.println("\rAsignaciones " + asignaciones);
        instrucciones = comparaciones + asignaciones;
        System.out.println("Instrucciones " + instrucciones);
        


        //contador de memoria utilizada
        // Obtén una instancia de la clase Runtime
        Runtime runtime = Runtime.getRuntime();

        // Llama al método gc() para solicitar la ejecución del recolector de basura
        runtime.gc();

        // Obtén la memoria total del sistema
        long memoriaTotal = runtime.totalMemory();
        long memoriaLibre = runtime.freeMemory();
        
        // Calcula la memoria utilizada
        long memoriaUsada = memoriaTotal - memoriaLibre;
        
        // Imprime los resultados
        System.out.println("Memoria total: " + memoriaTotal + " bytes");
        System.out.println("Memoria libre: " + memoriaLibre + " bytes");
        System.out.println("Memoria utilizada: " + memoriaUsada + " bytes");
        
    }

    public static class Grafo {
        private int[][] matriz;
        private int vertices;

        public Grafo(int vertices) {
            this.vertices = vertices;
            matriz = new int[vertices][vertices];

            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (i != j) {
                        matriz[i][j] = (int) (Math.random() * 250) + 1;
                    }
                }
            }
        }
    }

    public static void voraz(Grafo grafo, int verticeInicio, int vertiveFinal) {
       
        asignaciones++; asignaciones++; asignaciones++;


        boolean[] visitado = new boolean[grafo.vertices]; asignaciones++; 
        int verticeActual = verticeInicio; asignaciones++; 
        int distanciaTotal = 0; asignaciones++; 

        System.out.println("Ruta desde el vértice " + verticeInicio + " hasta el vértice " + vertiveFinal + ":");

        comparaciones++;
        while (verticeActual != vertiveFinal) {
            comparaciones++;
            System.out.print(verticeActual + " -> ");
            visitado[verticeActual] = true; asignaciones++;

            int nextVertex = findClosestNeighbor(grafo.matriz[verticeActual], visitado); asignaciones++; 
            comparaciones++;
            if (nextVertex == -1) {
                System.out.println("No se puede llegar al vértice final.");
                
                asignaciones++;
                return;
            }

            distanciaTotal += grafo.matriz[verticeActual][nextVertex];
            verticeActual = nextVertex; 
        }

        System.out.println(vertiveFinal);
        System.out.println("Distancia total: " + distanciaTotal);
        
    }

    public static int findClosestNeighbor(int[] distancias, boolean[] visitados) {
        
        
        int distanciaMinima = Integer.MAX_VALUE; asignaciones++; 
        int verticeMasCercano = -1; asignaciones++; 

        comparaciones++; // contador del for
        asignaciones++; // contador del for


        for (int i = 0; i < distancias.length; i++) {
            comparaciones++; // contador del for
            asignaciones++; // contador del for
            comparaciones+=2;
            if (!visitados[i] && distancias[i] != 0 && distancias[i] < distanciaMinima) {
                distanciaMinima = distancias[i]; asignaciones++;
                verticeMasCercano = i; asignaciones++;
            }
        }
        asignaciones++;
        return verticeMasCercano;
    }

    public static void printGraph(Grafo grafo) {
        System.out.println("Representación del Grafo:");
        for (int i = 0; i < grafo.vertices; i++) {
            for (int j = 0; j < grafo.vertices; j++) {
                System.out.print(grafo.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
