package nl.saxion.cds.housingassociation.models.complaint;

public class Complaint implements Comparable<Complaint> {
    private final Long complaintID;
    private Long homeID;
    private String category;
    private int estimatedTime;
    private int actualTime;
    private int otherCosts;

    @Override
    public int compareTo(Complaint c) {
        return (int) (complaintID - c.complaintID);
    }

    public Complaint(Long complaintID) {
        this.complaintID = complaintID;
    }

    public Long getComplaintID() {
        return complaintID;
    }

    public Long getHomeId() {
        return homeID;
    }

    public void setHomeId(Long homeID) {
        this.homeID = homeID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getActualTime() {
        return actualTime;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }

    public int getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(int otherCosts) {
        this.otherCosts = otherCosts;
    }
}
