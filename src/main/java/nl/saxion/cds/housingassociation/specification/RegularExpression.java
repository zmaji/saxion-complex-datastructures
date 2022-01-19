package nl.saxion.cds.housingassociation.specification;

import nl.saxion.cds.housingassociation.models.people.Client;
import nl.saxion.cds.housingassociation.providers.ClientProvider;

import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    private static PriorityQueue<Client> clients = ClientProvider.clients;

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
        String str1 = "H";
        System.out.println(str1 + ": " + isValidLastName(str1));

        String str2 = "uPSIDEDOWN";
        System.out.println(str2 + ": " + isValidLastName(str2));

        String str3 = "Williamson";
        System.out.println(str3 + ": " + isValidLastName(str3));

        String str4 = "1212121212";
        System.out.println(str4 + ": " + isValidLastName(str4));

        for (Client client : clients) {
            String lastName = client.getName();
            System.out.println(lastName + ": " + isValidLastName(lastName));
        }
    }
}
