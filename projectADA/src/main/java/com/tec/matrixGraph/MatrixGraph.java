package com.tec.matrixGraph;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MatrixGraph {

    public static int graph[][];
    public static List<String> vistedList = new LinkedList<>(); //para guardar los visitados
    public static int shortRange = 99999;
    public static String mRouter = "";
    
    public static void createGraphs(int n) {
        graph = new int[n][n];
        Random randomNum = new Random();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (i == j) {
                    // La distancia de un vértice a sí mismo es 0
                    graph[i][j] = 0;
                } else {
                    // Generar un número aleatorio entre 1 y 250
                    graph[i][j] = randomNum.nextInt(250) + 1;
                }
            }
        }
    }
    
    //Método para imprimir la matriz
    public static void printGraph() {
        System.out.println("Matriz de adyacencia");
        for (int i = 0; i < graph.length; i++) {
            System.out.print("|");
            for (int j = 0; j < graph[0].length; j++) {
                System.out.print(graph[i][j]);
                if (j != graph[0].length - 1) {
                    System.out.print("\t");
                }
            }
           System.out.println(" ");
        }
    }


}
