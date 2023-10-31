package com.tec.geneticAlgorithm;

import java.util.List;

public class Route {
        public List<Integer> path;
        public int distance;

        public Route(List<Integer> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
        public static int calculateDistance(List<Integer> path, int[][] graph) {
            int distance = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                distance += graph[path.get(i)][path.get(i + 1)];
            }
            return distance;
        }
        
    }