/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec.ada.backTracking;

/**
 * 10/28/2023
 * @author josuc
 */

/**
 * 
 * 
 */
public class backTracking {
    private int[][] matrizAdyacencia;
    private int numVertices;
    public int asignaciones;
    public int comparaciones;
    
    public backTracking (int numVertices, int[][] matrizAdyacencia) {
        this.numVertices = numVertices;
        this.matrizAdyacencia = matrizAdyacencia;
    }
    
    /**
    * Encuentra la ruta más corta entre los vértices del grafo utilizando un algoritmo de fuerza bruta (backtracking).
    * 
    * @return La distancia mínima entre los vértices del grafo.
    */
    public int encontrarRutaMasCorta() {
        int[] camino = new int[numVertices]; // Almancena el camino actual
        boolean[] visitado = new boolean[numVertices]; // Arreglo para saber que vertices se han visitado
        int[] mejorCamino = new int[numVertices]; // Almacena el camino mas corto
        int[] distanciaMinima = { Integer.MAX_VALUE }; // Almacena la distancia minima, se inicializa con un valor maximo
        camino[0] = 0;
        visitado[0] = true;
        asignaciones += 6;
        backtracking(0, camino, visitado, mejorCamino, distanciaMinima);
        asignaciones ++;
        return distanciaMinima[0];
    }
    
    /**
    * Realiza un proceso de búsqueda recursiva para encontrar la ruta más corta entre los vértices del grafo.
    * 
    * @param pos Posición actual en el camino.
    * @param camino Arreglo que almacena el camino actual.
    * @param visitado Arreglo para realizar un seguimiento de los vértices visitados.
    * @param mejorCamino Arreglo para almacenar la mejor ruta encontrada.
    * @param distanciaMinima Arreglo que almacena la distancia mínima actual.
    */
    private void backtracking(int pos, int[] camino, boolean[] visitado, int[] mejorCamino, int[] distanciaMinima) {
        asignaciones += 5;
        if (pos == numVertices - 1) { // comprueba si se ha llegado al ultimo vertice
            comparaciones ++;
            int distancia = calcularDistancia(camino); // calcula la distancia del camino actual
            asignaciones ++;
            if (distancia < distanciaMinima[0]) { // compara la distancia actual con la minima
                comparaciones ++;
                distanciaMinima[0] = distancia; // si es menor asigna la distancia actual a la distancia minima
                asignaciones ++;
                System.arraycopy(camino, 0, mejorCamino, 0, numVertices); // copia la lista con el camino actual en la lista con el mejor camino
                asignaciones ++;
            }
            asignaciones ++;
            return;
        }
        asignaciones ++;
        comparaciones ++; // comparacion falsa
        for (int i = 0; i < numVertices; i++) { // Recorre los vertices no visitados
            asignaciones ++;
            comparaciones ++;
            if (!visitado[i]) { // verifica si el vertice actual no esta visitado
                comparaciones ++;
                visitado[i] = true; // si no esta visitado lo asigna como visitado
                camino[pos + 1] = i; // aniade el siguiente vertice al camino
                backtracking(pos + 1, camino, visitado, mejorCamino, distanciaMinima); // llama a la funcion de forma recursiva por el siguiente vertices
                visitado[i] = false; // desmarca el vertice si no se encontro mejor ruta
                asignaciones += 4;
            }
        }
    }
    /**
    * Calcula la distancia total a lo largo de un camino dado en el grafo.
    * 
    * @param camino Arreglo que representa el camino cuya distancia se calculará.
    * @return La distancia total a lo largo del camino.
    */
    private int calcularDistancia(int[] camino) {
        asignaciones ++;
        int distancia = 0;// Inicializa la variable que almacenará la distancia.
        asignaciones += 2;
        comparaciones ++; //comparacion falsa
        for (int i = 0; i < numVertices - 1; i++) {// Recorre el camino y suma las distancias entre los vértices del camino actual
            asignaciones ++;
            comparaciones ++;
            distancia += matrizAdyacencia[camino[i]][camino[i + 1]]; // Suma la distancia entre los vértices del camino
            asignaciones ++;
        }
        asignaciones ++;
        return distancia;// Devuelve la distancia total a lo largo del camino.
    }
    
}
