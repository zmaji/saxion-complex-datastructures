package nl.saxion.cds.housingassociation.graph;

import nl.saxion.cds.housingassociation.models.complaint.Complaint;
import nl.saxion.cds.housingassociation.models.home.Home;
import nl.saxion.cds.housingassociation.models.people.WorkTask;

import java.util.*;

public class DijkstraNode {

    /** Class is made with the help of https://www.baeldung.com/java-dijkstra
     * Comments and implementation made by Nils Kimenai and Maurice ten Teije */

    private Long name;
    private Home home;
    private WorkTask workTask;

    private List<DijkstraNode> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<DijkstraNode, Integer> adjacentNodes = new HashMap<>();

    public DijkstraNode(Long name, Home home) {
        this.name = home.getHomeID();
        this.home = home;
    }

    public DijkstraNode(Long name, Home home, WorkTask workTask) {
        this.name = home.getHomeID();
        this.home = home;
        this.workTask = workTask;
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

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public WorkTask getWorkTask() {
        return workTask;
    }

    public void setWorkTask(WorkTask workTask) {
        this.workTask = workTask;
    }

    public void setAdjacentNodes(Map<DijkstraNode, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Map<DijkstraNode, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public String toString() {
        return "Home ID: " + this.getHome().getHomeID();
    }
}