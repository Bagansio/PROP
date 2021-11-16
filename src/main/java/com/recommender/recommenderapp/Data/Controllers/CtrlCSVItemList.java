package com.recommender.recommenderapp.Data.Controllers;


//import com.opencsv.CSVReader;
import com.recommender.recommenderapp.Data.Utils.CSVReader;
import com.recommender.recommenderapp.Data.Utils.ItemsCSVUtils;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVItemList;
import com.recommender.recommenderapp.Domain.Models.Item;

import java.util.*;

/**
 * @author Alex
 */
public class CtrlCSVItemList implements ICtrlCSVItemList {

    public Map<String, Item> getItemList() {
        Map<String, Item> items = new HashMap<>();

        try  {
            CSVReader reader = new CSVReader();
            List<List<String>> allData = reader.readFile("items");

            ItemsCSVUtils csvUtils = new ItemsCSVUtils();
            ListIterator<List<String>> iterator = allData.listIterator(1);
            while(iterator.hasNext()){
                List<String> itemData = iterator.next();
                Item currentItem = new Item();
                for (int i = 0; i < itemData.size(); ++i) {
                    if (csvUtils.relevantAttribute(i)) {
                        String[] attributeInfo = csvUtils.getAttributeInfo(i);
                        if (attributeInfo[0] == "id") {
                            currentItem.setId(itemData.get(i));
                        } else if (attributeInfo[0] == "title") {
                            currentItem.setTitle(itemData.get(i));
                        }
                        else{
                            switch (attributeInfo[1]) {
                                case "Integer":
                                    currentItem.addIntAttribute(attributeInfo[0], Integer.parseInt(itemData.get(i)));
                                    break;
                                case "String":
                                    currentItem.addStringAttribute(attributeInfo[0], itemData.get(i));
                                    break;
                                case "Double":
                                    currentItem.addDoubleAttribute(attributeInfo[0], Double.parseDouble(itemData.get(i)));
                                    break;
                                case "Set":
                                    List<String> attributes = reader.readLine(itemData.get(i),";" );
                                    currentItem.addSetAttribute(attributeInfo[0], new HashSet<>(attributes));
                                    break;
                            }
                        }

                    }
                }

                items.put(currentItem.getId(), currentItem);
                /*
                System.out.println("ID: " + currentItem.getId() + " Title: " + currentItem.getTitle());
                System.out.println("Integer Attributes: ");
                System.out.println("-----------------------------------------------------------------------");
                currentItem.getIntAttributes().forEach((k,v)->{
                    System.out.println(k + "  " + v);
                });

                System.out.println("TOTAL ITEMS: " + items.size());

                items.forEach((k,v)->{
                    System.out.println(k + " - " + v.getTitle());
                });
                */
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return items;
    }
}