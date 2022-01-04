package nl.saxion.cds.housingassociation.controllers;

import nl.saxion.cds.housingassociation.models.Complaint;
import nl.saxion.cds.housingassociation.services.ComplaintService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComplaintController {
    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public Collection<Complaint> getComplaints() {
        return complaintService.getComplaints();
    }

    @GetMapping("/top-complaints")
    public void getTopComplaints() {
        complaintService.getTopComplaints();
    }

//    @GetMapping("/top-complaints")
//    public int getMostFrequent() {
//        return complaintService.getTopComplaintsCount();
//    }
}