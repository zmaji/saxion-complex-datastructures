package nl.saxion.cds.housingassociation.graph;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.saxion.cds.housingassociation.models.home.Home;
import nl.saxion.cds.housingassociation.models.people.WorkTask;

import java.util.*;

public class DijkstraNode {

    /** Class is made with the help of https://www.baeldung.com/java-dijkstra
     * Comments and implementation made by Nils Kimenai and Maurice ten Teije */

    private Long homeID;

    @JsonIgnore
    private Home home;

    @JsonIgnore
    private WorkTask workTask;

    private List<DijkstraNode> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    @JsonIgnore
    Map<DijkstraNode, Integer> adjacentNodes = new HashMap<>();


    public DijkstraNode(Long homeID, Home home) {
        this.homeID = home.getHomeID();
        this.home = home;
    }

    public DijkstraNode(Long homeID, Home home, WorkTask workTask) {
        this.homeID = home.getHomeID();
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

    public Long getHomeID() {
        return homeID;
    }

    public void setHomeID(Long homeID) {
        this.homeID = homeID;
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
        return "Home ID: " + this.getHomeID();
    }
}