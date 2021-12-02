package com.recommender.recommenderapp.Data.Utils;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


/**
 *
 */
public class StaticFiles {


    private String pathHead;
    private CSVReader reader;
    private static final String LINE_BREAK = System.getProperty("line.separator");


    /**
     *
     */
    public StaticFiles() {
        pathHead = Utils.PATH;
        reader = new CSVReader();
    }






    /**
     * @param dataset
     * @param filename
     * @return All the attributes of the csv files
     * @throws FileNotFoundException
     */
    public String[] getAttributes(String dataset, String filename) throws FileNotFoundException {
        String path = pathHead + "\\" + dataset + "\\" + filename;
        return reader.readFirstLine(path);
    }


    /**
     *
     * @param dataset
     * @return
     */
    public Map<String, Item> getItems(String dataset) {
        Map<String, Item> items = new HashMap<>();

        String[][] data = reader.readFile(pathHead + dataset + "\\" + Utils.ITEMS);

        String[] attributes = data[0];

        for (int i = 1; i < data.length; ++i) {
            Item currentItem = new Item();

            for (int j = 0; j < data[i].length; ++j) {

                if (!data[i][j].equals("")) {
                    if ("id".equals(attributes[j])) {
                        currentItem.setId(data[i][j]);
                    } else if (attributes[j].contains("title")) {
                        currentItem.setTitle(data[i][j]);
                    }//
                    else if (Pattern.matches("^[\\\\+\\\\-]{0,1}[0-9]+[\\\\.\\\\,][0-9]  +$", data[i][j]) == true) { //
                        currentItem.addDoubleAttribute(attributes[j], Double.parseDouble(data[i][j]));
                    } else if (Pattern.matches("^[0-9]+$", data[i][j]) == true)
                        currentItem.addIntAttribute(attributes[j], Integer.parseInt(data[i][j]));
                    else if (data[i][j].contains(";")) {
                        currentItem.addSetAttribute(attributes[j], new HashSet<>(Arrays.asList(data[i][j].split(";"))));
                    } else
                        currentItem.addStringAttribute(attributes[j], data[i][j]);
                }
            }
            items.put(currentItem.getId(), currentItem);
        }
        return items;
    }


    /**
     *
     * @param attributes
     * @return
     */
    private Map<String, Integer> userAttributes(String[] attributes) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < attributes.length; ++i) {
            map.put(attributes[i], i);
        }
        return map;
    }


    /**
     * @param dataset
     * @param filename
     * @param items
     * @return
     */
    public Map<String, User> getUsers(String dataset, String filename, Map<String, Item> items) {
        Map<String, User> users = new HashMap<>();

        String[][] data = reader.readFile(pathHead + dataset + "\\" + filename + ".csv");

        Map<String, Integer> attributes = userAttributes(data[0]);


        for (int i = 1; i < data.length; ++i) {
            String userId = data[i][attributes.get("userId")];
            if (!users.containsKey(userId)) {
                users.put(userId, new User(userId));
            }
            User currentUser = users.get(userId);
            currentUser.addItem(items.get(data[i][attributes.get("itemId")]));
            currentUser.rateItem(data[i][attributes.get("itemId")], Double.parseDouble(data[i][attributes.get("rating")]));
        }
        return users;
    }

    private void writeLine(FileWriter writer, String line) throws IOException{
        writer.write(line + LINE_BREAK);
    }

    public void writeFile(String path, String[] data) throws IOException{
        File file = new File(path);
        FileWriter writer = new FileWriter(file);

        for(String line : data){
            writeLine(writer,line);
        }
    }



    /**
     *
     * @param fileWriter
     * @throws IOException
     */
    private void writeUserHeader(FileWriter fileWriter) throws IOException {
        fileWriter.write("itemId,rating,userId" + LINE_BREAK);
    }


    private void writeUserRates(FileWriter fileWriter, Map<String,User> users, Optional<String> delimiter) throws IOException {

        for(User user : users.values()){
            Map<String,Double> ratings = user.getRatings();
            for(String itemId : ratings.keySet()){
                fileWriter.write(itemId + "," + ratings.get(itemId) + "," + user.getId());
                fileWriter.write(LINE_BREAK);
            }
        }
    }
    /**
     *
     * @param dataset
     * @param users
     * @param filename
     * @throws IOException
     */
    public void writeTempUsers(String dataset, Map<String, User> users, String filename) throws IOException {
        String path = pathHead + "\\" + dataset + "\\" + Utils.TEMP + "\\";
        FilesUtils fUtils = new FilesUtils();


        fUtils.createDir(path);

        File file = new File(path + filename);


        FileWriter fileWriter = new FileWriter(file);

        String[] attributes = getAttributes(dataset, filename);

        writeUserHeader(fileWriter);

        writeUserRates(fileWriter, users, Optional.empty());

        fileWriter.flush();
    }
}
