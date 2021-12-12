package com.recommender.recommenderapp.Data.Utils;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;


/**
 *
 */
public class DataUtils {


    private CSVReader reader;


    /**
     *
     */
    public DataUtils() {
        reader = new CSVReader();
    }



    public boolean existTemp(String path){
        boolean exists =  new File(path).exists();
        exists &= new File(path + "\\" + Utils.KNOWN_USERS).exists();
        exists &= new File(path + "\\" + Utils.USERS).exists();
        exists &= new File(path + "\\" + Utils.UNKNOWN_USERS).exists();
        return exists;
    }

    public boolean existDataset(String path){
        return existTemp(path) && new File(path + "\\" + Utils.ITEMS).exists();
    }


    /**
     * @param path
     * @return All the attributes of the csv files
     * @throws FileNotFoundException
     */
    public String[] getAttributes(String path) throws FileNotFoundException {
        return reader.readFirstLine(path);
    }


    /**
     *
     * @param path
     * @return
     */
    public Map<String, Item> getItems(String path) throws Exception{
        Map<String, Item> items = new HashMap<>();

        String[][] data = reader.readFile(path);

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
     * @param path
     * @param items
     * @return
     */
    public Map<String, User> getUsers(String path, Map<String, Item> items) throws Exception{
        Map<String, User> users = new HashMap<>();

        String[][] data = reader.readFile(path);

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
        writer.write(line + Utils.LINE_BREAK);
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
        fileWriter.write("userId,rating,itemId" + Utils.LINE_BREAK);
    }


    private void writeUserRates(FileWriter fileWriter, User[] users) throws IOException {

        for(User user : users){
            Map<String,Double> ratings = user.getRatings();
            for(String itemId : ratings.keySet()){
                fileWriter.write(user.getId() + "," + ratings.get(itemId) + "," + itemId);
                fileWriter.write(Utils.LINE_BREAK);
            }
        }
    }

    private boolean createDir(String path){
        return new File(path).mkdir();
    }

    /**
     *
     * @param users
     * @throws IOException
     */
    public void writeTempUsers(String path, String filename, User[] users) throws IOException {

        createDir(path);

        File file = new File(path + "\\" + filename);

        FileWriter fileWriter = new FileWriter(file);

        writeUserHeader(fileWriter);

        writeUserRates(fileWriter, users);

        fileWriter.flush();
    }

}
