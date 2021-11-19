package nl.saxion.cds.housingassociation.providers;

import nl.saxion.cds.housingassociation.models.Home;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class HomeProvider {
    public static HashMap<String, Home> homes = new HashMap<>();

    static {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);
        File homeFile = new File("./src/main/java/nl/saxion/cds/housingassociation/data/homes.csv");
        try(Scanner scanner = new Scanner(homeFile)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] splits = scanner.nextLine().split(";");
                if (splits.length > 0) {
//                    1000;718;473;600;2;false
                    Home newHome = new Home(Long.parseLong(splits[0]));
                    newHome.setAddressX(Integer.parseInt(splits[1]));
                    newHome.setAddressY(Integer.parseInt(splits[2]));
                    newHome.setRent(Integer.parseInt(splits[3]));
                    newHome.setNrOfRooms(Integer.parseInt(splits[4]));
                    newHome.setGarden(Boolean.parseBoolean(splits[5]));

                    homes.put(splits[0], newHome);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
