package com.tec.geneticAlgorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import com.tec.geneticAlgorithm.Route;

public class GeneticAlgorithm{
    public static int countAssig = 0;
    public static int countComp = 0;
    public static int countMemory = 0;
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
        countAssig++;
        countAssig++;
        countAssig++;
        countAssig++;
        List<Route> population = new ArrayList<>(); countAssig++;
        Random random = new Random(); countAssig++;
        countAssig++; //asignacion i
        for (int i = 0; i < populationSize; i++) {
            countComp++;
            countAssig++;
            List<Integer> path = new ArrayList<>(); countAssig++;
            path.add(origin); countAssig++;
            countComp++;// falsa
            while (path.get(path.size() - 1) != destination) {
                countComp++;
                countAssig++;
                int nextVertex = random.nextInt(graph.length);
                countComp++;
                if (!path.contains(nextVertex)) {
                    countComp++;
                    countAssig++;
                    path.add(nextVertex);
                }
            }
            countAssig++;
            int distance = Route.calculateDistance(path, graph);
            countAssig++;
            population.add(new Route(path, distance));
        }
        countAssig++;
        return population;
    }


    /**
     * Imprime los padres y los hijos resultantes de un cruce de rutas.
     * @param parent1 La primera ruta padre.
     * @param parent2 La segunda ruta padre.
     * @param childrenList La lista de rutas hijas resultantes del cruce.
     */
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

    /**
     * Imprime la ruta original y la ruta mutada junto con sus respectivas distancias.
     * @param original La ruta original.
     * @param mutated La ruta mutada.
     */
    public static void printMutation(Route original, Route mutated) {
        System.out.println("Original: " + original.path + ", Distancia: " + original.distance);
        System.out.println("Mutado: " + mutated.path + ", Distancia: " + mutated.distance);
        System.out.println("--------------------");
    }

    /**
     * Imprime las mejores nuevas rutas con su respectiva distancia.
     * @param bestNewRoutes Lista de las mejores nuevas rutas.
     */
    public static void printBestnewRoutes(List<Route> bestNewRoutes) {
        System.out.println("Mejores nuevas rutas: ");
        for (int i = 0; i < bestNewRoutes.size(); i++) {
            System.out.println("Ruta " + i + ": " + bestNewRoutes.get(i).path + ", Distancia: " + bestNewRoutes.get(i).distance);
        }
        System.out.println("--------------------");
    }

    
