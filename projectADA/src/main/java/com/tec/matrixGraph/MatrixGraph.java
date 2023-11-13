package com.tec.matrixGraph;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import com.tec.geneticAlgorithm.GeneticAlgorithm;

/**
 * La clase MatrixGraph crea un gráfico con distancias aleatorias entre vértices.
 */
public class MatrixGraph {

    public static int graph[][];
    public static List<String> vistedList = new LinkedList<>(); //para guardar los visitados
    public static int shortRange = 99999;
    public static String mRouter = "";
    
    /**
     * La función crea un gráfico con n vértices y asigna pesos aleatorios a los bordes.
     * 
     * @param n El parámetro "n" representa el número de vértices del gráfico.
     */
    public static void createGraphs(int n) {
        GeneticAlgorithm.countAssig++;
        graph = new int[n][n]; 
        Random randomNum = new Random(); GeneticAlgorithm.countAssig++;
        GeneticAlgorithm.countAssig++;
        for (int i = 0; i < graph.length; i++) {
            GeneticAlgorithm.countComp++;
            GeneticAlgorithm.countAssig++;
            GeneticAlgorithm.countAssig++;
            GeneticAlgorithm.countComp++;
            for (int j = 0; j < graph[0].length; j++) {
                GeneticAlgorithm.countComp++;
                GeneticAlgorithm.countAssig++;
                if (i == j) {
                    GeneticAlgorithm.countAssig++;
                    // La distancia de un vértice a sí mismo es 0
                    graph[i][j] = 0; GeneticAlgorithm.countAssig++;
                } else {
                    // Generar un número aleatorio entre 1 y 250
                    graph[i][j] = randomNum.nextInt(250) + 1; GeneticAlgorithm.countAssig++;
                }
            }
        }
    }
    
    
    /**
     * La función "printGraph" imprime la matriz de adyacencia de un gráfico.
     */
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
