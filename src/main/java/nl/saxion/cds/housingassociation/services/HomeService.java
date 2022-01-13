package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.binarytree.BinaryTree;
import nl.saxion.cds.housingassociation.graph.Graph;
import nl.saxion.cds.housingassociation.graph.Node;
import nl.saxion.cds.housingassociation.models.Complaint;
import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.providers.ComplaintProvider;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HomeService {
    private static HashMap<String, Home> homes = HomeProvider.homes;
    private static HashMap<String, Complaint> complaints = ComplaintProvider.complaints;

    //TODO: Boom heeft homes, compareTo gebasseerd op Mainenance Costs

    public Collection<Home> getHomes() {
        return homes.values();
    }

    public HashMap<Long, Integer> getTotalMaintenanceCosts() {
        HashMap<Long, Integer> maintenanceCosts = new HashMap<>();
//        int highest = 0;
        BinaryTree tree = new BinaryTree();

        for (Complaint complaint : complaints.values()) {
            if (maintenanceCosts.containsKey(complaint.getHomeId())) {
                int cost = maintenanceCosts.get(complaint.getHomeId()) + complaint.getOtherCosts();
//                if (cost > highest) {
//                    highest = cost;
//                }
                maintenanceCosts.put(complaint.getHomeId(), cost);
            } else {
                maintenanceCosts.put(complaint.getHomeId(), complaint.getOtherCosts());
            }
        }

        BinaryTree.Node root = new BinaryTree.Node(0);
        for (Integer value : maintenanceCosts.values()) {
            tree.insert(root, value);
        }

        System.out.println("Top Maintenance Cost Tree");
        System.out.println("Building tree with root value " + root.value);
        System.out.println("Traversing tree in order");
        tree.traverseInOrder(root);
        System.out.println();
        List<Integer> topNodes = tree.largestElements(root, 10);
        System.out.println("Getting largest element " + topNodes);

        HashMap<Long, Integer> maintenanceCostsTop = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : maintenanceCosts.entrySet()) {
            boolean valid = false;
            for (Integer topNode : topNodes) {
                if (entry.getValue().equals(topNode)) {
                    valid = true;
                }
            }
            if (valid) {
                maintenanceCostsTop.put(entry.getKey(), entry.getValue());
            }

        }
        return maintenanceCostsTop;
    }

