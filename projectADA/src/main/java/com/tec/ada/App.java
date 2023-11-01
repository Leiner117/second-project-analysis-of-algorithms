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
        
        int populationSize = 50;
        MatrixGraph.createGraphs(populationSize);
        int generations = 20;
        MatrixGraph.printGraph();
        List<Route> finalPopulation = GeneticAlgorithm.geneticAlgorithm(populationSize, 0, 9, generations, MatrixGraph.graph);
    }
   
}
