import java.util.*;
class TUF {
    public static boolean graphColoring(List < Integer > [] G, int[] color, int i, int C) {
        // Your code here
        int n = G.length;
        if (solve(i, G, color, n, C) == true) return true;
        return false;
    }
    private static boolean isSafe(int node, List < Integer > [] G, int[] color, int n, int col) {
        for (int it: G[node]) {
            if (color[it] == col) return false;
        }
        return true;
    }
    private static boolean solve(int node, List < Integer > [] G, int[] color, int n, int m) {
        if (node == n) return true;

        for (int i = 1; i <= m; i++) {
            if (isSafe(node, G, color, n, i)) {
                color[node] = i;
                if (solve(node + 1, G, color, n, m) == true) return true;
                color[node] = 0;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int N = 7, M =3;
        List < Integer > [] G = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            G[i] = new ArrayList < > ();
        }
        G[0].add(1);
        G[0].add(2);
        G[1].add(2);
        G[1].add(3);
        G[1].add(0);
        G[2].add(1);
        G[2].add(0);
        G[2].add(3);
        G[2].add(4);
        G[2].add(5);
        G[3].add(4);
        G[3].add(4);
        G[3].add(2);
        G[3].add(1);
        G[4].add(5);
        G[4].add(3);
        G[4].add(2);
        G[5].add(2);
        G[5].add(4);
        G[5].add(6);
        G[6].add(5);
        int[] color = new int[N];
        boolean ans = graphColoring(G, color, 0, M);
        if (ans) {
            System.out.println("Graph coloring is possible");
            System.out.println("Node colors:");
            for (int i = 0; i < N; i++) {
                System.out.println("Node " + i + " is colored with " + color[i]);
            }
        } else {
            System.out.println("Graph coloring is not possible with " + M + " colors.");
        }

        if (ans == true)
            System.out.println("1");
        else
            System.out.println("0");
    }
}