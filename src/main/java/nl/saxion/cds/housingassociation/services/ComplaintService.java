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

    public void getTopComplaints () {
        List<String> categories = new ArrayList<>();
        HashMap<String, Integer> topComplaints = new HashMap<>();
        List<Map.Entry<String, Integer>> convertedTopComplaints = new ArrayList<>(topComplaints.entrySet());

        // Add all category variables of a Complaint object into a new List
        for (Complaint complaint : complaints.values()) {
            categories.add(complaint.getCategory());
        }

        // Count the frequency of a certain category
        int electCount = Collections.frequency(categories, "electricity");
        int waterCount = Collections.frequency(categories, "water");
        int gasCount = Collections.frequency(categories, "gas");
        int otherCount = Collections.frequency(categories, "other");

        // Add name with frequency count into a new List
        topComplaints.put("Electricity", electCount);
        topComplaints.put("Water", waterCount);
        topComplaints.put("Gas", gasCount);
        topComplaints.put("Other", otherCount);

        // Sort List based on frequency in reversed order
        convertedTopComplaints.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        System.out.println(convertedTopComplaints);
    }



//    public int getTopComplaintType() {
//        Collection<Complaint> types = complaints.values();
//
//        int electCount = Collections.frequency(types, "electricity");
//        int waterCount = Collections.frequency(types, "water");
//        int gasCount = Collections.frequency(types, "gas");
//        int otherCount = Collections.frequency(types, "other");
//
//        List<Integer> numbers = new ArrayList<>();
//
//        int highestType = 0;
//        for (Integer type : types) {
//            if (type > highestType) {
//                highestType = type;
//            }
//        }
//
//        return highestType;
//    }

}
