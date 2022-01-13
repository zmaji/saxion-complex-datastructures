package nl.saxion.cds.housingassociation.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

    /** Class is made with the help of https://www.baeldung.com/java-dijkstra
     * Comments and implementation made by Nils Kimenai and Maurice ten Teije */

    private Set<DijkstraNode> dijkstraNodes = new HashSet<>();

    public void addNode(DijkstraNode dijkstraNodeA) {
        dijkstraNodes.add(dijkstraNodeA);
    }

    public static Graph calculateShortestPathFromSource(Graph graph, DijkstraNode source) {
        source.setDistance(0);

        Set<DijkstraNode> settledDijkstraNodes = new HashSet<>();
        Set<DijkstraNode> unsettledDijkstraNodes = new HashSet<>();

        unsettledDijkstraNodes.add(source);

        while (unsettledDijkstraNodes.size() != 0) {
            DijkstraNode currentDijkstraNode = getLowestDistanceNode(unsettledDijkstraNodes);
            unsettledDijkstraNodes.remove(currentDijkstraNode);
            for (Map.Entry<DijkstraNode, Integer> adjacencyPair:
                    currentDijkstraNode.getAdjacentNodes().entrySet()) {
                DijkstraNode adjacentDijkstraNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledDijkstraNodes.contains(adjacentDijkstraNode)) {
                    calculateMinimumDistance(adjacentDijkstraNode, edgeWeight, currentDijkstraNode);
                    unsettledDijkstraNodes.add(adjacentDijkstraNode);
                }
            }
            settledDijkstraNodes.add(currentDijkstraNode);
        }
        return graph;
    }

    private static DijkstraNode getLowestDistanceNode(Set<DijkstraNode> unsettledDijkstraNodes) {
        DijkstraNode lowestDistanceDijkstraNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (DijkstraNode dijkstraNode : unsettledDijkstraNodes) {
            int nodeDistance = dijkstraNode.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceDijkstraNode = dijkstraNode;
            }
        }
        return lowestDistanceDijkstraNode;
    }

    private static void calculateMinimumDistance(DijkstraNode evaluationDijkstraNode,
                                                 Integer edgeWeigh, DijkstraNode sourceDijkstraNode) {
        Integer sourceDistance = sourceDijkstraNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationDijkstraNode.getDistance()) {
            evaluationDijkstraNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<DijkstraNode> shortestPath = new LinkedList<>(sourceDijkstraNode.getShortestPath());
            shortestPath.add(sourceDijkstraNode);
            evaluationDijkstraNode.setShortestPath(shortestPath);
        }
    }

    public Set<DijkstraNode> getNodes() {
        return dijkstraNodes;
    }

    public void setNodes(Set<DijkstraNode> dijkstraNodes) {
        this.dijkstraNodes = dijkstraNodes;
    }
}
