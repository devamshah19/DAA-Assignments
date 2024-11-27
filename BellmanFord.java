package Assignment14;

import java.util.Arrays;

public class BellmanFord {
    public static void bellmanFord(int[][] graph, int source) {
        int n = graph.length;
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 1; i < n; i++) {
            for (int u = 0; u < n; u++) {
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] != Integer.MAX_VALUE && distances[u] != Integer.MAX_VALUE &&
                            distances[u] + graph[u][v] < distances[v]) {
                        distances[v] = distances[u] + graph[u][v];
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && distances[u] != Integer.MAX_VALUE &&
                        distances[u] + graph[u][v] < distances[v]) {
                    System.out.println("Graph contains a negative-weight cycle");
                    return;
                }
            }
        }

        System.out.println("Shortest distances from node " + source + ": " + Arrays.toString(distances));
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 6, Integer.MAX_VALUE, Integer.MAX_VALUE, 7},
                {Integer.MAX_VALUE, 0, 5, -4, 8},
                {Integer.MAX_VALUE, -2, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {2, Integer.MAX_VALUE, 7, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, -3, 9, 0}
        };
        bellmanFord(graph, 0);
    }
}