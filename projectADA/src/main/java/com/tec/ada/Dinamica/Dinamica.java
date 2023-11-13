package com.tec.ada.Dinamica;
import java.time.Instant;
import java.util.Arrays;
import java.util.Stack;

public class Dinamica {

    static int comparaciones = 0;
    static int asignaciones = 0;
    static int instrucciones = 0;
  

    public static void main() {
        // Registro del tiempo de inicio de la ejecución.
        long startTime = Instant.now().toEpochMilli();

        Grafo grafo = new Grafo(60); // Ajusta el número de vértices según sea necesario

        int verticeInicio = 0; // Vértice de origen
        int verticeFinal = 9; // Vértice final

        dijkstra(grafo, verticeInicio, verticeFinal);
        // Registro del tiempo de finalización de la ejecución.
        long endTime = Instant.now().toEpochMilli();
        // Cálculo del tiempo transcurrido en segundos y muestra en pantalla.
        long timeElapsed = endTime - startTime;
        
        System.out.println("\nExecution time in milliseconds: " + timeElapsed);
        System.out.println("Asignaciones " + asignaciones);
        System.out.println("Comparaciones " + comparaciones);
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

    public static void dijkstra(Grafo grafo, int verticeInicio, int verticeFinal) {
     
        asignaciones++; asignaciones++; asignaciones++; 
        int[] distancia = new int[grafo.vertices]; asignaciones++; 
        int[] padre = new int[grafo.vertices]; asignaciones++; 
        boolean[] visitados = new boolean[grafo.vertices]; asignaciones++; 

        Arrays.fill(distancia, Integer.MAX_VALUE);
        distancia[verticeInicio] = 0; asignaciones++;


        comparaciones++; // contador del for
        asignaciones++; // contador del for
        
        for (int i = 0; i < grafo.vertices - 1; i++) {
            comparaciones++; // contador del for
            asignaciones++; // contador del for
            int minimoVertices = encontratMinimoVertices(distancia, visitados); asignaciones++; 
            visitados[minimoVertices] = true; asignaciones++;


            comparaciones++; // contador del for
            asignaciones++; // contador del for
            for (int j = 0; j < grafo.vertices; j++) {
                
                comparaciones++; // contador del for
                comparaciones++; // contador del if
                asignaciones++; // contador del for
                comparaciones++; // contador del if
                if (!visitados[j] && grafo.matriz[minimoVertices][j] != 0
                        && distancia[minimoVertices] != Integer.MAX_VALUE
                        && distancia[minimoVertices] + grafo.matriz[minimoVertices][j] < distancia[j]) {
                    distancia[j] = distancia[minimoVertices] + grafo.matriz[minimoVertices][j]; asignaciones++;
                    padre[j] = minimoVertices; asignaciones++;
                }
            }
        }
        asignaciones+=4;
        printShortestPath(distancia, padre, verticeInicio, verticeFinal);
    }

    public static int encontratMinimoVertices(int[] distancia, boolean[] visitados) {
     
        asignaciones+=3;
        int minimoVertices = -1; asignaciones++;
       
        comparaciones++; // contador del for
        asignaciones++; // contador del for
        for (int i = 0; i < distancia.length; i++) {
            
            comparaciones++; // contador del for
            comparaciones++; // contador del if
            comparaciones++; // contador del if
            asignaciones++; // contador del for
            if (!visitados[i] && (minimoVertices == -1 || distancia[i] < distancia[minimoVertices])) {
                minimoVertices = i; asignaciones++;
            }
        }
        asignaciones++;
        return minimoVertices;
    }

    public static void printShortestPath(int[] distancia, int[] padre, int verticeInicio, int verticeFinal) {
        System.out.println("Distancia total desde el vértice " + verticeInicio + " hasta el vértice " + verticeFinal + ": " + distancia[verticeFinal]);

        System.out.println("Ruta desde el vértice " + verticeInicio + " hasta el vértice " + verticeFinal + ": ");
        Stack<Integer> ruta = new Stack<>();
        int actual = verticeFinal;
        ruta.push(actual);

        while (padre[actual] != verticeInicio) {
            actual = padre[actual];
            ruta.push(actual);
        }

        System.out.print(verticeInicio + " -> ");
        while (!ruta.isEmpty()) {
            System.out.print(ruta.pop());
            if (!ruta.isEmpty()) {
                System.out.print(" -> ");
            }
        }
    }
}