/**
 * La función `mutate` toma una ruta, una población de rutas y un grafo como entrada, verifica si la
 * ruta está duplicada en la población y, de ser así, aplica una mutación a la ruta intercambiando dos
 * vértices si hay un borde. entre ellos y devuelve la ruta mutada.
 * 
 * @param route El parámetro de ruta es un objeto de tipo Ruta, que representa una ruta específica en
 * un gráfico. Contiene una lista de números enteros que representan la ruta de la ruta y un número
 * entero que representa la distancia de la ruta.
 * @param population El parámetro de población es una lista de objetos de ruta. Cada objeto Ruta
 * representa una posible solución a un problema y la población es una colección de estas soluciones.
 * @param graph El parámetro "gráfico" es una matriz 2D que representa el gráfico de vértices y
 * aristas. Cada elemento `graph[i][j]` representa el peso o la distancia entre el vértice `i` y el
 * vértice `j`.
 * @return El método devuelve un objeto Ruta.
 */
    public static Route mutate(Route route, List<Route> population, int[][] graph) {
        countAssig++;
        countAssig++;
        countAssig++;
        boolean isDuplicate = false; countAssig++;
        countAssig++;
        for (Route r : population) {
            countComp++;
            countComp++;
            if (route.path.equals(r.path)) {
                countComp++;
                isDuplicate = true;
                break;
            }
        }
    
        // Si se encuentra una ruta duplicada, aplicar una mutación
        countComp++;
        countComp++;
        if (isDuplicate && route.path.size() > 2) {
            countAssig++;
            countAssig++;
            List<Integer> originalPath = new ArrayList<>(route.path);
            int originalDistance = route.distance;
            countAssig++;
            countAssig++;
            int maxAttempts = 100;
            int attempts = 0;
            countComp++;
            while (attempts < maxAttempts) {
                countComp++;
                int index1 = new Random().nextInt(route.path.size() - 2) + 1;countAssig++;
                int index2 = new Random().nextInt(route.path.size() - 2) + 1;countAssig++;
    
                // Validación de la existencia de una arista entre los vértices a intercambiar
                countComp++;
                if (graph[route.path.get(index1)][route.path.get(index2)] > 0) {
                    countComp++;
                    Collections.swap(route.path, index1, index2);
                    countAssig++;
                    route.distance = Route.calculateDistance(route.path, graph);
    
                    // Si la mutación mejora la distancia y no existe en la población original, salir del bucle
                    countComp++;
                    countComp++;
                    if (route.distance < originalDistance && population.stream().noneMatch(r -> r.path.equals(route.path))) {
                        printMutation(new Route(originalPath, originalDistance), route);
                        break;
                    } else {
                        // Deshacer la mutación si no mejora la distancia o ya existe en la población original
                        countAssig++;
                        countAssig++;
                        route.path = new ArrayList<>(originalPath);
                        route.distance = originalDistance;
                    }
                }
    
                attempts++;countAssig++;
            }
    
            // Si después de maxAttempts la mutación no mejora la distancia, eliminar la ruta
            countComp++;
            if (attempts == maxAttempts) {
                countComp++;
                return null;
            }
        }
        countAssig++;
        return route;
    }
    
    
    /**
     * La función geneticAlgorithm implementa un algoritmo genético para encontrar las mejores rutas
     * entre un origen y un destino en un gráfico.
     * 
     * @param populationSize El tamaño de la población es el número de individuos (rutas) en cada
     * generación del algoritmo genético. Determina la diversidad de la población y afecta la velocidad
     * de convergencia del algoritmo.
     * @param origin El parámetro de origen representa el vértice inicial del gráfico. Es el vértice
     * desde donde comenzarán las rutas.
     * @param destination El parámetro "destino" representa el vértice o nodo del gráfico donde debe
     * terminar la ruta.
     * @param generations El parámetro "generaciones" representa el número de iteraciones o
     * generaciones que se ejecutará el algoritmo genético. Cada generación implica seleccionar los
     * mejores individuos de la población actual, realizar cruces para crear nuevos individuos y
     * actualizar la población para la próxima generación. El algoritmo repetirá este proceso durante
     * el número especificado de
     * @param graph El parámetro "gráfico" es una matriz 2D que representa las distancias entre los
     * vértices de un gráfico. Cada elemento de la matriz representa la distancia entre dos vértices.
     * Por ejemplo, el gráfico [i] [j] representa la distancia entre el vértice i y el vértice j.
     * @return El método devuelve una lista de objetos de ruta.
     */
    public static List<Route> geneticAlgorithm(int populationSize, int origin, int destination, int generations, int[][] graph) {
        countAssig++;
        countAssig++;
        countAssig++;
        countAssig++;
        countAssig++;
        List<Route> population = generateInitialPopulation(populationSize, origin, destination, graph); countAssig++;
        countAssig++;
        for (int generation = 0; generation < generations; generation++) {
            countComp++;
            countAssig++;
            // Selection
            Collections.sort(population, Comparator.comparingInt(a -> a.distance));
    
           // Crossover
            countAssig++;
            List<Route> newPopulation = new ArrayList<>();
            countComp++;
            while (newPopulation.size() < populationSize * 2) {
                countComp++;
                Random random = new Random(); 
                countAssig++;
                Route parent1 = population.get(random.nextInt(population.size()));
                countAssig++;
                Route parent2;
                countAssig++;
                countComp++;
                do {
                    countComp++;
                    parent2 = population.get(random.nextInt(population.size()));
                } while (parent1.path.equals(parent2.path));

                
                // Crear rutas para los hijos
                countAssig++;
                countAssig++;
                List<Integer> childPath1 = new ArrayList<>();
                List<Integer> childPath2 = new ArrayList<>();
                
                // Tomar una parte de la ruta del padre 1 y el resto de la ruta del padre 2
                countAssig++;
                int crossoverPoint = random.nextInt(Math.min(parent1.path.size() - 1, parent2.path.size() - 1)) + 1;
                childPath1.addAll(parent1.path.subList(0, crossoverPoint));countAssig++;
                childPath1.addAll(parent2.path.subList(crossoverPoint, parent2.path.size()));countAssig++;
                
                // Tomar una parte de la ruta del padre 2 y el resto de la ruta del padre 1
                childPath2.addAll(parent2.path.subList(0, crossoverPoint));countAssig++;
                childPath2.addAll(parent1.path.subList(crossoverPoint, parent1.path.size()));countAssig++;
                
                List<Route> childrens = new ArrayList<>();countAssig++;

                // Asegurarse de que la ruta del hijo comienza en el vértice de origen y termina en el vértice de destino
                countComp++;
                countComp++;

                if (childPath1.get(0) == origin && childPath1.get(childPath1.size() - 1) == destination) {
                    int childDistance = Route.calculateDistance(childPath1, graph);countAssig++;
                    Route child1 = new Route(childPath1, childDistance);countAssig++;
                    countComp++;
                    if (newPopulation.stream().noneMatch(route -> route.path.equals(childPath1))) {
                        countComp++;
                        newPopulation.add(child1);countAssig++;
                        childrens.add(child1);countAssig++;
                    }
                    else{
                        countComp++;
                        if (child1 != null){
                            countComp++;
                            newPopulation.add(child1);countAssig++;
                            childrens.add(child1);countAssig++;
                        }
                    }
                }
                countComp++;
                countComp++;
                if (childPath2.get(0) == origin && childPath2.get(childPath2.size() - 1) == destination) {
                    int childDistance = Route.calculateDistance(childPath2, graph);countAssig++;
                    Route child2 = new Route(childPath2, childDistance);countAssig++;
                    countComp++;
                    if (newPopulation.stream().noneMatch(route -> route.path.equals(childPath2))) {
                        newPopulation.add(child2);countAssig++;
                        childrens.add(child2);countAssig++;
                    }
                    else{
                        countComp++;
                        if (child2 != null){
                            newPopulation.add(child2);countAssig++;
                            childrens.add(child2);countAssig++;
                        }
                    }
                }
            
                printCrossover(parent1, parent2, childrens);
                
            }
        
            //Actualizar la población para la próxima generación
            Collections.sort(population, Comparator.comparingInt(a -> a.distance));
    
            // Mantener solo los 5 mejores de la nueva población y descartar los 5 peores
            List<Route> bestNewRoutes = newPopulation.subList(0, 5); countAssig++;
            List<Route> worstOldRoutes = population.subList(populationSize - 5, populationSize); countAssig++;
            population.removeAll(worstOldRoutes);
            population.addAll(0, bestNewRoutes);countAssig++;
        }
    
        Collections.sort(population, Comparator.comparingInt(a -> a.distance));
        countAssig++;
        return population;
    }
}
