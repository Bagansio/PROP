package com.recommender.recommenderapp.Data.Controllers;


import com.opencsv.CSVReader;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlItem;
import com.recommender.recommenderapp.Domain.Models.ItemList;

import java.io.FileReader;
import java.util.List;

/**
 * @author Alex
 */
public class CtrlCSVItemList implements ICtrlItem {

    private static final String PATH = "src\\main\\resources\\datasets\\items.csv";

    public static void main(String[] args) {
        getItemList();
    }

    public static ItemList getItemList() {
        ItemList items = new ItemList();
        try (CSVReader reader = new CSVReader(new FileReader(PATH))) {
            List<String[]> allData = reader.readAll();


            for (String[] data : allData) {
                for (String s : data) {
                    System.out.println(s + " ");
                }
                System.out.println();
            }
        } catch (Exception ex) {

        }
        return items;
    }
}
