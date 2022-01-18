package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.complaint.Complaint;
import nl.saxion.cds.housingassociation.models.complaint.TopComplaint;
import nl.saxion.cds.housingassociation.providers.ComplaintProvider;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComplaintService {
    private static HashMap<String, Complaint> complaints = ComplaintProvider.complaints;

    public Collection<Complaint> getComplaints() {
        List<Complaint> complaintObjects = new ArrayList<>(complaints.values());
        Collections.sort(complaintObjects);
        return complaintObjects;
    }

    public List<TopComplaint> getTopComplaints () {
        List<String> categories = new ArrayList<>();

        // Add all category variables of a Complaint object into a new List due to not being able to count the frequency in HashMap
        for (Complaint complaint : complaints.values()) {
            categories.add(complaint.getCategory());
        }
        // PRE: categories.size == 0
        assert !categories.isEmpty() : "No categories have been found to add!";
        // POST: categories.size == 4

        // Count the frequency of a certain category
        int electCount = Collections.frequency(categories, "electricity");

        // PRE: electCount == 0
        assert electCount == 248 : "Something went wrong with counting";
        // POST: electCount == 248

        int waterCount = Collections.frequency(categories, "water");
        int gasCount = Collections.frequency(categories, "gas");
        int otherCount = Collections.frequency(categories, "other");

        // Initialise new List with TopComplaints
        List<TopComplaint> topComplaintList = new ArrayList<>();
        topComplaintList.add(new TopComplaint("Electricity", electCount));
        topComplaintList.add(new TopComplaint("Water", waterCount));
        topComplaintList.add(new TopComplaint("Gas", gasCount));
        topComplaintList.add(new TopComplaint("Other", otherCount));

        // Sort TopComplaints based on frequency
        Collections.sort(topComplaintList);

        return topComplaintList;
    }
}
