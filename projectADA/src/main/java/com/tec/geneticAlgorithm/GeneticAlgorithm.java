package com.tec.geneticAlgorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.tec.geneticAlgorithm.Route;
public class GeneticAlgorithm{
    public int populationSize = getInitialPopulationSize();

    public static int getInitialPopulationSize() {
        Random randomNum = new Random();
        return (randomNum.nextInt(6)+1)*10;
    }

    /**
     * La función genera una población inicial de rutas para un algoritmo genético, donde cada ruta
     * comienza desde un origen determinado y termina en un destino determinado en un gráfico.
     * 
     * @param populationSize El tamaño de la población es el número de rutas que se generarán en la
     * población inicial. Determina el tamaño de la lista de rutas en la población.
     * @param origin El parámetro de origen representa el vértice inicial de las rutas en la población.
     * Es un valor entero que corresponde a un vértice del gráfico.
     * @param destination El parámetro de destino representa el índice del vértice de destino en el
     * gráfico. Se utiliza para determinar cuándo dejar de agregar vértices al camino.
     * @param graph El parámetro del gráfico es una matriz 2D que representa las distancias entre los
     * vértices de un gráfico. Cada elemento de la matriz representa la distancia entre dos vértices.
     * Por ejemplo, el gráfico [i] [j] representa la distancia entre el vértice i y el vértice j.
     * @return El método devuelve una lista de objetos de ruta.
     */
    public static List<Route> generateInitialPopulation(int populationSize, int origin, int destination, int[][] graph) {
        List<Route> population = new ArrayList<>();
        Random random = new Random();

            for (int i = 0; i < populationSize; i++) {
                List<Integer> path = new ArrayList<>();
                path.add(origin);
                while (path.get(path.size() - 1) != destination) {
                    int nextVertex = random.nextInt(graph.length);
                    if (!path.contains(nextVertex)) {
                        path.add(nextVertex);
                    }
                }
                int distance = Route.calculateDistance(path, graph);
                population.add(new Route(path, distance));
            }
            return population;
    }


    public static void printCrossover(Route parent1, Route parent2, Route child) {
        System.out.println("Padre 1: " + parent1.path + ", Distancia: " + parent1.distance);
        System.out.println("Padre 2: " + parent2.path + ", Distancia: " + parent2.distance);
        System.out.println("Hijo: " + child.path + ", Distancia: " + child.distance);
        System.out.println("--------------------");
    }
    public static void printMutation(Route original, Route mutated) {
        System.out.println("Original: " + original.path + ", Distancia: " + original.distance);
        System.out.println("Mutado: " + mutated.path + ", Distancia: " + mutated.distance);
        System.out.println("--------------------");
    }
    public static List<Route> geneticAlgorithm(int populationSize, int origin, int destination, int generations, int[][] graph) {
        List<Route> population = generateInitialPopulation(populationSize, origin, destination, graph);

        for (int generation = 0; generation < generations; generation++) {
            // Selection
            Collections.sort(population, Comparator.comparingInt(a -> a.distance));
            population = population.subList(0, populationSize / 2);
        }

        // Crossover
        List<Route> newPopulation = new ArrayList<>();
        while (newPopulation.size() < populationSize * 2) {
            Random random = new Random();
            int index1 = random.nextInt(population.size());
            int index2 = random.nextInt(population.size());

            Route parent1 = population.get(index1);
            Route parent2 = population.get(index2);

            // Crear conjuntos de vértices de las rutas de los padres
            Set<Integer> parent1Vertices = new HashSet<>(parent1.path);
            Set<Integer> parent2Vertices = new HashSet<>(parent2.path);

            // Crear la intersección de los conjuntos de vértices
            Set<Integer> commonVertices = new HashSet<>(parent1Vertices);
            commonVertices.retainAll(parent2Vertices);

            List<Integer> childPath = new ArrayList<>();
            for (Integer vertex : commonVertices) {
                if (!childPath.contains(vertex)) {
                    childPath.add(vertex);
                }
            }

            // Rellenar el resto de la ruta del hijo con vértices que no están en la intersección
            for (Integer vertex : parent1.path) {
                if (!childPath.contains(vertex)) {
                    childPath.add(vertex);
                }
            }

            // Si la ruta del hijo es única, agregarla a la nueva población
            if (newPopulation.stream().noneMatch(route -> route.path.equals(childPath))) {
                int childDistance = Route.calculateDistance(childPath, graph);
                Route child = new Route(childPath, childDistance);
                newPopulation.add(child);
                printCrossover(parent1, parent2, child);
            }
        }


        population.addAll(newPopulation);


        // Mutation
            
        for (int i = 0; i < population.size(); i++) {
            Route route = population.get(i);
            boolean isDuplicate = false;

            for (int j = 0; j < population.size(); j++) {
                if (i != j && route.path.equals(population.get(j).path)) {
                    isDuplicate = true;
                    break;
                }
            }

            // Si se encuentra una ruta duplicada, aplicar una mutación
            if (isDuplicate) {
                List<Integer> originalPath = new ArrayList<>(route.path);
                int originalDistance = route.distance;

                int index1 = new Random().nextInt(route.path.size());
                int index2 = new Random().nextInt(route.path.size());
                Collections.swap(route.path, index1, index2);
                route.distance = Route.calculateDistance(route.path, graph);

                // Si la mutación empeora la distancia, deshacer la mutación
                if (route.distance > originalDistance) {
                    route.path = originalPath;
                    route.distance = originalDistance;
                }
                else{
                    printMutation(new Route(originalPath, originalDistance), route);
                }
               
            }
        }
        Collections.sort(population, Comparator.comparingInt(a -> a.distance));
        return population;
    }
}
