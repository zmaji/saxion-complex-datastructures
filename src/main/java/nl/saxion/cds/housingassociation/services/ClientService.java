package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.Client;
import nl.saxion.cds.housingassociation.providers.ClientProvider;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;

@Service
public class ClientService {
    private static PriorityQueue<Client> clients = ClientProvider.clients;

    public PriorityQueue<Client> getClients() {
        return clients;
    }
}
