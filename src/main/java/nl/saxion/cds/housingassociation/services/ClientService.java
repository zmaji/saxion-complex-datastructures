package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.models.people.Client;
import nl.saxion.cds.housingassociation.providers.ClientProvider;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    private static PriorityQueue<Client> clients = ClientProvider.clients;
    private static HashMap<String, Home> homes = HomeProvider.homes;

    public PriorityQueue<Client> getClients() {
        return clients;
    }

    public List<Map.Entry<Client, Home>> getQualifiedClients() {
        HashMap<Client, Home> qualifiedClients = new HashMap<>();

        // Convert Priority Queue to List due to not being able to iterate through a Priority Queue because .poll() removes the element.
        List<Client> sortedClients = new ArrayList<>(clients);
        sortedClients.sort(Comparator.reverseOrder());

        // For every client loop through all homes
        for (Client client : sortedClients) {
            for (Home home : homes.values()) {
                // Check if desired specifications are qualified
                if (client.isGarden() == home.isGarden() && client.getNrOfRooms() == home.getNrOfRooms()) {
                    // If the home isn't already given away, add to the HashMap
                    if (!qualifiedClients.containsValue(home)) {
                        qualifiedClients.put(client, home);
                    }
                }
            }
        }
        // Convert HashMap to List for sorting based on urgency
        List<Map.Entry<Client, Home>> convertedQualifiedClients = new ArrayList<>(qualifiedClients.entrySet());
        convertedQualifiedClients.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        return convertedQualifiedClients;
    }
}
