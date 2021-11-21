package nl.saxion.cds.housingassociation.providers;

import nl.saxion.cds.housingassociation.models.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ClientProvider {
    public static HashMap<String, Client> clients = new HashMap<>();

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

                    clients.put(splits[0], newClient);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
