package com.recommender.recommenderapp.Data.Utils;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.io.FileNotFoundException;
import java.util.Map;

public class tests {

    public static void main(String args[]) throws FileNotFoundException {

        StaticFiles staticFiles = new StaticFiles();
        CSVReader reader = new CSVReader();
        String[] attributes = staticFiles.getAttributes("movies","items");
        Map<String, Item> m = staticFiles.getItems("movies");

        m.forEach((k,v)->{
            System.out.println("ITEM ID: "+ k + " TITLE: " + v.getTitle());
            v.getDoubleAttributes().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            v.getIntAttributes().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            System.out.println("...............");
            v.getStringAttributes().forEach((key,value)->{
                System.out.println(key + ": " + value);
            });
            System.out.println("+++++++++++");
        });



    }
}
