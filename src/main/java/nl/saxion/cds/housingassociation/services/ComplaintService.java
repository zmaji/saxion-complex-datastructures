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
////        for (Integer type : types) {
////            if (type > highestType) {
////                highestType = type;
////            }
////        }
//
//        return highestType;
//    }
}
