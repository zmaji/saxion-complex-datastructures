package nl.saxion.cds.housingassociation.models.people;

public class WorkTask {

    private Long homeID;
    private int totalEstimatedTime;
    private int totalActualTime;

    public WorkTask(Long homeID, int totalEstimatedTime, int totalActualTime) {
        this.homeID = homeID;
        this.totalEstimatedTime = totalEstimatedTime;
        this.totalActualTime = totalActualTime;
    }

    public Long getHomeID() {
        return homeID;
    }

    public void setHomeID(Long homeID) {
        this.homeID = homeID;
    }

    public int getTotalEstimatedTime() {
        return totalEstimatedTime;
    }

    public void setTotalEstimatedTime(int totalEstimatedTime) {
        this.totalEstimatedTime = totalEstimatedTime;
    }

    public int getTotalActualTime() {
        return totalActualTime;
    }

    public void setTotalActualTime(int totalActualTime) {
        this.totalActualTime = totalActualTime;
    }

    @Override
    public String toString() {
        return "HomeID: " + homeID + " total estimated time: " + totalEstimatedTime + " total actual time: " + totalActualTime;
    }
}
