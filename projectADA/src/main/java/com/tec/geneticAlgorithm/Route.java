package com.tec.geneticAlgorithm;

import java.util.List;
import com.tec.geneticAlgorithm.GeneticAlgorithm;
public class Route {
        public List<Integer> path;
        public int distance;

        public Route(List<Integer> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
        /**
         * La función calcula la distancia total de un camino determinado en un gráfico.
         * 
         * @param path El parámetro "ruta" es una lista de números enteros que representan la secuencia
         * de nodos en un gráfico. Representa el orden en que se visitan los nodos en el gráfico.
         * @param graph El parámetro "gráfico" es una matriz bidimensional que representa las
         * distancias entre diferentes nodos en un gráfico. Cada elemento de la matriz representa la
         * distancia entre dos nodos.
         * @return El método devuelve un valor entero, que representa la distancia total calculada en
         * función de la ruta y el gráfico dados.
         */
        public static int calculateDistance(List<Integer> path, int[][] graph) {
            int distance = 0;GeneticAlgorithm.countAssig++;
            GeneticAlgorithm.countAssig++;
            for (int i = 0; i < path.size() - 1; i++) {
                GeneticAlgorithm.countAssig++;
                GeneticAlgorithm.countComp++;
                distance += graph[path.get(i)][path.get(i + 1)];
            }
            GeneticAlgorithm.countAssig++;
            return distance;
        }
        
    }