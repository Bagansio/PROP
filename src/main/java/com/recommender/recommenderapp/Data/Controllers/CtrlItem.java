package com.recommender.recommenderapp.Data.Controllers;



import com.recommender.recommenderapp.Data.Utils.CSVReader;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlItem;

import java.io.IOException;
import java.util.List;

public class CtrlItem implements ICtrlItem {
    /* THATS FOR TEST THE CSVREADER
    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        List<List<String>> fileData = reader.readFile("items");
        for(List<String> line : fileData) {
            for(String element : line){
                System.out.println(element);
            }
        }


    }
    */
}
