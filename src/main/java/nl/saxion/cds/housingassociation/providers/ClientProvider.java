package nl.saxion.cds.housingassociation.providers;

import nl.saxion.cds.housingassociation.models.people.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ClientProvider {
    public static PriorityQueue<Client> clients = new PriorityQueue<>(Collections.reverseOrder());

    static {
        File homeFile = new File("./src/main/java/nl/saxion/cds/housingassociation/data/waitinglist.csv");
        try(Scanner scanner = new Scanner(homeFile)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] splits = scanner.nextLine().split(";");
                if (splits.length > 0) {
                    Client newClient = new Client(Long.parseLong(splits[0]));
                    newClient.setName(splits[1]);
                    newClient.setInitials(splits[2]);
                    newClient.setUrgency(Integer.parseInt(splits[3]));
                    newClient.setNrOfRooms(Integer.parseInt(splits[4]));
                    newClient.setGarden(Boolean.parseBoolean(splits[5]));

                    clients.add(newClient);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
