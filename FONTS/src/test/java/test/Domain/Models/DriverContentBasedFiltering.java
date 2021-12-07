package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.ContentBasedFiltering;
import stubs.StubsCollaborativeFiltering;
import stubs.StubsContentBasedFiltering;

import java.util.Map;
import java.util.Scanner;

public class DriverContentBasedFiltering {

    public static final String ansiGreen = "\u001B[32m";
    public static final String ansiRed = "\u001B[31m";
    public static final String ansiNormal = "\u001B[0m";

    static boolean first = true;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int usage;
        Usage();
        while ((usage = reader.nextInt()) != 2) {
            switch (usage) {
                case 1:
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
        System.out.println("You are executing ContentBasedFiltering's class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. query: executes the Knn algorithm");
        System.out.println(ansiRed + "\t2. Exit" + ansiNormal);
    }

    private static void executeQuery() {
        System.out.println("We will now create an instance of the class and execute the algorithm");
        StubsContentBasedFiltering stubsCBF = new StubsContentBasedFiltering();



        ContentBasedFiltering cbf = new ContentBasedFiltering();
        Map<String, Double> m = cbf.query(stubsCBF.getUnknownUser(), stubsCBF.getUnknownItems(), 1);

        for (String s: m.keySet()) {
            System.out.println(m.get(s));
        }
    }
}
