package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.Complaint;
import nl.saxion.cds.housingassociation.providers.ComplaintProvider;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ComplaintService {
    private static HashMap<String, Complaint> complaints = ComplaintProvider.complaints;

    public Collection<Complaint> getComplaints() {
        return complaints.values();
    }
}
