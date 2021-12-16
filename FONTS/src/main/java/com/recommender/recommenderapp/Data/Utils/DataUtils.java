package com.recommender.recommenderapp.Data.Utils;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
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


    /**
     *
     * @param fileWriter
     * @throws IOException
     */
    private void writeUserHeader(FileWriter fileWriter) throws IOException {
        fileWriter.write("userId,rating,itemId" + Utils.LINE_BREAK);
    }

    private void writeUserRates(FileWriter fileWriter, User user) throws  IOException{
        Map<String,Double> ratings = user.getRatings();
        for(String itemId : ratings.keySet()){
            fileWriter.write(user.getId() + "," + ratings.get(itemId) + "," + itemId);
            fileWriter.write(Utils.LINE_BREAK);
        }
    }


    public boolean createDir(String path){
        return new File(path).mkdir();
    }

    /**
     *
     * @param users
     * @throws IOException
     */
    public void writeTempUsers(String path, User[] users) throws IOException {

        File file = new File(path);

        FileWriter fileWriter = new FileWriter(file);

        writeUserHeader(fileWriter);

        for(User user: users)
            writeUserRates(fileWriter,user);

        fileWriter.flush();
    }


    /**
     * @brief Read Recommendation file.
     * @param path The recommendation file path
     * @param users Known Users
     * @return A Map with recommendations;
     * @throws Exception
     */
    public Map<String, Recommendation> readRecommendations(String path, Map<String,User> users) throws Exception{
        Map<String, Recommendation> recommendations = new LinkedHashMap<>();

        String[][] data = reader.readFile(path);


        for (int i = 1; i < data.length; ++i) {
            Recommendation currentRecommendation = new Recommendation(data[i][0],data[i][2]);
            currentRecommendation.setUser(users.get(data[i][1]));
            currentRecommendation.setScore(Integer.parseInt(data[i][3]));
            currentRecommendation.setPrecisionType(data[i][4]);

            Map<String,Double> rates = new LinkedHashMap<>();

            for(String rate : data[i][5].split(";")){
                String[] rateInfo = rate.split("-");
                rates.put(rateInfo[0],Double.parseDouble(rateInfo[1]));
            }
            recommendations.put(currentRecommendation.getId(),currentRecommendation);
        }
        return recommendations;
    }

    public void writeRecommendations(String path, Recommendation[] recommendations) throws IOException{

        System.out.println(recommendations.length);

        File file = new File(path);

        FileWriter fileWriter = new FileWriter(file);


        fileWriter.write("recommendationId,userId,algorithm,recommendationRate,precisionType,itemsId-rate" + Utils.LINE_BREAK);

        for(Recommendation currentRecommendation : recommendations){
            writeRecommendation(fileWriter,currentRecommendation);
        }

        fileWriter.flush();
    }

    private void writeRecommendation(FileWriter fileWriter, Recommendation recommendation) throws IOException{
        fileWriter.write(recommendation.getId() + "," + recommendation.getUser().getId() + "," + recommendation.getAlgorithmType() + "," + recommendation.getScore()  +  ","  +  recommendation.getPrecisionType().toString() + ",");
        Map<String, Double> rates = recommendation.getRecommendedItems();
        for (String itemId : rates.keySet()){
            fileWriter.write(itemId+"-"+rates.get(itemId)+";");
        }
        fileWriter.write(Utils.LINE_BREAK);
    }

    public void writeNewRecommendation(String path, Recommendation recommendation) throws IOException{

        File file = new File(path);
        if(! file.exists() || file.length() == 0){

            writeRecommendations(path,new Recommendation[]{recommendation});
        }
        else {
            FileWriter fileWriter = new FileWriter(file, true);

            writeRecommendation(fileWriter, recommendation);

            fileWriter.flush();
        }
    }

}
