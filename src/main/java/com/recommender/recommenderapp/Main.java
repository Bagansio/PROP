package com.recommender.recommenderapp;

import com.recommender.recommenderapp.Data.Utils.Datasets;
import com.recommender.recommenderapp.Domain.Controllers.CtrlItemList;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;

public class Main {

    public static void main(String args[]){

        CtrlItemList ctrlItemList = new CtrlItemList();
        ctrlItemList.setDataset(Datasets.series);
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
        ctrlUsers.setDataset(Datasets.series);
        Map<String, User> users = ctrlUsers.getUsers();

        users.forEach((k,v) -> {
            System.out.println("User Id: " + k);
            v.getRatings().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            System.out.println();
        });
    }
}
