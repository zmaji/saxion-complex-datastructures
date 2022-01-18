package nl.saxion.cds.housingassociation.controllers;

import nl.saxion.cds.housingassociation.models.people.QualifiedClientHome;
import nl.saxion.cds.housingassociation.models.people.Client;
import nl.saxion.cds.housingassociation.services.ClientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public PriorityQueue<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/qualified")
    public List<QualifiedClientHome> getQualifiedClients() {
        return clientService.getQualifiedClients();
    }
}
