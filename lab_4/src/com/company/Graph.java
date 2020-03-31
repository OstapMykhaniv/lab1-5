package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Graph {
    static private int count;
    private int[][] matrix;
    private boolean[] marks;

    public Graph(int count){
        this.count=count;
        matrix=new int[count][count];
        marks=new boolean[count];
    }

    public void setEdge(int a,int b,int weight){
        matrix[a][b]=weight;
        matrix[b][a]=weight;
    }

    public int getEdge(int a,int b){
        return matrix[a][b];
    }

    public boolean hasEdge(int a,int b){
        return matrix[a][b]!=0;
    }

    public static Graph load(String filename,int count) throws IOException {
        Graph graph=new Graph(count);
graph.matrix=readFile(filename);
//        while(sc.hasNext()){
//            int a=sc.nextInt();
//            int b=sc.nextInt();
//            int weight=sc.nextInt();
//
//            graph.setEdge(a,b,weight);
//        }

        return graph;
    }
     static private int[][] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        int matrix[][] = new int[count][count];
        String line ;
        String array[];
        for (int i = 0; i < count; i++) {
            line = scanner.nextLine();
            array = line.split(" ");
            for (int j = 0; j < count; j++) {
                matrix[i][j] = Integer.valueOf(array[j]);
            }
        }
        return matrix;
    }


    public boolean enter(int pos){
        if(marks[pos]){
            return false;
        }else{
            marks[pos]=true;
            return true;
        }
    }

    public void leave(int pos){
        marks[pos]=false;
    }

    public int getCount(){
        return count;
    }

    public boolean allVisited(){
        for(int i=0;i<marks.length;i++){
            if(!marks[i])return false;
        }
        return true;
    }
}
