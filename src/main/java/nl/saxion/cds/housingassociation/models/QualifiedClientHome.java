package nl.saxion.cds.housingassociation.models;

import nl.saxion.cds.housingassociation.models.people.Client;

public class QualifiedClientHome {
    private Client client;
    private Home home;

    public QualifiedClientHome(Client client, Home home) {
        this.client = client;
        this.home = home;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
