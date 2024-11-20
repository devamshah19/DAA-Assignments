import java.util.*;

class Graph {
    private int vertices;
    private LinkedList<Integer>[] adjacencyList;
    private Map<Integer, String> labels;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        labels = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
    }

    public void setLabel(int vertex, String label) {
        labels.put(vertex, label);
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int neighbor : adjacencyList[v]) {
            if (!visited[neighbor]) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(v);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];
        Arrays.fill(visited, false);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        System.out.println("Topological Sort:");
        while (!stack.isEmpty()) {
            System.out.print(labels.get(stack.pop()) + " ");
        }
    }
}

public class TopologicalSortExample {
    public static void main(String[] args) {
        Graph graph = new Graph(8);

        graph.setLabel(0, "m");
        graph.setLabel(1, "n");
        graph.setLabel(2, "o");
        graph.setLabel(3, "p");
        graph.setLabel(4, "q");
        graph.setLabel(5, "r");
        graph.setLabel(6, "s");
        graph.setLabel(7, "t");

        graph.addEdge(0, 4);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(1, 4);
        graph.addEdge(1, 7);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(2, 7);
        graph.addEdge(2, 6);
        graph.addEdge(3, 2);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 7);
        graph.addEdge(5, 7);
        graph.addEdge(5, 6);

        graph.topologicalSort();
    }
}
