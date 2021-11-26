package nl.saxion.cds.housingassociation.models;

public class Client extends Person {
    private int urgency;
    private int nrOfRooms;
    private boolean garden;

    public Client(Long ID) {
        super(ID);
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
