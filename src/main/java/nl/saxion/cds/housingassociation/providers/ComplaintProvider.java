package nl.saxion.cds.housingassociation.providers;

import nl.saxion.cds.housingassociation.models.Complaint;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ComplaintProvider {
    public static HashMap<String, Complaint> complaints = new HashMap<>();

    static {
        File homeFile = new File("./src/main/java/nl/saxion/cds/housingassociation/data/complaints.csv");
        try(Scanner scanner = new Scanner(homeFile)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] splits = scanner.nextLine().split(";");
                if (splits.length > 0) {
                    Complaint newComplaint = new Complaint(Long.parseLong(splits[0]));
                    newComplaint.setHomeId(Long.parseLong(splits[1]));
                    newComplaint.setCategory(splits[2]);
                    newComplaint.setEstimatedTime(Integer.parseInt(splits[3]));
                    if (splits.length > 4) {
                        newComplaint.setActualTime(Integer.parseInt(splits[4]));
                        newComplaint.setOtherCosts(Integer.parseInt(splits[5]));
                    }

                    complaints.put(splits[0], newComplaint);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
