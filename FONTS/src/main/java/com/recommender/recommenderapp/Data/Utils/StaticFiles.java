package com.recommender.recommenderapp.Data.Utils;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class StaticFiles {


    private String pathHead;
    private CSVReader reader;

    public StaticFiles(){
        pathHead =  System.getProperty("user.dir") + "\\" + Utils.getPATH();
        reader = new CSVReader();
    }



    public void writeDynamic(String dataset, String filename, String[][] data){

    }

     public String[] getDatasets() throws DirectoryDoesNotExist {

        File[] directories = new File(pathHead).listFiles(File::isDirectory);

        if(directories == null)
            throw new DirectoryDoesNotExist("DIRECTORY '" + pathHead + "' DOESN'T EXIST", pathHead);

        String[] datasets = new String[directories.length];

        int i = 0;

        for(File f : directories) {
            datasets[i] = f.getName();
            ++i;
        }

        return datasets;
    }


    /**
     *
     * @param dataset
     * @param filename
     * @return All the attributes of the csv files
     * @throws FileNotFoundException
     */
    public String[] getAttributes(String dataset, String filename) throws FileNotFoundException {
        String path = pathHead + "\\" + dataset + "\\" + filename + ".csv";
        return reader.readFirstLine(path);
    }


    public Map<String, Item> getItems(String dataset){
        Map<String,Item> items = new HashMap<>();

        String[][] data = reader.readFile(pathHead + dataset + "\\items.csv");

        String[] attributes = data[0];

        for(int i = 1; i < data.length; ++i){
            Item currentItem = new Item();

            for(int j = 0 ; j < data[i].length; ++j) {

                if (! data[i][j].equals("")) {
                    if ("id".equals(attributes[j])) {
                        currentItem.setId(data[i][j]);
                    } else if (attributes[j].contains("title")) {
                        currentItem.setTitle(data[i][j]);
                    }//
                    else if (Pattern.matches("^[\\\\+\\\\-]{0,1}[0-9]+[\\\\.\\\\,][0-9]  +$", data[i][j]) == true) { //
                        currentItem.addDoubleAttribute(attributes[j], Double.parseDouble(data[i][j]));
                    } else if (Pattern.matches("^[0-9]+$", data[i][j]) == true)
                        currentItem.addIntAttribute(attributes[j], Integer.parseInt(data[i][j]));
                    else if (data[i][j].contains(";")){
                        currentItem.addSetAttribute(attributes[j],new HashSet<>(Arrays.asList(data[i][j].split(";"))));
                    }else
                        currentItem.addStringAttribute(attributes[j], data[i][j]);
                }
            }
            items.put(currentItem.getId(),currentItem);
        }
        return items;
    }


    private Map<String,Integer> userAttributes(String[] attributes){
        Map<String,Integer> map = new HashMap<>();

        for(int i = 0; i < attributes.length ; ++i){
            map.put(attributes[i],i);
        }
        return map;
    }

    public Map<String, User> getUsers(String dataset,String filename, Map<String,Item> items){
        Map<String,User> users = new HashMap<>();

        String[][] data = reader.readFile(pathHead + dataset + "\\" + filename + ".csv" );

        Map<String,Integer> attributes = userAttributes(data[0]);


        for(int i = 1; i < data.length ; ++i) {
            String userId = data[i][attributes.get("userId")];
            if (!users.containsKey(userId)) {
                users.put(userId, new User(userId));
            }
            User currentUser = users.get(userId);
            currentUser.addItem(items.get(data[i][attributes.get("itemId")]));
            currentUser.rateItem(data[i][attributes.get("itemId")],Double.parseDouble(data[i][attributes.get("rating")]));
        }
        return users;
    }


}
