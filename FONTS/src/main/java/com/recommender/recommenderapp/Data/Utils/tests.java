package com.recommender.recommenderapp.Data.Utils;

import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.Map;

public class tests {

    public static void main(String args[]) throws Exception {

        DataUtils dataUtils = new DataUtils();
        String dataset = "series";
        CSVReader reader = new CSVReader();
        //String[] attributes = dataUtils.getAttributes(dataset,Utils.ITEMS);
        Map<String, Item> m = dataUtils.getItems(dataset);
        /*
        staticFiles.getUsers(dataset,"ratings.db",m).forEach((u,us)->{
            System.out.println(u + ":");
            Map<String,Item> mo = us.getItems();
            us.getRatings().forEach((l,ms)->{
                System.out.println(l + "--> " + ms + " .... " + mo.get(l).getTitle());
            });

        });

        m.forEach((k,v)->{
            System.out.println("ITEM ID: "+ k + " TITLE: " + v.getTitle());
            System.out.println();
            System.out.println("DOUBLE ATTRIBUTES:");
            System.out.println();
            v.getDoubleAttributes().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            System.out.println();
            System.out.println("INT ATTRIBUTES:");
            System.out.println();
            v.getIntAttributes().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });

            System.out.println();
            System.out.println("STRING ATTRIBUTES");
            System.out.println();
            v.getStringAttributes().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            System.out.println();
            System.out.println("SET ATTRIBUTES:");
            System.out.println();
            v.getSetAttributes().forEach((ks,vs)->{
                System.out.print(ks + ": ");
                for(String value : vs)
                    System.out.print(value + "  -  ");
            });
            System.out.println("+++++++++++");
        });

        */
        //staticFiles.writeTempUsers(dataset,staticFiles.getUsers(dataset,"ratings.db",m),Utils.USERS);

    }
}
