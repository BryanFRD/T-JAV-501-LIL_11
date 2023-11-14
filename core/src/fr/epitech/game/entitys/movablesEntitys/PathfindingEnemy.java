package fr.epitech.game.entitys.movablesEntitys;

import java.util.*;

public class PathfindingEnemy {
    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    private int vertices;
    private List<List<Node>> adjacencyList;

    public PathfindingEnemy(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            new ArrayList<>().add(this.adjacencyList);
        }
    }

    public void addEdge(int source, int destination, int weight) {
        this.adjacencyList.get(source).add(new Node(destination, weight));
        this.adjacencyList.get(destination).add(new Node(source, weight)); // for undirected graph
    }

    /**
     * Runs Dijkstra's algorithm to find the shortest distances from the source vertex.
     *
     * @param source The source vertex.
     * @return An array containing the shortest distances from the source vertex to each vertex in the graph.
     */
    public int[] dijkstra(int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.add(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();

            for (Node neighbor : adjacencyList.get(currentNode.vertex)) {
                int newDistance = distances[currentNode.vertex] + neighbor.distance;

                if (newDistance < distances[neighbor.vertex]) {
                    distances[neighbor.vertex] = newDistance;
                    minHeap.add(new Node(neighbor.vertex, newDistance));
                }
            }
        }

        return distances;
    }
}

