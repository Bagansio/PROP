package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.UserGroup;
import stubs.StubsUserGroup;

import java.util.Scanner;

public class DriverUserGroup {
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
                    executeGetId();
                    break;

                case 2:
                    executeSetId();
                    break;
                default:
                    System.out.println("Invalid value");
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
        System.out.println("You are executing User Group class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. getID: creates an User Group and checks if the getter works properly");
        System.out.println(" \t2. setId: change the id and checks if the change has been done properly");
        System.out.println(ansiRed + "\t3. Exit" + ansiNormal);
    }

    private static void executeGetId() {
        System.out.println("Testing Get Id");
        System.out.println("We will now  create an UserGroup");
        StubsUserGroup stubsUserGroup = new StubsUserGroup();
        UserGroup userGroup = stubsUserGroup.getStubUserGroup();
        System.out.println("Expected id values after the execution: 1");
        int finalId =userGroup.getId();
        System.out.println("id after the execution: " +finalId);
        if (finalId == 1) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);


    }
    private static void executeSetId() {
        System.out.println("Testing Set Id");
        System.out.println("We will now  create an UserGroup");
        UserGroup userGroup = new UserGroup(1);
        int id =5 ;
        userGroup.setId(id);
        System.out.println("Expected id values after the execution:" + id);
        int finalId =userGroup.getId();
        System.out.println("id after the execution: " +finalId);
        if (finalId == id) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }


}