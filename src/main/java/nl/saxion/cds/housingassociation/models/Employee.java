package nl.saxion.cds.housingassociation.models;

import java.util.List;

public class Employee extends Person {
    private List<Complaint> complaints;
    private List<Route> routes;

    public Employee(Long ID) {
        super(ID);
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
