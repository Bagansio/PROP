package com.recommender.recommenderapp;

import com.recommender.recommenderapp.Domain.Controllers.CtrlItemList;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;
import com.recommender.recommenderapp.Domain.Models.ContentBasedFiltering;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void test1(){
        CtrlItemList ctrlItemList = new CtrlItemList();

        Map<String, Item> items = ctrlItemList.getItemList();
        System.out.println(items.size());
        items.forEach((k,v) -> {
            System.out.println("ItemId: " + k +  " ItemTitle: " + v.getTitle());
            v.getSetAttributes().forEach((key,value)->{
                System.out.println(key + ":");

                for(String l : value){
                    System.out.print(l + " - ");
                }
                System.out.println();
                System.out.println();
            });
        });


        CtrlUsers ctrlUsers = new CtrlUsers();
        Map<String, User> users = ctrlUsers.getUsers();

        users.forEach((k,v) -> {
            System.out.println("User Id: " + k);
            v.getRatings().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            System.out.println();
        });
    }


    public static void testContentBasedItemsEqual(){
        User user = new User("test");
        Item testItem1 = new Item();
        Item testItem2 = new Item();
        Item testItem3 = new Item();

        testItem1.setId("test1");
        testItem2.setId("test2");
        testItem3.setId("test3");

        Map<String,Integer> intAttributes = new HashMap<>();
        Map<String,Integer> intAttributes2 = new HashMap<>();
        Map<String,Integer> intAttributes3 = new HashMap<>();
        intAttributes.put("attribute1",10);
        intAttributes2.put("attribute1",100);
        intAttributes3.put("attribute1",5);

        testItem1.setIntAttributes(intAttributes);
        testItem2.setIntAttributes(intAttributes2);
        testItem3.setIntAttributes(intAttributes3);


        Map<String,String> stringAttributes = new HashMap<>();
        Map<String,String> stringAttributes2 = new HashMap<>();
        stringAttributes.put("att", "att");

        stringAttributes2.put("att", "5");

        testItem1.setStringAttributes(stringAttributes);
        testItem2.setStringAttributes(stringAttributes2);

        System.out.println(testItem1.getIntAttributes().get("attribute1"));

        user.addItem(testItem1);
        user.addItem(testItem2);


        Map<String,Item> unknown = new HashMap<>();
        unknown.put("test1",testItem1);
        unknown.put("test2",testItem2);
        unknown.put("test3",testItem3);
        ContentBasedFiltering algorithm = new ContentBasedFiltering();
        algorithm.query(user,unknown);

    }
    public static void testContentBased(){
        CtrlItemList ctrlItemList = new CtrlItemList();

        Map<String, Item> items = ctrlItemList.getItemList();
        CtrlUsers ctrlUsers = new CtrlUsers();
        Map<String, User> users = ctrlUsers.getUsers();
        Map<String, User> knownUsers = ctrlUsers.getKnownUsers();

        Map<String, User> unknownUsers = ctrlUsers.getUnknownUsers();
        //System.out.println(unknownUsers);

        ContentBasedFiltering algorithm = new ContentBasedFiltering();

        User user = knownUsers.entrySet().iterator().next().getValue();

        System.out.println(user.getId());
        //System.out.println(user.getRatings());
        Map<String, Double> result = algorithm.query(user,unknownUsers.get(user.getId()).getItems());
                result.forEach((k,v)->{
            System.out.println("ITEM ID: " +k + " - rate: " + v);
        });

        /*
        System.out.println("TTTTTTTTTTTTTTTTT");
        user.getRatings().forEach((k,v) ->{
            System.out.println(k + " - " + v);
        });
        Map<String,Double> result = user.getRatings().entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println("+++++++++++++---------------");
        result.forEach((k,v) ->{
            System.out.println(k + " - " + v);
        });

        */

    }
    public static void main(String args[]){
        //test1();
        testContentBased();
        //testContentBasedItemsEqual();
    }
}
