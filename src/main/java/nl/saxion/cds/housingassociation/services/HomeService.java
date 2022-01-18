package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.binarytree.BinaryTree;
import nl.saxion.cds.housingassociation.graph.BreadthFirstNode;
import nl.saxion.cds.housingassociation.graph.DijkstraNode;
import nl.saxion.cds.housingassociation.graph.Graph;
import nl.saxion.cds.housingassociation.models.complaint.Complaint;
import nl.saxion.cds.housingassociation.models.home.Home;
import nl.saxion.cds.housingassociation.models.home.TopMaintenanceHome;
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

    public List<TopMaintenanceHome> getTotalMaintenanceCosts() {
        HashMap<Long, Integer> maintenanceCosts = new HashMap<>();
        BinaryTree tree = new BinaryTree();

        // Iterate over the values of the Complaints HashMap
        for (Complaint complaint : complaints.values()) {
            // If the HashMap maintenanceCosts already contains the Complaint
            if (maintenanceCosts.containsKey(complaint.getHomeId())) {

                int cost = maintenanceCosts.get(complaint.getHomeId()) + complaint.getOtherCosts();
                // Add the Complaint and Costs to the maintenanceCosts HashMap
                maintenanceCosts.put(complaint.getHomeId(), cost);
            } else {
                // Add the Complaint and Costs to the maintenanceCosts HashMap
                maintenanceCosts.put(complaint.getHomeId(), complaint.getOtherCosts());
            }
        }

        // Create a new Node as Root Node with value 0
        BinaryTree.Node root = new BinaryTree.Node(0);
        // Add all values of the maintenanceCosts to the tree as Nodes
        for (Integer value : maintenanceCosts.values()) {
            tree.insert(root, value);
        }

        // Print the Traversing in order from the Root Node of the Tree
        System.out.println("Top Maintenance Cost Tree");
        System.out.println("Building tree with root value " + root.value);
        System.out.println("Traversing tree in order");
        tree.traverseInOrder(root);
        System.out.println();
        // Initialise a List with the top 10 largest elements in the Binary Tree
        List<Integer> topNodes = tree.largestElements(root, 10);
        System.out.println("Getting largest element " + topNodes);

        // Create a HashMap to store the HomeID together with the value
        HashMap<Long, Integer> maintenanceCostsTop = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : maintenanceCosts.entrySet()) {
            boolean valid = false;
            for (Integer topNode : topNodes) {
                if (entry.getValue().equals(topNode)) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                maintenanceCostsTop.put(entry.getKey(), entry.getValue());
            }

        }

        List<TopMaintenanceHome> finalTopMaintenanceList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : maintenanceCostsTop.entrySet()) {
            finalTopMaintenanceList.add(new TopMaintenanceHome(entry.getKey(), entry.getValue()));
        }

        Collections.sort(finalTopMaintenanceList);

        return finalTopMaintenanceList;
    }

    public int calculateDistance(DijkstraNode start, DijkstraNode destination) {
        return (destination.getHome().getAddressY() - start.getHome().getAddressY() + (destination.getHome().getAddressX() - start.getHome().getAddressX()));
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

        // PRE: chosenHomes.size == 0
        assert chosenHomes.size() != 0;
        // POST: chosenHomes.size == 10

        // Create a new Home as starting location with name 0 and coordinates 0,0
        Home startingLocation = new Home(0L, 0, 0);
        DijkstraNode dijkstraNodeA = new DijkstraNode(0L, startingLocation); // 0

        // Create a new DijkstraNode for every Home with the HomeID as name and send Home object as well
        //TODO: Add Complaint object to constructor to calculate EstimatedTime, TotalTime and OtherCosts for a route
        DijkstraNode dijkstraNodeB = new DijkstraNode(chosenHomes.get(0).getHomeID(), chosenHomes.get(0)); // 1003

        // PRE: dijkstraNodeB == null
        assert dijkstraNodeB.getHome().getHomeID() == 1003;
        // POST: dijkstraNodeB == DijkstraNode(chosenHomes.get(0).getHomeID(), chosenHomes.get(0))

        DijkstraNode dijkstraNodeC = new DijkstraNode(chosenHomes.get(1).getHomeID(), chosenHomes.get(1)); // 1008
        DijkstraNode dijkstraNodeD = new DijkstraNode(chosenHomes.get(2).getHomeID(), chosenHomes.get(2)); // 1018
        DijkstraNode dijkstraNodeE = new DijkstraNode(chosenHomes.get(3).getHomeID(), chosenHomes.get(3)); // 1021
        DijkstraNode dijkstraNodeF = new DijkstraNode(chosenHomes.get(4).getHomeID(), chosenHomes.get(4)); // 1023
        DijkstraNode dijkstraNodeG = new DijkstraNode(chosenHomes.get(5).getHomeID(), chosenHomes.get(5)); // 1079
        DijkstraNode dijkstraNodeH = new DijkstraNode(chosenHomes.get(6).getHomeID(), chosenHomes.get(6)); // 1085
        DijkstraNode dijkstraNodeI = new DijkstraNode(chosenHomes.get(7).getHomeID(), chosenHomes.get(7)); // 1086
        DijkstraNode dijkstraNodeJ = new DijkstraNode(chosenHomes.get(8).getHomeID(), chosenHomes.get(8)); // 1096
        DijkstraNode dijkstraNodeK = new DijkstraNode(chosenHomes.get(9).getHomeID(), chosenHomes.get(9)); // 1106

        // Add neighbours for each DijkstraNode and add calculated distance between Nodes
        //TODO: Invent loop for adding all these destinations

        //        0; 0x, 0y (starting location) --> neighbours; 1079
        dijkstraNodeA.addDestination(dijkstraNodeG, calculateDistance(dijkstraNodeA, dijkstraNodeG)); // 1079

        //        1079; 53x, 37y --> neighbours; 1085, 1096, 1023
        dijkstraNodeG.addDestination(dijkstraNodeH, calculateDistance(dijkstraNodeG, dijkstraNodeH)); // 1085
        dijkstraNodeG.addDestination(dijkstraNodeJ, calculateDistance(dijkstraNodeG, dijkstraNodeJ)); // 1096
        dijkstraNodeG.addDestination(dijkstraNodeF, calculateDistance(dijkstraNodeG, dijkstraNodeF)); // 1023

        //        1085; 146x, 418y --> neighbours; 1079, 1096, 1086
        dijkstraNodeH.addDestination(dijkstraNodeG, calculateDistance(dijkstraNodeH, dijkstraNodeG)); // 1079
        dijkstraNodeH.addDestination(dijkstraNodeJ, calculateDistance(dijkstraNodeH, dijkstraNodeJ)); // 1096
        dijkstraNodeH.addDestination(dijkstraNodeI, calculateDistance(dijkstraNodeH, dijkstraNodeI)); // 1086

        //        1086; 201x, 721y --> neighbours; 1085, 1106, 1008
        dijkstraNodeI.addDestination(dijkstraNodeH, calculateDistance(dijkstraNodeI, dijkstraNodeH)); // 1085
        dijkstraNodeI.addDestination(dijkstraNodeK, calculateDistance(dijkstraNodeI, dijkstraNodeK)); // 1106
        dijkstraNodeI.addDestination(dijkstraNodeC, calculateDistance(dijkstraNodeI, dijkstraNodeC)); // 1008

        //        1106; 74x, 979y --> neighbours; 1086
        dijkstraNodeK.addDestination(dijkstraNodeI, calculateDistance(dijkstraNodeK, dijkstraNodeI)); // 1086

        //        1008; 330x, 792y --> neighbours; 1086, 1096, 1018
        dijkstraNodeC.addDestination(dijkstraNodeI, calculateDistance(dijkstraNodeC, dijkstraNodeI)); // 1086
        dijkstraNodeC.addDestination(dijkstraNodeJ, calculateDistance(dijkstraNodeC, dijkstraNodeJ)); // 1096
        dijkstraNodeC.addDestination(dijkstraNodeD, calculateDistance(dijkstraNodeC, dijkstraNodeD)); // 1018

        //        1096; 347x, 427y --> neighbours; 1085, 1079, 1023, 1003, 1018, 1008
        dijkstraNodeJ.addDestination(dijkstraNodeH, calculateDistance(dijkstraNodeJ, dijkstraNodeH)); // 1085
        dijkstraNodeJ.addDestination(dijkstraNodeG, calculateDistance(dijkstraNodeJ, dijkstraNodeG)); // 1079
        dijkstraNodeJ.addDestination(dijkstraNodeF, calculateDistance(dijkstraNodeJ, dijkstraNodeF)); // 1023
        dijkstraNodeJ.addDestination(dijkstraNodeB, calculateDistance(dijkstraNodeJ, dijkstraNodeB)); // 1003
        dijkstraNodeJ.addDestination(dijkstraNodeD, calculateDistance(dijkstraNodeJ, dijkstraNodeD)); // 1018
        dijkstraNodeJ.addDestination(dijkstraNodeC, calculateDistance(dijkstraNodeJ, dijkstraNodeC)); // 1008

        //        1023; 414x, 314y --> neighbours; 1079, 1096, 1003
        dijkstraNodeF.addDestination(dijkstraNodeG, calculateDistance(dijkstraNodeF, dijkstraNodeG)); // 1079
        dijkstraNodeF.addDestination(dijkstraNodeJ, calculateDistance(dijkstraNodeF, dijkstraNodeJ)); // 1096
        dijkstraNodeF.addDestination(dijkstraNodeB, calculateDistance(dijkstraNodeF, dijkstraNodeB)); // 1003

        //        1018; 668x, 684y --> neighbours; 1008, 1096, 1003, 1021
        dijkstraNodeD.addDestination(dijkstraNodeC, calculateDistance(dijkstraNodeD, dijkstraNodeC)); // 1008
        dijkstraNodeD.addDestination(dijkstraNodeJ, calculateDistance(dijkstraNodeD, dijkstraNodeJ)); // 1096
        dijkstraNodeD.addDestination(dijkstraNodeB, calculateDistance(dijkstraNodeD, dijkstraNodeB)); // 1003
        dijkstraNodeD.addDestination(dijkstraNodeE, calculateDistance(dijkstraNodeD, dijkstraNodeE)); // 1021

        //        1003; 736x, 471y --> neighbours; 1096, 1023, 1018
        dijkstraNodeB.addDestination(dijkstraNodeJ, calculateDistance(dijkstraNodeB, dijkstraNodeJ)); // 1096
        dijkstraNodeB.addDestination(dijkstraNodeF, calculateDistance(dijkstraNodeB, dijkstraNodeF)); // 1023
        dijkstraNodeB.addDestination(dijkstraNodeD, calculateDistance(dijkstraNodeB, dijkstraNodeD)); // 1018

        //        1021; 945x, 776y --> neighbours; 1018
        dijkstraNodeE.addDestination(dijkstraNodeD, calculateDistance(dijkstraNodeE, dijkstraNodeD)); // 1018

        // Create a new Dijkstra Graph and add all newly created Nodes to the Graph
        Graph homesGraph = new Graph();

        homesGraph.addNode(dijkstraNodeA);
        homesGraph.addNode(dijkstraNodeB);
        homesGraph.addNode(dijkstraNodeC);
        homesGraph.addNode(dijkstraNodeD);
        homesGraph.addNode(dijkstraNodeE);
        homesGraph.addNode(dijkstraNodeF);
        homesGraph.addNode(dijkstraNodeG);
        homesGraph.addNode(dijkstraNodeH);
        homesGraph.addNode(dijkstraNodeI);
        homesGraph.addNode(dijkstraNodeJ);
        homesGraph.addNode(dijkstraNodeK);

        // Calculate shortest path from homesGraph with dijkstraNodeA (starting location) as start
        System.out.println(Graph.calculateShortestPathFromSource(homesGraph, dijkstraNodeA));
        return Graph.calculateShortestPathFromSource(homesGraph, dijkstraNodeA);
    }

    public Optional<BreadthFirstNode<Integer>> BreadthFirstSearch() {
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

        // Create a Node for the start (housing association location)
        BreadthFirstNode<Integer> start = new BreadthFirstNode<>(0);
        // Create a Node for each Home, with the HomeID as value
        BreadthFirstNode<Integer> B = new BreadthFirstNode<>(1079);
        BreadthFirstNode<Integer> C = new BreadthFirstNode<>(1085);
        BreadthFirstNode<Integer> D = new BreadthFirstNode<>(1086);
        BreadthFirstNode<Integer> E = new BreadthFirstNode<>(1106);
        BreadthFirstNode<Integer> F = new BreadthFirstNode<>(1008);
        BreadthFirstNode<Integer> G = new BreadthFirstNode<>(1096);
        BreadthFirstNode<Integer> H = new BreadthFirstNode<>(1023);
        BreadthFirstNode<Integer> I = new BreadthFirstNode<>(1003);
        BreadthFirstNode<Integer> J = new BreadthFirstNode<>(1018);
        BreadthFirstNode<Integer> K = new BreadthFirstNode<>(1021);

        // Add Neighbours to each Node
        start.addNeighbour(B); // start --> 1079

        B.addNeighbour(start); // 1079 --> start
        B.addNeighbour(C); // 1079 --> 1085
        B.addNeighbour(G); // 1079 --> 1096
        B.addNeighbour(H); // 1079 --> 1023

        C.addNeighbour(B); // 1085 --> 1079
        C.addNeighbour(G); // 1085 --> 1096
        C.addNeighbour(D); // 1085 --> 1086

        D.addNeighbour(C); // 1086 --> 1085
        D.addNeighbour(E); // 1086 --> 1106
        D.addNeighbour(F); // 1086 --> 1008

        E.addNeighbour(D); // 1106 --> 1086

        F.addNeighbour(D); // 1008 --> 1086
        F.addNeighbour(G); // 1008 --> 1096
        F.addNeighbour(J); // 1008 --> 1018

        G.addNeighbour(B); // 1096 --> 1079
        G.addNeighbour(C); // 1096 --> 1085
        G.addNeighbour(F); // 1096 --> 1008
        G.addNeighbour(H); // 1096 --> 1023
        G.addNeighbour(I); // 1096 --> 1003
        G.addNeighbour(J); // 1096 --> 1018

        H.addNeighbour(B); // 1023 --> 1079
        H.addNeighbour(G); // 1023 --> 1096
        H.addNeighbour(I); // 1023 --> 1003

        I.addNeighbour(G); // 1003 --> 1096
        I.addNeighbour(H); // 1003 --> 1023
        I.addNeighbour(J); // 1003 --> 1018

        J.addNeighbour(F); // 1018 --> 1008
        J.addNeighbour(G); // 1018 --> 1096
        J.addNeighbour(I); // 1018 --> 1003
        J.addNeighbour(K); // 1018 --> 1021

        K.addNeighbour(J); // 1021 --> 1018

        BreadthFirstNode.search(1021, B);
        return Optional.empty();
    }
}
