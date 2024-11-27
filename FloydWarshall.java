package Assignment14;

import java.util.Arrays;

public class FloydWarshall {
    public static void floydWarshall(int[][] graph) {
        int n = graph.length;
        int[][] dist = new int[n][n];

        // Initialize the distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Compute shortest paths
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Print the shortest path matrix
        System.out.println("Shortest path matrix:");
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 3, 8, Integer.MAX_VALUE, -4},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 1, 7},
                {Integer.MAX_VALUE, 4, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {2, Integer.MAX_VALUE, -5, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0}
        };
        floydWarshall(graph);
    }
}