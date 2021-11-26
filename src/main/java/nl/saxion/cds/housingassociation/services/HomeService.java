package nl.saxion.cds.housingassociation.services;

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

    public Collection<Home> getHomes() {
        return homes.values();
    }

    public HashMap<Long, Integer> getTopMaintenanceHomes() {
        HashMap<Long, Integer> maintenanceCosts = new HashMap<>();
        for (Complaint complaint : complaints.values()) {
            if (maintenanceCosts.containsKey(complaint.getHomeId())) {
                int cost = maintenanceCosts.get(complaint.getHomeId()) + complaint.getOtherCosts();
                maintenanceCosts.put(complaint.getHomeId(), cost);
            } else {
                maintenanceCosts.put(complaint.getHomeId(), complaint.getOtherCosts());
            }
        }


        return maintenanceCosts;
    }
}
