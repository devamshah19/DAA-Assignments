package Assignment14;

import java.util.*;

public class Dijkstra {
    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && !visited[v]) {
                    int newDist = distances[u] + graph[u][v];
                    if (newDist < distances[v]) {
                        distances[v] = newDist;
                        pq.offer(new int[]{v, newDist});
                    }
                }
            }
        }

        System.out.println("Shortest distances from node " + source + ": " + Arrays.toString(distances));
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, Integer.MAX_VALUE, 30, 100},
                {Integer.MAX_VALUE, 0, 50, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 10},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 20, 0, 60},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        dijkstra(graph, 0);
    }
}
