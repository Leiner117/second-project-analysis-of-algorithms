package com.tec.ada;
import com.tec.matrixGraph.MatrixGraph;

import java.util.List;

import com.tec.geneticAlgorithm.GeneticAlgorithm;
import com.tec.geneticAlgorithm.Route;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Obtén una instancia de la clase Runtime
        Runtime runtime = Runtime.getRuntime();

        // Llama al método gc() para solicitar la ejecución del recolector de basura
        runtime.gc();
        int populationSize = 50;
        MatrixGraph.createGraphs(populationSize);
        int generations = 20;
        MatrixGraph.printGraph();
        List<Route> finalPopulation = GeneticAlgorithm.geneticAlgorithm(populationSize, 0, 9, generations, MatrixGraph.graph);
        // Obtén la memoria total del sistema
        long memoriaTotal = runtime.totalMemory();
        long memoriaLibre = runtime.freeMemory();

        // Calcula la memoria utilizada
        long memoriaUsada = memoriaTotal - memoriaLibre;

        // Imprime los resultados
        System.out.println("Memoria total: " + memoriaTotal + " bytes");
        System.out.println("Memoria libre: " + memoriaLibre + " bytes");
        System.out.println("Memoria utilizada: " + memoriaUsada + " bytes");
        System.out.println("Comparaciones: " + GeneticAlgorithm.countComp);
        System.out.println("Asignaciones: " + GeneticAlgorithm.countAssig);
    }
   
}
