package com.recommender.recommenderapp;

import com.recommender.recommenderapp.Domain.Controllers.CtrlItems;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;
import com.recommender.recommenderapp.Domain.Models.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainDepr {


    public static void main(String args[]){
        System.out.println("Initializing Main.....");
        Scanner reader =  new Scanner(System.in);
        CtrlItems ctrlItems = CtrlItems.getInstance();
        CtrlUsers ctrlUsers = CtrlUsers.getInstance();


        System.out.println("Select a Dataset");
        int i = 0;/*
        for (Datasets dataset: Arrays.asList(Datasets.values())){
            System.out.print(i);
            System.out.print(". ");
            System.out.println(dataset);
            ++i;
        }
        int usage;
        Datasets dataset = Datasets.movies;
        boolean exit = false;
        while (! exit){
            usage = reader.nextInt();
            switch(usage) {
                case 0:
                    dataset = Datasets.movies;
                    exit = true;
                    break;
                case 1:
                    dataset = Datasets.series;
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid value");
                    System.out.println();
                    break;
            }
        }
        ctrlItems.setDataset(dataset.toString());
        ctrlUsers.setDataset(dataset.toString());
        Map<String, Item> items = ctrlItems.getItems();
        Map<String, User> users = ctrlUsers.getUsers();
        Map<String, User> knownUsers = ctrlUsers.getKnownUsers();
        Map<String, User> unknownUsers = ctrlUsers.getUnknownUsers();
        boolean isCollaborative = true;
        System.out.println("Select an Algorithm");
        System.out.println("\t0. Collaborative Filtering");
        System.out.println("\t1. Content Based");
        exit = false;
        while (! exit){
            usage = reader.nextInt();
            switch(usage) {
                case 0:
                    isCollaborative = true;
                    exit = true;
                    break;
                case 1:
                    isCollaborative = false;
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid value");
                    System.out.println();
                    break;

            }

        }
        System.out.println("How many Known Users you will use");

        int numberKnownUsers = reader.nextInt();


        Integer itemId;
        String rating;
        Integer userId;
        Item item;
        Map<String, Item> unknownItem = new HashMap<>();
        for(int j =0; j< numberKnownUsers; ++j){ // Read the Known Users
            System.out.println("User Id:");
            userId = reader.nextInt();
            User user= new User(userId.toString());
            System.out.println("How many Known items you will use");
            int numberKnownItems = reader.nextInt();
            System.out.println("How many Unknown items you will use");
            int numberUnknownItems = reader.nextInt();
            System.out.println("How many recommendations you want");
            int Q = reader.nextInt();
            while(Q > numberUnknownItems){
                System.out.println("ERROR: The number of recommendations is bigger  than unknown items to recommend");
                System.out.println("TRY AGAIN");
                System.out.println("Number Unknown Items :" + numberKnownItems);
                Q = reader.nextInt();
            }

            Map<String, Double> ratings = user.getRatings();
            for(int w =0; w < numberKnownItems; ++w){ // Read the Known Items
                System.out.println("Item Known ID");
                itemId = reader.nextInt();
                System.out.println("Item Known rating");
                rating = reader.next();
                item = items.get(itemId.toString());
                user.addItem(item);
                ratings.put(itemId.toString(), Double.parseDouble(rating));
            }
            for(int w =0; w < numberUnknownItems; ++w){ // Read the Unknown Items

                System.out.println("Item Unknown ID");
                itemId = reader.nextInt();
                item = items.get(itemId.toString());
                unknownItem.put(itemId.toString(), item);
            }
            Map<String,Double> result;
            Algorithm alg;


            if(isCollaborative){
                NewCollaborativeFiltering algorithm = new NewCollaborativeFiltering();
                algorithm.preprocessingData(items,users);
                result = algorithm.query(user,unknownItem,Q);

            }
            else{
                ContentBasedFiltering algorithm = new ContentBasedFiltering();
                algorithm.preprocessingData(items,users);
                result = algorithm.query(user,unknownItem,Q);
            }
            var algorithm = new ContentBasedFiltering();
            //print:  Item Id   Expected Rate   Calculated Rate  Precision
            //Double discountedCumulativeGain = algorithm.discountedCumulativeGain(result,unknownUsers.get(userId));
            System.out.println("ITEM ID     Calculated Rate");
            for(String currentItem : result.keySet()){
                Double calculatedRate =  result.get(currentItem);
                //Double expectedRate = unknownUsers.get(userId).getRatings().get(currentItem);
                System.out.println(currentItem + "     " + calculatedRate + " " );

            }
            System.out.println("-----------------------------------------------");
            //System.out.println("DISCOUNTED CUMULATIVE GAIN: " + discountedCumulativeGain);

        }





        /// }

        // preguntar dataset  printear cual quieres y obtenerlo
        // printear algoritmo y ejecutarlo
        // preguntar cuantos known voy a leer los leo , cuantos unknown y leer
        // if content based decir que usuario
        //dar el output como queremos
        //print: User dataset
        //print:  Item Id   Expected Rate   Calculated Rate  Precision
        // }
        */
    }


}

