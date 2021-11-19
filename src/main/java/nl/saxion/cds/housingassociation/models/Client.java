package nl.saxion.cds.housingassociation.models;

public class Client {
    private final Long clientID;
    private String name;
    private String initials;
    private int urgency;
    private int nrOfRooms;
    private boolean garden;

    public Client(Long clientID) {
        this.clientID = clientID;
    }

    public Long getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public int getNrOfRooms() {
        return nrOfRooms;
    }

    public void setNrOfRooms(int nrOfRooms) {
        this.nrOfRooms = nrOfRooms;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }
}
