package nl.saxion.cds.housingassociation.models;

public class Home {
    private final Long homeID;
    private int addressX;
    private int addressY;
    private int rent;
    private int nrOfRooms;
    private boolean garden;

    public Home(Long homeID) {
        this.homeID = homeID;
    }

    public Home(Long homeID, int addressX, int addressY) {
        this.homeID = homeID;
        this.addressX = addressX;
        this.addressY = addressY;
    }

    public Long getHomeID() {
        return homeID;
    }

    public int getAddressX() {
        return addressX;
    }

    public void setAddressX(int addressX) {
        this.addressX = addressX;
    }

    public int getAddressY() {
        return addressY;
    }

    public void setAddressY(int addressY) {
        this.addressY = addressY;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
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
