package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int VNUMB = 5;


    public static void main(String[] args) throws FileNotFoundException {

        int[][] matrix = readFile("Input(1).txt");

        List<Character> vertices = new ArrayList<>();
        for (int i = 0; i < VNUMB; i++) {
            vertices.add((char) (65 + i));
        }
        List<Edge> inputedEdges = new ArrayList<>();
        for (int i = 0; i < VNUMB - 1; i++) {
            char u = vertices.get(i);
            for (int j = 1 + i; j < VNUMB; j++) {
                char v = vertices.get(j);
                if (matrix[i][j] > 0 && matrix[i][j] < 1000) {
                    Edge edge = new Edge(u, v, matrix[i][j]);
                    inputedEdges.add(edge);
                }
            }
        }

        List<Edge> resultEdges = new ArrayList(inputedEdges);

        EulerCycle euler = new EulerCycle();
        int[][]unevenVertices = euler.checkUnevenVertices(vertices, inputedEdges);
        int countOfUnevenVertices = 0;
        if (unevenVertices != null) {
            System.out.println("Graph has uneven nodes: ");
            for (int i = 0; i < unevenVertices.length ; i++) {
                if (unevenVertices[i][0] %2 == 1){
                    System.out.printf("deg(%s)=%d\t", (char)(unevenVertices[i][1]+65),unevenVertices[i][0]);
                    countOfUnevenVertices++;
                }
            }
            System.out.println();

            DuplicateEdgesInGraph modifyGraph = new DuplicateEdgesInGraph();
            List<Edge> edgesToDuplicate = modifyGraph.edgesToDuplicate(inputedEdges, unevenVertices);

            List<Edge> duplicateEdges = modifyGraph.duplicateEdges(edgesToDuplicate, countOfUnevenVertices);
            int sum = 0;
            System.out.println("This edges should be duplicated:");
            for(Edge edge : duplicateEdges) {
                System.out.printf("(%s,%s) ", edge.getU(), edge.getV());
                sum += edge.getWeigth();
            }
            System.out.printf("with total weight %d \n", sum);

            for(Edge edge : duplicateEdges) {
                resultEdges.add(edge);
            }

            if (euler.checkUnevenVertices(vertices, resultEdges) == null) {
                System.out.println("Now Postman Problem could be solved!");
            }
        }

        System.out.println("Enter starting point (Example - A) -> ");

        Scanner sc=new Scanner(System.in);
        char verticeStart = sc.nextLine().charAt(0);


        euler.eulerProcess(verticeStart, vertices, resultEdges);

    }

    static public int[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        int matrix[][] = new int[VNUMB][VNUMB];
        String line;
        String array[];
        for (int i = 0; i < VNUMB; i++) {
            line = scanner.nextLine();
            array = line.split(" ");
            for (int j = 0; j < VNUMB; j++) {
                matrix[i][j] = Integer.valueOf(array[j]);
            }
        }
        return matrix;
    }
}

