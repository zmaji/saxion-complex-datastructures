package nl.saxion.cds.housingassociation.models;

public class TopMaintenanceHome implements Comparable<TopMaintenanceHome> {
    private final Long HomeID;
    private int totalMaintenanceCost;

    public TopMaintenanceHome(Long homeID, int totalMaintenanceCost) {
        HomeID = homeID;
        this.totalMaintenanceCost = totalMaintenanceCost;
    }

    public Long getHomeID() {
        return HomeID;
    }

    public int getTotalMaintenanceCost() {
        return totalMaintenanceCost;
    }

    public void setTotalMaintenanceCost(int totalMaintenanceCost) {
        this.totalMaintenanceCost = totalMaintenanceCost;
    }

    @Override
    public int compareTo(TopMaintenanceHome o) {
        return  o.totalMaintenanceCost - totalMaintenanceCost;
    }
}
