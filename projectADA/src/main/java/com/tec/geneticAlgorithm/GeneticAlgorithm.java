package com.tec.geneticAlgorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
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


    public static void printCrossover(Route parent1, Route parent2, List<Route>childrenList) {
        if (childrenList.size() != 0){
            System.out.println("Padre 1: " + parent1.path + ", Distancia: " + parent1.distance);
            System.out.println("Padre 2: " + parent2.path + ", Distancia: " + parent2.distance);
            for(int i =0;i< childrenList.size();i++){
                System.out.println("Hijo"+i+":" + childrenList.get(i).path + ", Distancia: " + childrenList.get(i).distance);
            }
            System.out.println("--------------------");
        }
        
    }
    public static void printMutation(Route original, Route mutated) {
        System.out.println("Original: " + original.path + ", Distancia: " + original.distance);
        System.out.println("Mutado: " + mutated.path + ", Distancia: " + mutated.distance);
        System.out.println("--------------------");
    }
    public static void printBestnewRoutes(List<Route> bestNewRoutes) {
        System.out.println("Mejores nuevas rutas: ");
        for (int i = 0; i < bestNewRoutes.size(); i++) {
            System.out.println("Ruta " + i + ": " + bestNewRoutes.get(i).path + ", Distancia: " + bestNewRoutes.get(i).distance);
        }
        System.out.println("--------------------");
    }
    public static Route mutate(Route route, List<Route> population, int[][] graph) {
        boolean isDuplicate = false;
    
        for (Route r : population) {
            if (route.path.equals(r.path)) {
                isDuplicate = true;
                break;
            }
        }
    
        // Si se encuentra una ruta duplicada, aplicar una mutación
        if (isDuplicate && route.path.size() > 2) {
            List<Integer> originalPath = new ArrayList<>(route.path);
            int originalDistance = route.distance;
    
            int maxAttempts = 100;
            int attempts = 0;
    
            while (attempts < maxAttempts) {
                int index1 = new Random().nextInt(route.path.size() - 2) + 1;
                int index2 = new Random().nextInt(route.path.size() - 2) + 1;
    
                // Validación de la existencia de una arista entre los vértices a intercambiar
                if (graph[route.path.get(index1)][route.path.get(index2)] > 0) {
                    Collections.swap(route.path, index1, index2);
                    route.distance = Route.calculateDistance(route.path, graph);
    
                    // Si la mutación mejora la distancia y no existe en la población original, salir del bucle
                    if (route.distance < originalDistance && population.stream().noneMatch(r -> r.path.equals(route.path))) {
                        printMutation(new Route(originalPath, originalDistance), route);
                        break;
                    } else {
                        // Deshacer la mutación si no mejora la distancia o ya existe en la población original
                        route.path = new ArrayList<>(originalPath);
                        route.distance = originalDistance;
                    }
                }
    
                attempts++;
            }
    
            // Si después de maxAttempts la mutación no mejora la distancia, eliminar la ruta
            if (attempts == maxAttempts) {
                return null;
            }
        }
    
        return route;
    }
    
    
    public static List<Route> geneticAlgorithm(int populationSize, int origin, int destination, int generations, int[][] graph) {
        List<Route> population = generateInitialPopulation(populationSize, origin, destination, graph);
        int po = 0;
        for (int generation = 0; generation < generations; generation++) {
            // Selection
            Collections.sort(population, Comparator.comparingInt(a -> a.distance));
    
           // Crossover
            List<Route> newPopulation = new ArrayList<>();

            while (newPopulation.size() < populationSize * 2) {
                Random random = new Random();
                Route parent1 = population.get(random.nextInt(population.size()));
                Route parent2;
                do {
                    parent2 = population.get(random.nextInt(population.size()));
                } while (parent1.path.equals(parent2.path));

                
                // Crear rutas para los hijos
                List<Integer> childPath1 = new ArrayList<>();
                List<Integer> childPath2 = new ArrayList<>();
                
                // Tomar una parte de la ruta del padre 1 y el resto de la ruta del padre 2
                int crossoverPoint = random.nextInt(Math.min(parent1.path.size() - 1, parent2.path.size() - 1)) + 1;
                childPath1.addAll(parent1.path.subList(0, crossoverPoint));
                childPath1.addAll(parent2.path.subList(crossoverPoint, parent2.path.size()));
                
                // Tomar una parte de la ruta del padre 2 y el resto de la ruta del padre 1
                childPath2.addAll(parent2.path.subList(0, crossoverPoint));
                childPath2.addAll(parent1.path.subList(crossoverPoint, parent1.path.size()));
                
                List<Route> childrens = new ArrayList<>();

                // Asegurarse de que la ruta del hijo comienza en el vértice de origen y termina en el vértice de destino
                if (childPath1.get(0) == origin && childPath1.get(childPath1.size() - 1) == destination) {
                    int childDistance = Route.calculateDistance(childPath1, graph);
                    Route child1 = new Route(childPath1, childDistance);
                    if (newPopulation.stream().noneMatch(route -> route.path.equals(childPath1))) {
                        newPopulation.add(child1);
                        childrens.add(child1);
                    }
                    else{
                        
                        if (child1 != null){
                            newPopulation.add(child1);
                            childrens.add(child1);
                        }
                    }
                }
                                
                if (childPath2.get(0) == origin && childPath2.get(childPath2.size() - 1) == destination) {
                    int childDistance = Route.calculateDistance(childPath2, graph);
                    Route child2 = new Route(childPath2, childDistance);
                    if (newPopulation.stream().noneMatch(route -> route.path.equals(childPath2))) {
                        newPopulation.add(child2);
                        childrens.add(child2);
                    }
                    else{
                        
                        if (child2 != null){
                            newPopulation.add(child2);
                            childrens.add(child2);
                        }
                    }
                }
            
                printCrossover(parent1, parent2, childrens);
                
            }
        
            //Actualizar la población para la próxima generación
            Collections.sort(population, Comparator.comparingInt(a -> a.distance));
    
            // Mantener solo los 5 mejores de la nueva población y descartar los 5 peores
            List<Route> bestNewRoutes = newPopulation.subList(0, 5);
            List<Route> worstOldRoutes = population.subList(populationSize - 5, populationSize);
            population.removeAll(worstOldRoutes);
            population.addAll(0, bestNewRoutes);
            System.out.println(po++);
        }
    
        Collections.sort(population, Comparator.comparingInt(a -> a.distance));
        System.out.println(population.get(0).path+"->"+population.get(0).distance);
        System.out.println(population.get(1).path+"->"+population.get(1).distance);
        System.out.println(population.get(2).path+"->"+population.get(3).distance);
        return population;
    }
    
}
