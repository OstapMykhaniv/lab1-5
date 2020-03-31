package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    private static final int VNUMB = 6;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] matrix = readFile("matrix.txt");
        System.out.println(FordFulkerson.maxFlow(matrix, 0, 5));


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
