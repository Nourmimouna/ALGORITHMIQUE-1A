import java.util.*;

public class Prim {
    public Integer[] spanningTree(WeightedGraph graph, int v0) {
        int numVertices = graph.getVertexSize();
        Integer[] parents = new Integer[numVertices];
        boolean[] visited = new boolean[numVertices];
        int[] weights = new int[numVertices];
        Arrays.fill(parents, null);
        Arrays.fill(visited, false);
        Arrays.fill(weights, Integer.MAX_VALUE);

        weights[v0] = 0;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        priorityQueue.add(new Edge(v0, v0, 0, graph));

        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.poll();
            int u = minEdge.u;
            int v = minEdge.v;
            int weight = minEdge.weight;

            if (visited[v])
                continue;

            parents[v] = u;
            visited[v] = true;

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                Integer neighborWeight = graph.getEdgeWeight(v, neighbor);
                if (neighborWeight != null && !visited[neighbor] && neighborWeight < weights[neighbor]) {
                    weights[neighbor] = neighborWeight;
                    priorityQueue.add(new Edge(v, neighbor, neighborWeight, graph));
                }
            }
        }

        return parents;
    }

    public void testSpanningTree() {
        var testBench = new Object() {
            int totalTests = 0, testSuccesses = 0;

            void printTestResult(boolean r) {
                totalTests++;
                System.out.printf("Test %d: %s\n", totalTests, r ? "PASSED" : "FAILED");
                if (r)
                    testSuccesses++;
            }

            void printSummary() {
                System.out.printf("Final result: %d/%d\n", testSuccesses, totalTests);
            }

        };

        var g1 = new WeightedGraph(5);
        g1.addEdge(0, 1, 2);
        g1.addEdge(0, 2, 2);
        g1.addEdge(0, 4, 5);
        g1.addEdge(1, 3, 3);
        g1.addEdge(3, 4, 1);

        testBench.printTestResult(Arrays.deepEquals(spanningTree(g1, 1), new Integer[] { 1, null, 0, 1, 3 }));
        testBench.printTestResult(Arrays.deepEquals(spanningTree(g1, 4), new Integer[] { 1, 3, 0, 4, null }));

        testBench.printSummary();
    }

    public static void main(String[] args) {
        Prim prim = new Prim();
        prim.testSpanningTree();
    }
}

class Edge {
    int u;
    int v;
    int weight;
    WeightedGraph graph;

    public Edge(int u, int v, int weight, WeightedGraph graph) {
        this.u = u;
        this.v = v;
        this.weight = weight;
        this.graph = graph;
    }
}
