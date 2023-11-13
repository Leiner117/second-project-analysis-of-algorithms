package com.tec.ada;
import com.tec.matrixGraph.MatrixGraph;

import java.util.List;

import com.tec.geneticAlgorithm.GeneticAlgorithm;
import com.tec.geneticAlgorithm.Route;
import java.util.Scanner;
import com.tec.ada.backTracking.backTracking;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Menu Principal");
        System.out.println("Seleccione la poblacion inicial");
        int poblation = selectPoblation();
        
        MatrixGraph.createGraphs(poblation);
        while (true){
            int algorithm = selectAlgorithm();
            switch (algorithm){
            case 1:
                //MatrixGraph.voraz();
                break;
            case 2:
                
                //MatrixGraph.dinamico();
                break;
            case 3:
                backTracking(poblation);

                break;
            case 4:
                GeneticAlgorithm(poblation);
                break;
            case 5:
                System.exit(0);
                break;
            
        }
        }
        
        
    }
    //menu para seleccionar el algoritmo
    public static int selectAlgorithm(){
        Scanner scanner = new Scanner(System.in);
        int algorithm = 0;
        int count = 1;
        System.out.println("Seleccione el algoritmo a utilizar");
        System.out.println(count + ". Voraz");
        count++;
        System.out.println(count + ". Dinamico");
        count++;
        System.out.println(count + ". Backtracking");
        count++;
        System.out.println(count + ". Algoritmo Genetico");
        count++;
        System.out.println(count + ". Salir");
        //Seleccionar la opcion del usuario
       
        while (algorithm < 1 || algorithm > 6){
            System.out.println("Ingrese la opcion deseada");
            algorithm = scanner.nextInt();
            if (algorithm < 1 || algorithm > 6){
                System.out.println("Opcion invalida");
            }
        }
        return algorithm;
    }
    //menu para seleccionar la cantidad de poblacion
    public static int selectPoblation(){
        Scanner scanner = new Scanner(System.in);
        int poblation = 0;
        int count = 1;
        System.out.println("Seleccione la cantidad de poblacion");
        for (int i = 10;i <= 60;i+=10){
            System.out.println(count + ". " + i);
            count++;
        }
        //Seleccionar la opcion del usuario
       
        while (poblation < 1 || poblation > 6){
            System.out.println("Ingrese la opcion deseada");
            poblation = scanner.nextInt();
            if (poblation < 1 || poblation > 6){
                System.out.println("Opcion invalida");
            }
        }
        return poblation*10;
    }
    public static void GeneticAlgorithm(int populationSize){
        long startTime = System.currentTimeMillis();
        // Obtén una instancia de la clase Runtime
        Runtime runtime = Runtime.getRuntime();

        // Llama al método gc() para solicitar la ejecución del recolector de basura
        runtime.gc();
        MatrixGraph.createGraphs(populationSize);
        int generations = 20;
        List<Route> finalPopulation = GeneticAlgorithm.geneticAlgorithm(populationSize, 0, 9, generations, MatrixGraph.graph);
        // Obtén la memoria total del sistema
        long memoriaTotal = runtime.totalMemory();
        long memoriaLibre = runtime.freeMemory();

        
        // Calcula la memoria utilizada
        long memoriaUsada = memoriaTotal - memoriaLibre;

        // Imprime los resultados
        long endTime = System.currentTimeMillis();
        System.out.println("Memoria total: " + memoriaTotal + " bytes");
        System.out.println("Memoria libre: " + memoriaLibre + " bytes");
        System.out.println("Memoria utilizada: " + memoriaUsada + " bytes");
        System.out.println("Comparaciones: " + GeneticAlgorithm.countComp);
        System.out.println("Asignaciones: " + GeneticAlgorithm.countAssig);
        System.out.println("Lineas ejecutadas: " + (GeneticAlgorithm.countComp+GeneticAlgorithm.countAssig));
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");
    }
    public static void backTracking(int poblation){
        long startTime = System.currentTimeMillis();
        // Obtén una instancia de la clase Runtime
        Runtime runtime = Runtime.getRuntime();

        // Llama al método gc() para solicitar la ejecución del recolector de basura
        runtime.gc();
        backTracking back = new backTracking(poblation, MatrixGraph.graph);
        back.encontrarRutaMasCorta();
        // Obtén la memoria total del sistema
        long memoriaTotal = runtime.totalMemory();
        long memoriaLibre = runtime.freeMemory();

        
        // Calcula la memoria utilizada
        long memoriaUsada = memoriaTotal - memoriaLibre;

        // Imprime los resultados
        long endTime = System.currentTimeMillis();
        System.out.println("Memoria total: " + memoriaTotal + " bytes");
        System.out.println("Memoria libre: " + memoriaLibre + " bytes");
        System.out.println("Memoria utilizada: " + memoriaUsada + " bytes");
        System.out.println("Comparaciones: " + back.comparaciones);
        System.out.println("Asignaciones: " + back.asignaciones);
        System.out.println("Lineas ejecutadas: " + (back.comparaciones+back.asignaciones));
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " milisegundos");

    }
   
}
