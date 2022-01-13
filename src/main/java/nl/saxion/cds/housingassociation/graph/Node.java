package nl.saxion.cds.housingassociation.graph;

import nl.saxion.cds.housingassociation.models.Home;

import java.util.*;

public class Node {

    private Long name;

    private Home home;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(Long name, Home home) {
        this.name = home.getHomeID();
        this.home = home;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Home getHome() {
        return home;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public String toString() {
        return "Home ID: " + this.getHome().getHomeID();
    }
}