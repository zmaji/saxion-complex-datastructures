package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.BinaryTree;
import nl.saxion.cds.housingassociation.dijkstra.Graph;
import nl.saxion.cds.housingassociation.dijkstra.Node;
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

    public Graph calculateShortestPathFromSource() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph dijkstra = new Graph();

        dijkstra.addNode(nodeA);
        dijkstra.addNode(nodeB);
        dijkstra.addNode(nodeC);
        dijkstra.addNode(nodeD);
        dijkstra.addNode(nodeE);
        dijkstra.addNode(nodeF);

//        System.out.println(dijkstra.calculateShortestPathFromSource(dijkstra, nodeA));
        return dijkstra.calculateShortestPathFromSource(dijkstra, nodeA);
    }
}
