package nl.saxion.cds.housingassociation.models;

public class TopComplaint implements Comparable<TopComplaint> {
    private String name;
    private int amount;

    public TopComplaint(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(TopComplaint o) {
        return o.amount - amount;
    }
}
