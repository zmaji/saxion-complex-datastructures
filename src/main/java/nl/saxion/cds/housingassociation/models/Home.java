package nl.saxion.cds.housingassociation.models;

public class Home {
    private final Long HomeId;
    private int addressX;
    private int addressY;
    private int rent;
    private int nrOfRooms;
    private boolean garden;

    public Home(Long homeId) {
        HomeId = homeId;
    }

    public Long getHomeId() {
        return HomeId;
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
