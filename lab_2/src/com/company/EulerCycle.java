package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EulerCycle {


    public int[][] checkUnevenVertices(List<Character> vertices, List<Edge> edges) {
        boolean evenGraph = true;
        int[][] degVertices = new int[vertices.size()][2];
        for (int i = 0; i < vertices.size(); i++) {
            degVertices[i][0] = 0;
            degVertices[i][1] = i;
            for (Edge edge : edges) {
                if (vertices.get(i) == edge.getU() || vertices.get(i) == edge.getV()) {
                    degVertices[i][0]++;
                }
            }
            if (degVertices[i][0] % 2 == 1) {
                evenGraph = false;
            }
        }
        if (evenGraph == true) {
            return null;
        } else {
            return degVertices;
        }
    }

    public void eulerProcess(char verticeStart, List<Character> verticesParam, List<Edge> edgesParam) {
        List<Character> vertices = new ArrayList<>(verticesParam);
        List<Edge> edges = new ArrayList<>(edgesParam);
        Stack<Character> path = new Stack<>();
        System.out.println();

        char w;
        path.push(verticeStart);
        boolean b = false;
        while (path.size() != 0) {
            w = path.peek();
            for (char u : vertices) {
                for (int i = 0; i < edges.size(); i++) {

                    if (toFindEdgeWhithVertices(w, u, edges.get(i)) == true) {
                        path.push(u);
                        edges.remove(edges.get(i));
                        b=true;
                        break;
                    }

                }
                if(b==true){
                    b=false;
                    break;
                }

            }
            if (w == path.peek()) {
                path.pop();
                System.out.printf("(%s)", w);
                if (path.size() != 0) {
                    System.out.print("->");
                }
            }
        }
    }

    private boolean toFindEdgeWhithVertices(char u, char v, Edge edge) {
        if ((u == edge.getU() && v == edge.getV()) || (u == edge.getV() && v == edge.getU()))
            return true;
        else
            return false;
    }
}

