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