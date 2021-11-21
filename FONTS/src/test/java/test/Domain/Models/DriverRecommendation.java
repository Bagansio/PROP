package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
import stubs.StubsItem;
import stubs.StubsRecommendation;

import java.util.Scanner;

public class DriverRecommendation {
    public static final String ansiGreen = "\u001B[32m";
    public static final String ansiRed = "\u001B[31m";
    public static final String ansiNormal = "\u001B[0m";

    static boolean first = true;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int usage;
        Usage();
        while ((usage = reader.nextInt()) != 5) {
            switch(usage) {
                case 1:
                    executeGetScore();
                    break;
                case 2:
                    executeSetScore();
                    break;
                case 3:
                    executeGetId();
                    break;
                case 4:
                    executeSetId();
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
        if (!first) {
            System.out.println();
            first = false;
        }
        System.out.println("You are executing Recommendation's class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. getScore(): creates an item and checks if the function returns the expected value");
        System.out.println("\t2. setScore(): creates an item and checks if the insertion has been done properly");
        System.out.println("\t3. getId(): creates an item and checks if the function returns the expected value");
        System.out.println("\t4. setId(): creates an item and checks if the insertion has been done properly");
        System.out.println(ansiRed + "\t5. Exit" + ansiNormal);
    }

    private static void executeGetScore() {
        System.out.println("We will now  create a Recommendation and add a score from the stub");
        StubsRecommendation stubsRecommendation = new StubsRecommendation();
        int score = stubsRecommendation.getStubScore();
        System.out.println("Expected Recommendation score value: " + score);

        System.out.println("Creating Recommendation");
        Recommendation r = new Recommendation();
        r.setScore(score);

        int aux = r.getScore();
        System.out.println("Score value obtained using the tested function: " + aux);
        if (score == aux) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }

    private static void executeSetScore() {
        System.out.println("We will now  create a Recommendation and add a score from the stub");
        StubsRecommendation stubsRecommendation = new StubsRecommendation();
        int score = stubsRecommendation.getStubScore();

        Recommendation r = new Recommendation();

        System.out.println("Expected score value after the execution: " + score);

        System.out.println("Inserting value");
        r.setScore(score);

        System.out.println("Score value after the execution: " + r.getScore());

        if (r.getScore() == score) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetId() {
        System.out.println("We will now  create a Recommendation and add an Id from the stub");
        StubsRecommendation stubsRecommendation = new StubsRecommendation();
        String id = stubsRecommendation.getStubId();
        System.out.println("Expected Recommendation id value: " + id);

        System.out.println("Creating Recommendation");
        Recommendation r = new Recommendation();
        r.setId(id);

        String aux = r.getId();
        System.out.println("id value obtained using the tested function: " + aux);
        if (id.equals(aux)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }

    private static void executeSetId() {
        System.out.println("We will now  create a Recommendation and add an Id from the stub");
        StubsRecommendation stubsRecommendation = new StubsRecommendation();
        String id = stubsRecommendation.getStubId();

        Recommendation r = new Recommendation();

        System.out.println("Expected id value after the execution: " + id);

        System.out.println("Inserting value");
        r.setId(id);

        System.out.println("id value after the execution: " + r.getId());

        if (r.getId().equals(id)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }
}
