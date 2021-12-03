package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.BinaryTree;
import nl.saxion.cds.housingassociation.models.Complaint;
import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.providers.ComplaintProvider;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

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
        int highest = 0;
        BinaryTree tree = new BinaryTree();

        for (Complaint complaint : complaints.values()) {
            if (maintenanceCosts.containsKey(complaint.getHomeId())) {
                int cost = maintenanceCosts.get(complaint.getHomeId()) + complaint.getOtherCosts();
                if (cost > highest) {
                    highest = cost;
                }
                maintenanceCosts.put(complaint.getHomeId(), cost);
            } else {
                maintenanceCosts.put(complaint.getHomeId(), complaint.getOtherCosts());
            }
        }


        BinaryTree.Node root = new BinaryTree.Node(highest);
        for (Integer value : maintenanceCosts.values()) {
            tree.insert(root, value);
        }

        System.out.println("Top Maintenance Cost Tree");
        System.out.println("Building tree with root value " + root.value);
        System.out.println("Traversing tree in order");
        tree.traverseInOrder(root);


        return maintenanceCosts;
    }
}
