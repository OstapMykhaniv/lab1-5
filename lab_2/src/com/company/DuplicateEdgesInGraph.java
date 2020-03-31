package com.company;

import java.util.ArrayList;
import java.util.List;

public class DuplicateEdgesInGraph {
    public List<Edge> edgesToDuplicate(List<Edge> edges, int[][] unevenVertices) {
        List<Edge> edgesToDuplicate = new ArrayList<>();
        for (int i = 0; i < (unevenVertices.length - 1); i++) {
            if (unevenVertices[i][0] % 2 != 0) {
                for (int j = i + 1; j < unevenVertices.length; j++) {
                    if (unevenVertices[j][0] % 2 != 0) {
                        for (Edge edge : edges) {
                            if (toFindEdgeWhithVertices(unevenVertices[i][1], unevenVertices[j][1], edge) == true) {
                                edgesToDuplicate.add(edge);
                            }
                        }
                    }
                }
            }
        }
        for (Edge edge : edges) {
            if (edge == null)
                edgesToDuplicate.remove(edge);
        }
        return edgesToDuplicate;
    }

    private boolean toFindEdgeWhithVertices(int iu, int iv, Edge edge) {
        char u = (char) (iu + 65);
        char v = (char) (iv + 65);
        if ((u == edge.getU() || u == edge.getV()) && (v == edge.getU() || v == edge.getV()))
            return true;
        else
            return false;
    }

    public List<Edge> duplicateEdges(List<Edge> edgesToDuplicate, int countOfUnevenVertices) {
        List<List<Edge>> sums = new ArrayList<>();
        for (int i = 0; i < edgesToDuplicate.size(); i++) {
            List<Edge> tempr = new ArrayList<>();
            tempr.add(edgesToDuplicate.get(i));
            sums.add(tempr);
        }

        System.out.println();
        int koef = 1;
        if (countOfUnevenVertices < 4) {
            koef = 2;
        }
        for (int i = 0; i < countOfUnevenVertices / 2 - koef; i++) {
            List<List<Edge>> temp = new ArrayList<>();
            for (List<Edge> ed : sums) {
                List<Edge> edgeList = new ArrayList(ed);
                for (int j = 0; j < edgesToDuplicate.size(); j++) {
                    for (Edge edge : edgeList) {
                        if (!check(edge, edgesToDuplicate.get(j))) {
                            edgeList.add(edgesToDuplicate.get(j));
                            List<Edge> edge2 = new ArrayList<>(edgeList);
                            if (!end(temp, edge2)) {
                                temp.add(edge2);
                            }
                            edgeList.remove(edgesToDuplicate.get(j));
                        }
                    }
                }
            }
            sums.clear();
            sums = new ArrayList(temp);
        }

        int[] sumas = new int[sums.size()];
        for (int i = 0; i < sums.size(); i++) {
            sumas[i] = 0;
            for (Edge tempEd : sums.get(i)) {
                sumas[i] += tempEd.getWeigth();
                System.out.printf("(%s,%s)", tempEd.getU(), tempEd.getV());
            }
            System.out.printf("Total weigth of edges: %d", sumas[i]);
        }
        System.out.println();

        int index = 0;
        int min = sumas[0];
        for (int i = 0; i < sumas.length; i++) {
            if (sumas[i] < min) {
                min = sumas[i];
                index = i;
            }
        }

        return sums.get(index);
    }

    private boolean check(Edge x, Edge y) {
        if (x.getU() == y.getU() || x.getU() == y.getV()
                || x.getV() == y.getU() || x.getV() == y.getV()) {
            return true;
        }
        return false;
    }

    private boolean end(List<List<Edge>> x, List<Edge> y) {
        for (List<Edge> a : x) {
            int i = 0;
            if (a.size() == y.size()) {
                for (Edge b : y) {
                    if (a.contains(b)) {
                        i++;
                    }
                }
                if (i == y.size())
                    return true;
            }
        }
        return false;
    }

}

