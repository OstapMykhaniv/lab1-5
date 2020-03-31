package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    static final int VNUMB = 6;


    public static void main(String[] args) throws FileNotFoundException {
        int matrix[][];
        matrix = readFile("matrix.txt");

        Prim prim = new Prim(VNUMB, matrix);
        System.out.println(prim.calculateMin());
        System.out.println(prim.calculateMax());


    }

    static public int[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        int matrix[][] = new int[VNUMB][VNUMB];
        String line ;
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
