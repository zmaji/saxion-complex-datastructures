package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.Home;
import nl.saxion.cds.housingassociation.models.people.Client;
import nl.saxion.cds.housingassociation.providers.ClientProvider;
import nl.saxion.cds.housingassociation.providers.HomeProvider;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ClientService {
    private static PriorityQueue<Client> clients = ClientProvider.clients;
    private static HashMap<String, Home> homes = HomeProvider.homes;

    public PriorityQueue<Client> getClients() {
        return clients;
    }

    public HashMap<Client, Home> getAvailableHomes() {
        List<Home> availableHomes = new ArrayList<>(homes.values());
        HashMap<Client, Home> qualifiedHomes = new HashMap<>();
//        System.out.println(availableHomes.size());
        for (Home home : availableHomes) {
            for (Client client : clients) {
                if (client.isGarden() == home.isGarden()) {
                    if (client.getNrOfRooms() == home.getNrOfRooms()) {
                        qualifiedHomes.put(client, home);
                    }
                }
            }
        }
        return qualifiedHomes;
    }

//    public List<Home> getAvailableHomes2() {
//        List<Home> availableHomes = new ArrayList<>(homes.values());
//        HashMap<Client, Home> qualifiedHomes = new HashMap<>();
////        System.out.println(availableHomes.size());
//        for (Home home : availableHomes) {
//            for (Client client : clients) {
//                if (client.isGarden() && home.isGarden()) {
//                    if (client.getNrOfRooms() == home.getNrOfRooms()) {
//                        qualifiedHomes.put(client, home);
//                    }
//                }
//            }
//        }
//        return availableHomes;
//    }
}
