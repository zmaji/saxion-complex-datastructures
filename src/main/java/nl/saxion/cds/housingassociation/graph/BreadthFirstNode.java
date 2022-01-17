package nl.saxion.cds.housingassociation.graph;

import java.util.*;

/** Class is made with the help of https://www.baeldung.com/java-breadth-first-search
 * Comments and implementation made by Nils Kimenai and Maurice ten Teije */

// Creates class that could be used with a generic type
public class BreadthFirstNode<T> {
    private T value;
    private Set<BreadthFirstNode<T>> neighbours;

    // Create a constructor generic value for a Node and a HashSet for Neighbours
    public BreadthFirstNode(T value) {
        this.value = value;
        this.neighbours = new HashSet<>();
    }

    // Adds a Neighbour to a Node
    public void addNeighbour(BreadthFirstNode<T> node) {
        // If this node is the same as the given Node
        if (this == node) {
            // Throw error because you can't connect a Node to itself
            throw new IllegalArgumentException("Can't connect Node to itself");
        } else {
            // Add this and given Node to each others neighbouring Set
            this.neighbours.add(node);

            // PRE: !this.neighbours.contains(node)
            assert this.neighbours.contains(node) : "Node has not been added as Neighbour";
            // PRE: this.neighbours.contains(node)

            node.neighbours.add(this);
        }
    }

    public static <T> Optional<BreadthFirstNode<T>> search(T value, BreadthFirstNode<T> start) {
        // Implement Queue for the Breadth-First algorithm to traverse the Nodes
        Queue<BreadthFirstNode<T>> queue = new ArrayDeque<>();
        // Implement Set for already visited Nodes to avoid cycles
        Set<BreadthFirstNode<T>> visitedNodes = new HashSet<>();
        // Add given Start Node to the Queue
        queue.add(start);

        // Initialise currentNode variable
        BreadthFirstNode<T> currentNode;

        // Loop through the Queue while it is not empty
        while (!queue.isEmpty()) {
            // Each time remove(pop) a Node from the Queue
            currentNode = queue.remove();

            // PRE: queue.contains(currentNode)
            assert queue.contains(currentNode) : "Current Node has not been deleted!";
            // POST: !queue.contains(currentNode)

            // Print Nodes we've already visited
            System.out.println("Visited node with value: " + currentNode.getValue());

            // If this is the Node we're looking for, return it
            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            } else {
                // Add Node to the visited Nodes
                visitedNodes.add(currentNode);
                // Add its Neighbours to the Queue
                queue.addAll(currentNode.getNeighbours());
                // Remove the visited Nodes from the Queue
                queue.removeAll(visitedNodes);
            }
        }
        // If all Nodes are visited without the one we're searching for, return an empty result
        return Optional.empty();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Set<BreadthFirstNode<T>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<BreadthFirstNode<T>> neighbours) {
        this.neighbours = neighbours;
    }
}
