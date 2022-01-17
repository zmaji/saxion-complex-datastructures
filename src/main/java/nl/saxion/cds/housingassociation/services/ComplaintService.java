package nl.saxion.cds.housingassociation.services;

import nl.saxion.cds.housingassociation.models.Complaint;
import nl.saxion.cds.housingassociation.providers.ComplaintProvider;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComplaintService {
    private static HashMap<String, Complaint> complaints = ComplaintProvider.complaints;

    public Collection<Complaint> getComplaints() {
        return complaints.values();
    }

    public List<Map.Entry<String, Integer>> getTopComplaints () {
        List<String> categories = new ArrayList<>();
        HashMap<String, Integer> topComplaints = new HashMap<>();
        List<Map.Entry<String, Integer>> convertedTopComplaints = new ArrayList<>(topComplaints.entrySet());

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

        // Add name with frequency count into a new List so it can be ordered with new values
        topComplaints.put("Electricity", electCount);

        // PRE: topComplaints.size == 0
        assert topComplaints.size() == 1 : "Nothing has been added yet";
        // POST: topComplaints.size == 1

        topComplaints.put("Water", waterCount);
        topComplaints.put("Gas", gasCount);
        topComplaints.put("Other", otherCount);

        // Sort List based on frequency in reversed order
        System.out.println(convertedTopComplaints);
        convertedTopComplaints.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        System.out.println(topComplaints);
        System.out.println(convertedTopComplaints);
        return convertedTopComplaints;
    }
}
