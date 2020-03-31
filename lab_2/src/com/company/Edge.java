package com.company;

import java.util.Objects;

public class Edge {
    private char u;
    private char v;
    private int weigth;

    public Edge(char u, char v, int weigth) {
        this.u = u;
        this.v = v;
        this.weigth = weigth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return u == edge.u &&
                v == edge.v &&
                weigth == edge.weigth;
    }

    @Override
    public int hashCode() {

        return Objects.hash(u, v, weigth);
    }

    public int getWeigth() {
        return weigth;
    }
    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public char getU() {
        return u;
    }

    public void setU(char u) {
        this.u = u;
    }

    public char getV() {
        return v;
    }

    public void setV(char v) {
        this.v = v;
    }
}
