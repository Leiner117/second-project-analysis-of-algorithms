package com.tec.matrixGraph;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MatrixGraph {

    public static int graph[][] = new int[25][25];
    public static List<String> vistedList = new LinkedList<>(); //para guardar los visitados
    public static int shortRange = 99999;
    public static String mRouter = "";
    
    public static void createGraphs() {
        Random aleatorio = new Random();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = aleatorio.nextInt(100);
            }
        }
    }



}