//    public Graph calculateShortestPathFromSource() {
//        Node nodeA = new Node("A");
//        Node nodeB = new Node("B");
//        Node nodeC = new Node("C");
//        Node nodeD = new Node("D");
//        Node nodeE = new Node("E");
//        Node nodeF = new Node("F");
//
//        nodeA.addDestination(nodeB, 10);
//        nodeA.addDestination(nodeC, 15);
//
//        nodeB.addDestination(nodeD, 12);
//        nodeB.addDestination(nodeF, 15);
//
//        nodeC.addDestination(nodeE, 10);
//
//        nodeD.addDestination(nodeE, 2);
//        nodeD.addDestination(nodeF, 1);
//
//        nodeF.addDestination(nodeE, 5);
//
//        Graph dijkstra = new Graph();
//
//        dijkstra.addNode(nodeA);
//        dijkstra.addNode(nodeB);
//        dijkstra.addNode(nodeC);
//        dijkstra.addNode(nodeD);
//        dijkstra.addNode(nodeE);
//        dijkstra.addNode(nodeF);
//
//        System.out.println(dijkstra.calculateShortestPathFromSource(dijkstra, nodeA));
//        return dijkstra.calculateShortestPathFromSource(dijkstra, nodeA);
//    }

    public int calculateDistance(Node start, Node destination) {
        int addressDistance = (destination.getHome().getAddressY() - start.getHome().getAddressY() + (destination.getHome().getAddressX() - start.getHome().getAddressX()));
        return addressDistance;
    }

    public Graph calculateShortestPathFromSource() {

        // Invent a List of random Homes to be used as a route for an Employee
        // See Graph documentation for details on invention
        // Commented List for neighbouring overview
        //        0; 0x, 0y (starting location) --> neighbours; 1079
        //        1079; 53x, 37y --> neighbours; 1085, 1096, 1023
        //        1085; 146x, 418y --> neighbours; 1079, 1096, 1086
        //        1086; 201x, 721y --> neighbours; 1085, 1106, 1008
        //        1106; 74x, 979y --> neighbours; 1086
        //        1008; 330x, 792y --> neighbours; 1086, 1096, 1018
        //        1096; 347x, 427y --> neighbours; 1085, 1079, 1023, 1003, 1018, 1008
        //        1023; 414x, 314y --> neighbours; 1079, 1096, 1003
        //        1018; 668x, 684y --> neighbours; 1008, 1096, 1003, 1021
        //        1003; 736x, 471y --> neighbours; 1096, 1023, 1018
        //        1021; 945x, 776y --> neighbours; 1018
        String[] homeIDs = new String[] {"1003", "1008", "1018", "1021", "1023", "1079", "1085", "1086", "1096", "1106"};
        // List is automatically sorted on order of adding (smallest to largest HomeID)
        List<Home> chosenHomes = new ArrayList<>();

        // Iterate over the random HomeID's and find matching Home object in HashMap
        // Add found Home object into chosenHomes Arraylist
        for (String homeID : homeIDs) {
            if (homes.containsKey(homeID)) {
                chosenHomes.add(homes.get(homeID));
            }
        }

        // Create a new Home as starting location with name 0 and coordinates 0,0
        //TODO: Create Location class instead of using a new Home constructor for making this starting point
        Home startingLocation = new Home(0L, 0, 0);
        Node nodeA = new Node(0L, startingLocation); // 0

        // Create a new Node for every Home with the HomeID as name and send Home object as well
        //TODO: Add Complaint object to constructor to calculate EstimatedTime, TotalTime and OtherCosts for a route
        Node nodeB = new Node(chosenHomes.get(0).getHomeID(), chosenHomes.get(0)); // 1003
        Node nodeC = new Node(chosenHomes.get(1).getHomeID(), chosenHomes.get(1)); // 1008
        Node nodeD = new Node(chosenHomes.get(2).getHomeID(), chosenHomes.get(2)); // 1018
        Node nodeE = new Node(chosenHomes.get(3).getHomeID(), chosenHomes.get(3)); // 1021
        Node nodeF = new Node(chosenHomes.get(4).getHomeID(), chosenHomes.get(4)); // 1023
        Node nodeG = new Node(chosenHomes.get(5).getHomeID(), chosenHomes.get(5)); // 1079
        Node nodeH = new Node(chosenHomes.get(6).getHomeID(), chosenHomes.get(6)); // 1085
        Node nodeI = new Node(chosenHomes.get(7).getHomeID(), chosenHomes.get(7)); // 1086
        Node nodeJ = new Node(chosenHomes.get(8).getHomeID(), chosenHomes.get(8)); // 1096
        Node nodeK = new Node(chosenHomes.get(9).getHomeID(), chosenHomes.get(9)); // 1106

        // Add neighbours for each Node and add calculated distance between Nodes
        //TODO: Invent loop for adding all these destinations

        //        0; 0x, 0y (starting location) --> neighbours; 1079
        nodeA.addDestination(nodeG, calculateDistance(nodeA, nodeG)); // 1079

        //        1079; 53x, 37y --> neighbours; 1085, 1096, 1023
        nodeG.addDestination(nodeH, calculateDistance(nodeG, nodeH)); // 1085
        nodeG.addDestination(nodeJ, calculateDistance(nodeG, nodeJ)); // 1096
        nodeG.addDestination(nodeF, calculateDistance(nodeG, nodeF)); // 1023

        //        1085; 146x, 418y --> neighbours; 1079, 1096, 1086
        nodeH.addDestination(nodeG, calculateDistance(nodeH, nodeG)); // 1079
        nodeH.addDestination(nodeJ, calculateDistance(nodeH, nodeJ)); // 1096
        nodeH.addDestination(nodeI, calculateDistance(nodeH, nodeI)); // 1086

        //        1086; 201x, 721y --> neighbours; 1085, 1106, 1008
        nodeI.addDestination(nodeH, calculateDistance(nodeI, nodeH)); // 1085
        nodeI.addDestination(nodeK, calculateDistance(nodeI, nodeK)); // 1106
        nodeI.addDestination(nodeC, calculateDistance(nodeI, nodeC)); // 1008

        //        1106; 74x, 979y --> neighbours; 1086
        nodeK.addDestination(nodeI, calculateDistance(nodeK, nodeI)); // 1086

        //        1008; 330x, 792y --> neighbours; 1086, 1096, 1018
        nodeC.addDestination(nodeI, calculateDistance(nodeC, nodeI)); // 1086
        nodeC.addDestination(nodeJ, calculateDistance(nodeC, nodeJ)); // 1096
        nodeC.addDestination(nodeD, calculateDistance(nodeC, nodeD)); // 1018

        //        1096; 347x, 427y --> neighbours; 1085, 1079, 1023, 1003, 1018, 1008
        nodeJ.addDestination(nodeH, calculateDistance(nodeJ, nodeH)); // 1085
        nodeJ.addDestination(nodeG, calculateDistance(nodeJ, nodeG)); // 1079
        nodeJ.addDestination(nodeF, calculateDistance(nodeJ, nodeF)); // 1023
        nodeJ.addDestination(nodeB, calculateDistance(nodeJ, nodeB)); // 1003
        nodeJ.addDestination(nodeD, calculateDistance(nodeJ, nodeD)); // 1018
        nodeJ.addDestination(nodeC, calculateDistance(nodeJ, nodeC)); // 1008

        //        1023; 414x, 314y --> neighbours; 1079, 1096, 1003
        nodeF.addDestination(nodeG, calculateDistance(nodeF, nodeG)); // 1079
        nodeF.addDestination(nodeJ, calculateDistance(nodeF, nodeJ)); // 1096
        nodeF.addDestination(nodeB, calculateDistance(nodeF, nodeB)); // 1003

        //        1018; 668x, 684y --> neighbours; 1008, 1096, 1003, 1021
        nodeD.addDestination(nodeC, calculateDistance(nodeD, nodeC)); // 1008
        nodeD.addDestination(nodeJ, calculateDistance(nodeD, nodeJ)); // 1096
        nodeD.addDestination(nodeB, calculateDistance(nodeD, nodeB)); // 1003
        nodeD.addDestination(nodeE, calculateDistance(nodeD, nodeE)); // 1021

        //        1003; 736x, 471y --> neighbours; 1096, 1023, 1018
        nodeB.addDestination(nodeJ, calculateDistance(nodeB, nodeJ)); // 1096
        nodeB.addDestination(nodeF, calculateDistance(nodeB, nodeF)); // 1023
        nodeB.addDestination(nodeD, calculateDistance(nodeB, nodeD)); // 1018

        //        1021; 945x, 776y --> neighbours; 1018
        nodeE.addDestination(nodeD, calculateDistance(nodeE, nodeD)); // 1018

        // Create a new Dijkstra Graph and add all newly created Nodes to the Graph
        Graph homesGraph = new Graph();

        homesGraph.addNode(nodeA);
        homesGraph.addNode(nodeB);
        homesGraph.addNode(nodeC);
        homesGraph.addNode(nodeD);
        homesGraph.addNode(nodeE);
        homesGraph.addNode(nodeF);
        homesGraph.addNode(nodeG);
        homesGraph.addNode(nodeH);
        homesGraph.addNode(nodeI);
        homesGraph.addNode(nodeJ);
        homesGraph.addNode(nodeK);

        // Calculate shortest path from homesGraph with nodeA (starting location) as start
        System.out.println(homesGraph.calculateShortestPathFromSource(homesGraph, nodeA));
        return homesGraph.calculateShortestPathFromSource(homesGraph, nodeA);
    }
}
