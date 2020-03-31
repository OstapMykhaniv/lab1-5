package com.company;

public class FordFulkerson {
    public static int maxFlow(int[][] cap, int s, int t) {
        for (int flow = 0; ; ) {
            int df = findPath(cap, new boolean[cap.length], s, t, Integer.MAX_VALUE);
            if (df == 0) {
                System.out.println();
                System.out.print("Max sum: ");
                return flow;
            }

            flow += df;
        }
    }

    static private int findPath(int[][] cap, boolean[] vis, int u, int t, int f) {
        if (u == t) {

            System.out.println("End of path, passed: " + f);
            return f;
        }

        vis[u] = true;
        for (int v = 0; v < vis.length; v++)
            if (!vis[v] && cap[u][v] > 0) {
                int df = findPath(cap, vis, v, t, Math.min(f, cap[u][v]));
                if (df > 0) {
                    cap[u][v] -= df;
                   System.out.println(u + " -> " + v + ": " + cap[u][v]);
                    cap[v][u] += df;
                    System.out.println(u + " <- " + v + ": " + cap[v][u]);
//                    System.out.printf("(%d,%d):%d\n",u,v,cap[u][v]);
                    return df;
                }
            }
        return 0;
    }
}
