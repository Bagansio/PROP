package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Models.UserGroup;
import com.recommender.recommenderapp.Domain.Utils.ItemTypes;
import org.junit.Assert;
import org.junit.Test;
import stubs.StubsUser;
import stubs.StubsUserGroup;

import java.util.Map;
import java.util.Scanner;

public class DriverUser {


    public static final String ansiGreen = "\u001B[32m";
    public static final String ansiRed = "\u001B[31m";
    public static final String ansiNormal = "\u001B[0m";

    static boolean first = true;

    public static void main(String[] args) {
        Scanner reader =  new Scanner(System.in);
        int usage;
        Usage();
        while ((usage = reader.nextInt()) != 12) {
            switch(usage){
                case 1:
                    executeSetId();
                    break;

                case 2:
                    executeGetId();
                    break;

                case 3:
                    executeSetName();
                    break;

                case 4:
                    executeGetName();
                    break;

                case 5:
                    executeAddItem();
                    break;

                case 6:
                    executeQuitItem();
                    break;

                case 7:
                    executeGetItem();
                    break;

                case 8:
                    executeRateItem();
                    break;

                case 9:
                    executeSetRate();
                    break;

                case 10:
                    executeGetItems();
                    break;

                case 11:
                    executeGetRatings();
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
        System.out.println("You are executing User's class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. setId: creates an user and change the id and checks if the insertion has been done properly");
        System.out.println(" \t2. getId: creates an user and checks if the function returns the proper value");
        System.out.println(" \t3. setName: creates an user and change the name and checks if the change has been done properly");
        System.out.println(" \t4. getName: creates an user with name and checks if the function returns the proper value");
        System.out.println(" \t5. addItem: creates an user and an item and checks if the insertion has been done properly");
        System.out.println(" \t6. quitItem: creates an user with and item and quit it and checks if the delete has been done properly");
        System.out.println(" \t7. getItem: creates a user with an item and checks if the function returns the proper value");
        System.out.println(" \t8. rateITem: creates an user with one item and rates it and checks if the insertion has been done properly ");
        System.out.println(" \t9. setRate: creates an user with one item and rating, changes the value of the rating and checks if the change has been done properly");
        System.out.println(" \t10. getItems: creates an user with two Items and checks if the function returns the proper value");
        System.out.println(" \t11. getRatings: creates an user with two valued Items  and checks if the function returns the proper value");
        System.out.println(ansiRed + "\t12. Exit" + ansiNormal);
    }

    public static void executeSetId(){
        System.out.println("Testing Set Id");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        user.setId("testing");
        System.out.println("Expected id values after the execution: testing");
        String id = user.getId();
        System.out.println("id after the execution: testing");
        if (id == "testing") System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }

    public static void executeGetId(){
        System.out.println("Testing Get Id");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        System.out.println("Expected id values after the execution: test");
        String id = user.getId();
        System.out.println("id after the execution: test");
        if (id == "test") System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    public static void executeSetName(){
        System.out.println("Testing Set Name");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        user.setName("testing");
        System.out.println("Expected name values after the execution: testing") ;
        String finalName = user.getName();
        System.out.println("id after the execution: testing" );
        if (finalName == "testing") System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    public static void executeGetName(){
        System.out.println("Testing Get Name");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUserWithName();
        String name = stubsUser.getStubUserName();
        System.out.println("Expected name values after the execution:" + name);
        String finalName = user.getName();
        System.out.println("id after the execution: " + name);
        if (finalName == name) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    public static void executeAddItem() {
        System.out.println("Testing Add Item");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        System.out.println("We will now  create an Item");
        Item item = stubsUser.getStubItem();
        user.addItem(item);
        String id = item.getId();
        System.out.println("Expected item id after the execution:" + id);
        Item obtainedItem = user.getItem(id);
        System.out.println("Item id after the execution:" + obtainedItem.getId());
        if (obtainedItem == item) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }
    public static void executeQuitItem() {
        System.out.println("Testing Quit Item");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        System.out.println("We will now  create an Item");
        Item item = stubsUser.getStubItem();
        System.out.println("We now  add the Item");
        user.addItem(item);
        System.out.println("We now  quit the Item");
        user.quitItem("TestItem");
        if (user.getItem("TestItem") == null) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);


    }
    public static void executeGetItem() {
        System.out.println("Testing Get Item");
        System.out.println("We will now  create an User With one Item");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUserWithItem();
        System.out.println("Expected item id after the execution: TestItem");
        Item obtainedItem = user.getItem("TestItem");
        System.out.println("Item id after the execution:" + obtainedItem.getId());
        if (obtainedItem.getId() == "TestItem") System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }
    public static void executeRateItem() {
        System.out.println("Testing Rate Item");
        System.out.println("We will now  create an User With one Item");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUserWithItem();
        Double rating = stubsUser.getStubRating();
        user.rateItem("TestItem", rating);
        System.out.println("We now rate the Item with the value: " +rating);
        Map<String, Double> ratings = user.getRatings();
        Double finalRating = ratings.get("TestItem");
        System.out.println("Rate of the Item after the execution: " + finalRating);
        if (finalRating == rating) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }
    public static void executeSetRate() {
        System.out.println("Testing Set Rate");
        System.out.println("We will now  create an User With one Item");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUserWithItem();
        Double rating = stubsUser.getStubRating();
        user.rateItem("TestItem", rating);
        System.out.println("We now rate the Item with the value: " +rating);
        user.setRate("TestItem", 10.0);
        System.out.println("We change the value of the rate by new value: 10.0" );
        Map<String, Double> ratings = user.getRatings();
        Double finalRating = ratings.get("TestItem");
        System.out.println("Rate of the Item after the execution: " + finalRating);
        if (finalRating == 10.0) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }
    public static void executeGetItems() {
        System.out.println("Testing Get Items");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        System.out.println("We Create 2 Items");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        System.out.println("First Item id : TestItem");
        Item item1 = new Item("TestItem1", "TestItem1", ItemTypes.movie);
        user.addItem(item1);
        System.out.println("Second Item id : TestItem1");
        Map<String, Item> items = user.getItems();
        System.out.println("Id of the first Item after the execution: " + items.get("TestItem").getId());
        System.out.println("Id of the second Item after the execution: " + items.get("TestItem1").getId());
        if (items.get("TestItem") == item && items.get("TestItem1") ==item1 ) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);


    }

    public static void executeGetRatings() {
        System.out.println("Testing Get Ratings");
        System.out.println("We will now  create an User");
        StubsUser stubsUser = new StubsUser();
        User user = stubsUser.getStubUser();
        System.out.println("We Create 2 Items");
        Item item = new Item("TestItem", "TestItem", ItemTypes.movie);
        user.addItem(item);
        System.out.println("First Item id : TestItem");
        Item item1 = new Item("TestItem1", "TestItem1", ItemTypes.movie);
        user.addItem(item1);
        System.out.println("Second Item id : TestItem1");
        System.out.println("We rate the items with values 3.0 and 4.0 respectively");
        user.rateItem("TestItem", 3.0);
        user.rateItem("TestItem1", 4.0);
        Map<String, Double> ratings = user.getRatings();
        Assert.assertEquals("GetRating Failed Item1", ratings.get("TestItem"), 3.0, 0);
        Assert.assertEquals("GetRating Failed Item2", ratings.get("TestItem1"), 4.0, 0);
        System.out.println("Rating of the first Item after the execution: " + ratings.get("TestItem"));
        System.out.println("Rating of the second Item after the execution: " + ratings.get("TestItem1"));
        if (ratings.get("TestItem") == 3.0 && ratings.get("TestItem1") ==4.0 ) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }
}
