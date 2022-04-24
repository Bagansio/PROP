package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.NewCollaborativeFiltering;
import com.recommender.recommenderapp.Domain.Models.User;
import stubs.StubsCollaborativeFiltering;
import stubs.StubsItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Artur Farriols
 */
public class DriverCollaborativeFiltering {

    public static final String ansiGreen = "\u001B[32m";
    public static final String ansiRed = "\u001B[31m";
    public static final String ansiNormal = "\u001B[0m";

    static boolean first = true;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int usage;
        Usage();
        while ((usage = reader.nextInt()) != 3) {
            switch (usage) {
                case 1:
                    executePreprocessingData();
                    break;

                case 2:
                    executeQuery();
                    break;

                default:
                    System.out.println(ansiRed + "Invalid value" + ansiNormal);
                    System.out.println();
                    Usage();
                    break;
            }
        }
    }

    public static void Usage() {
        if (first) {
            System.out.println();
            first = false;
        }
        System.out.println("You are executing CollaborativeFiltering's class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. preprocessingData: initializes all the data structures and applies the Kmeans algorithm");
        System.out.println(" \t2. Query: executes the Slope One Algorithm");;
        System.out.println(ansiRed + "\t3. Exit" + ansiNormal);
    }

    private static void executePreprocessingData() {
        System.out.println("We will now create an instance of the class and initialize all the necessary data" +
                "structures and execute the Kmeans algorithm");
        StubsCollaborativeFiltering stubsCF = new StubsCollaborativeFiltering();
        System.out.println("Creating first user");
        User u1 = stubsCF.getStubKnownUser1();
        System.out.println("Creating second user");
        User u2 = stubsCF.getStubKnownUser2();

        Map<String, User> m1 = new HashMap<>();
        System.out.println("Adding users");
        m1.put("known",u1); m1.put("unknown",u2);
        Map<String, Item> m2 = stubsCF.getStubItems();

        System.out.println("After executing the function, both users should be assigned to the same cluster");
        NewCollaborativeFiltering cf = new NewCollaborativeFiltering();
        cf.preprocessingData(m2, m1);

        System.out.println("First user in cluster" + cf.inWhichCluster("known"));
        System.out.println("Second user in cluster" + cf.inWhichCluster("unknown"));

        if (cf.inWhichCluster("known").equals(cf.inWhichCluster("unknown"))) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeQuery() {
        System.out.println("We will now create an instance of the class and, after initializing all the necessary data" +
                "structures and executing the Kmeans algorithm, ");
        StubsCollaborativeFiltering stubsCF = new StubsCollaborativeFiltering();
        Map<String, User> m1 = new HashMap<>();
        Map<String, Item> m2 = stubsCF.getStubItems();
        User knownUser = stubsCF.getStubKnownUser1();
        m1.put("known", knownUser);

        NewCollaborativeFiltering cf = new NewCollaborativeFiltering();
        cf.preprocessingData(m2, m1);

        Item unknownItem = new Item();
        unknownItem.setId("500");
        Map<String, Item> m3 = new HashMap<>();
        m3.put("500", unknownItem);

        User unknownUser= stubsCF.getStubUnknownUser();

        System.out.println("Expected rating for Item 500: 5.0");

        System.out.println("Executing query");
        Map<String, Double> m4 = cf.query(unknownUser, m3, 1);

        System.out.println("Obtained rating for Item 500: " + m4.get("500"));

        if (m4.get("500") == 5.0) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

}
