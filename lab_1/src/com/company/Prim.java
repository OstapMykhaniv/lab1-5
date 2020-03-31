package com.company;

import java.util.ArrayList;
import java.util.List;

public class Prim {
    private final int VNUMB;
    private final int INF = Integer.MAX_VALUE;
    private int matrix[][];


    public Prim(int VNUMB) {
        this.VNUMB = VNUMB;
    }

    public Prim(int VNUMB, int[][] matrix) {
        this.VNUMB = VNUMB;
        this.matrix = matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int calculateMin() {
        return calculate(true);
    }

    public int calculateMax() {
        return calculate(false);
    }

    private int calculate(boolean isMin) {
        matrix = validateMatrix(matrix, isMin);
        List<Edge> edges = new ArrayList<>();
        List<Integer> used = new ArrayList<>();
        List<Integer> notUsed = new ArrayList<>();
        used.add(0);
        fill(notUsed, VNUMB);
        Edge edge;
        for (; ; ) {
            edge = getEdge(isMin, used, notUsed);
            if (edge != null) {
                used.add(edge.getTo());
                notUsed.remove(Integer.valueOf(edge.getTo()));
                edges.add(edge);
            } else break;
        }

        printEdges(edges);
        return count(edges);
    }

    public int[][] validateMatrix(int[][] matrix, boolean isMin) {
        if (isMin) {
            matrix = changeValue(matrix, 0, INF);
        } else matrix = changeValue(matrix, INF, 0);
        return matrix;
    }

    private int[][] changeValue(int[][] matrix, int changed, int value) {
        for (int i = 0; i < VNUMB; i++) {
            for (int j = 0; j < VNUMB; j++) {
                if (i != j && matrix[i][j] == changed)
                    matrix[i][j] = value;
            }
        }
        return matrix;
    }

    private int count(List<Edge> list) {
        int res = 0;
        for (Edge edge : list) {
            res += edge.getWeight();
        }
        return res;
    }


    private Edge getEdge(boolean isMin, List<Integer> used, List<Integer> notUsed) {
        int from = 0;
        int to = 0;
        int value;
        Edge edge;
        if (notUsed.isEmpty())
            return null;
        if (isMin) {
            value = INF;
            for (int i : used) {
                for (int j : notUsed) {
                    if (matrix[i][j] < value) {
                        from = i;
                        to = j;
                        value = matrix[i][j];
                    }
                }
            }
            edge = new Edge(from, to, value);
        } else {
            value = 0;
            for (int i : used) {
                for (int j : notUsed) {
                    if (matrix[i][j] > value) {
                        from = i;
                        to = j;
                        value = matrix[i][j];
                    }
                }
            }
            edge = new Edge(from, to, value);
        }


        return edge;
    }

    private void fill(List list, int numb) {
        for (int i = 1; i < numb; i++) {
            list.add(i);
        }
    }

    private void printEdges(List<Edge> edges) {
        for (Edge edge : edges) {
            System.out.printf("->(%d,%d)", edge.getFrom() + 1, edge.getTo() + 1);
        }
        System.out.println();
    }

}
