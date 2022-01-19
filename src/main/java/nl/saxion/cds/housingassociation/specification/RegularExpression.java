package nl.saxion.cds.housingassociation.specification;

import nl.saxion.cds.housingassociation.models.people.Client;
import nl.saxion.cds.housingassociation.providers.ClientProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    private static final PriorityQueue<Client> clients = ClientProvider.clients;
    private static final List<String> testNames = new ArrayList<>();

    public static boolean isValidLastName(String lastName) {

        // Regular expression to validate the Last Name with
            // ^ = First character
            // [A-Z] = Any capital character out of the alphabet
            // w{1, 11} = Name consists out of a minimum of 2 characters and a maximum of 12 after the first character
            // $ = end of the String
        String regex = "^[A-Z]\\w{1,11}$";

        // Compile the regular expression String
        Pattern pattern = Pattern.compile(regex);

        // If the Last Name is empty, return false
        if (lastName == null) {
            return false;
        }

        // Use matcher method to find match between lastName and regular expression
        Matcher matcher = pattern.matcher(lastName);

        // Return boolean based on if the lastName matched the regular expression
        return matcher.matches();
    }

    public static void main(String[] args) {
        testNames.add("");
        testNames.add("H");
        testNames.add("uPSIDEDOWN");
        testNames.add("Williamson");
        testNames.add("1212121212");
        testNames.add("Williamsonnnnnnnnnnnnnnnnnn");

        for (String testLastName : testNames) {
            System.out.println(testLastName + ": " + isValidLastName(testLastName));
        }

        for (Client client : clients) {
            String lastName = client.getName();
            System.out.println(lastName + ": " + isValidLastName(lastName));
        }
    }
}
