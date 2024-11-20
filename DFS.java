class GraphDFS {
    private int vertices;
    private LinkedList<Integer>[] adjacencyList;
    private Map<Integer, String> labels;

    public GraphDFS(int vertices) {
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

    private void dfsUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(labels.get(v) + " ");

        for (int neighbor : adjacencyList[v]) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        Arrays.fill(visited, false);

        dfsUtil(start, visited);
    }
}

public class DFSExample {
    public static void main(String[] args) {
        GraphDFS graph = new GraphDFS(6);

        // Set vertex labels
        graph.setLabel(0, "u");
        graph.setLabel(1, "v");
        graph.setLabel(2, "w");
        graph.setLabel(3, "x"); 
        graph.setLabel(4, "y");
        graph.setLabel(5, "z");

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 1);
        graph.addEdge(1, 4);
        graph.addEdge(4, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 5);

        System.out.println("Depth-First Search starting from vertex u:");
        graph.dfs(0);
    }
}
