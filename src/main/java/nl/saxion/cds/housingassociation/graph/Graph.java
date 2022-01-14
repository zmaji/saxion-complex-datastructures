package nl.saxion.cds.housingassociation.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

    /** Class is made with the help of https://www.baeldung.com/java-dijkstra
     * Comments and implementation made by Nils Kimenai and Maurice ten Teije */

    private Set<DijkstraNode> dijkstraNodes = new HashSet<>();

    // Add a Node to the HashSet
    public void addNode(DijkstraNode dijkstraNodeA) {
        dijkstraNodes.add(dijkstraNodeA);
    }

    // Calculate the shortest path from a chosen source to each Node of the given Graph
    public static Graph calculateShortestPathFromSource(Graph graph, DijkstraNode source) {
        // Set the distance of the starting Node to zero
        source.setDistance(0);

        // Initialise a HashSet for settled Nodes
        Set<DijkstraNode> settledDijkstraNodes = new HashSet<>();
        // Initialise a HashSet for unsettled Nodes
        Set<DijkstraNode> unsettledDijkstraNodes = new HashSet<>();

        // Add the starting Node to the unsettled Nodes HashSet
        unsettledDijkstraNodes.add(source);

        // While HashSet for unsettled Nodes is not empty
        while (unsettledDijkstraNodes.size() != 0) {
            // Initialise a current Node with the lowest distance Node
            DijkstraNode currentDijkstraNode = getLowestDistanceNode(unsettledDijkstraNodes);
            // Remove the current Node from the unsettled HashSet
            unsettledDijkstraNodes.remove(currentDijkstraNode);
            // Iterate over the Neighbours of the current Node
            for (Map.Entry<DijkstraNode, Integer> adjacencyPair:
                    currentDijkstraNode.getAdjacentNodes().entrySet()) {
                // Initialise a Neighbouring Node from the key
                DijkstraNode adjacentDijkstraNode = adjacencyPair.getKey();
                // Initialise a variable with the distance of the value
                Integer edgeWeight = adjacencyPair.getValue();
                // If the settled Node HashSet contains the Neighbour Node
                if (!settledDijkstraNodes.contains(adjacentDijkstraNode)) {
                    calculateMinimumDistance(adjacentDijkstraNode, edgeWeight, currentDijkstraNode);
                    unsettledDijkstraNodes.add(adjacentDijkstraNode);
                }
            }
            settledDijkstraNodes.add(currentDijkstraNode);
        }
        return graph;
    }

    // Get Node with the lowest distance value and return it
    private static DijkstraNode getLowestDistanceNode(Set<DijkstraNode> unsettledDijkstraNodes) {
        // Initialise lowest distance Node
        DijkstraNode lowestDistanceDijkstraNode = null;
        // Initialise lowest distance variable
        int lowestDistance = Integer.MAX_VALUE;
        // Iterate over the unsettled Nodes HashSet
        for (DijkstraNode dijkstraNode : unsettledDijkstraNodes) {
            int nodeDistance = dijkstraNode.getDistance();
            // For each Node, if the distance is lower than the lowest distance, it will become the lowest distance Node
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceDijkstraNode = dijkstraNode;
            }
        }
        // Return the lowest distance Node
        return lowestDistanceDijkstraNode;
    }

    // Calculates the minimum distance from a Node with a certain distance to the source Node
    private static void calculateMinimumDistance(DijkstraNode evaluationDijkstraNode, Integer edgeWeigh, DijkstraNode sourceDijkstraNode) {
        // Initialise a variable with the distance of the source Node
        Integer sourceDistance = sourceDijkstraNode.getDistance();
        // If the source distance + given distance is lower than the Neighbour distance
        if (sourceDistance + edgeWeigh < evaluationDijkstraNode.getDistance()) {
            // Set the distance of the Neighbour Node to the current Node distance + the given distance
            evaluationDijkstraNode.setDistance(sourceDistance + edgeWeigh);
            // Initialise a new LinkedList with the shortest path to the current Node
            LinkedList<DijkstraNode> shortestPath = new LinkedList<>(sourceDijkstraNode.getShortestPath());
            // Add the current Node to the shortest path
            shortestPath.add(sourceDijkstraNode);
            // Set above LinkedList as the Neighbour Node's shortest path
            evaluationDijkstraNode.setShortestPath(shortestPath);
        }
    }
}
