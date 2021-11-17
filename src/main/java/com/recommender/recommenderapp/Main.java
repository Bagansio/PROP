package com.recommender.recommenderapp;

import com.recommender.recommenderapp.Domain.Controllers.CtrlItemList;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;
import com.recommender.recommenderapp.Domain.Models.ContendBased;
import com.recommender.recommenderapp.Domain.Models.ContentBasedFiltering;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;

public class Main {

    public static void test1(){
        CtrlItemList ctrlItemList = new CtrlItemList();

        Map<String, Item> items = ctrlItemList.getItemList();
        /*
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
        */

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


    public static void testContentBased(){
        CtrlItemList ctrlItemList = new CtrlItemList();

        Map<String, Item> items = ctrlItemList.getItemList();
        CtrlUsers ctrlUsers = new CtrlUsers();
        Map<String, User> users = ctrlUsers.getUsers();
        Map<String, User> knownUsers = ctrlUsers.getKnownUsers();
        Map<String, User> unknownUsers = ctrlUsers.getUnknownUsers();

        ContentBasedFiltering algorithm = new ContentBasedFiltering();

        User user = knownUsers.entrySet().iterator().next().getValue();

        System.out.println(user.getId());
        algorithm.query(user,unknownUsers.get(user.getId()).getItems(),10);

    }
    public static void main(String args[]){
        testContentBased();

    }
}
