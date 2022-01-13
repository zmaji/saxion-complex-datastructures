package nl.saxion.cds.housingassociation.graph;

import nl.saxion.cds.housingassociation.models.Home;

import java.util.*;

public class DijkstraNode {

    private Long name;

    private Home home;

    private List<DijkstraNode> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<DijkstraNode, Integer> adjacentNodes = new HashMap<>();

    public DijkstraNode(Long name, Home home) {
        this.name = home.getHomeID();
        this.home = home;
    }

    public void addDestination(DijkstraNode destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Home getHome() {
        return home;
    }

    public List<DijkstraNode> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<DijkstraNode> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<DijkstraNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public String toString() {
        return "Home ID: " + this.getHome().getHomeID();
    }
}