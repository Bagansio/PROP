package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.DataUtils;
import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;
import javafx.scene.chart.PieChart;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CtrlData implements ICtrlData {

    private DataUtils utils = new DataUtils();


    public boolean existTemp(String dataset){
        String path = getPath(dataset) + "\\" + Utils.TEMP;
        return utils.existTemp(path);
    }



    private String pathTemp(String filename, boolean useTemp){
        if(useTemp)
            filename = Utils.TEMP + "\\" + filename;

        return filename;
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


    public Map<String, Item> loadItems(String dataset){
        String path = getPath(dataset) + Utils.ITEMS;
        Map<String, Item> items = null;
        try {
            items = utils.getItems(path);
        }
        catch(Exception e) {

        }
        return items;
    }


    private Map<String, User> loadUsers(String dataset, String filename, Map<String,Item> items){
        String path = getPath(dataset) + "\\" + filename;
        Map<String,User> users = null;
        try {
            users = utils.getUsers(path, items);
        }
        catch (Exception e){
        }
        return users;
    }


    public Map<String,User> loadKnownUsers(String dataset, boolean useTemp, Map<String,Item> items){
        String filename = pathTemp(Utils.KNOWN_USERS, useTemp);

        return loadUsers(dataset,filename,items);
    }

    public Map<String,User> loadUnknownUsers(String dataset, boolean useTemp, Map<String,Item> items){
        String filename = pathTemp(Utils.UNKNOWN_USERS, useTemp);

        return loadUsers(dataset,filename,items);
    }

    public Map<String,User> loadUsers(String dataset, boolean useTemp, Map<String,Item> items){
        String filename = pathTemp(Utils.USERS, useTemp);

        return loadUsers(dataset,filename,items);
    }


    /**
     *
     * @param dataset
     * @param filename
     * @param users an Array of users to save
     * @return true -> correctly save , false -> not save
     */
    private boolean saveUsers(String dataset, String filename, User[] users){
        String path = getPath(dataset) + "\\" + Utils.TEMP;

        try {
            utils.createDir(path);
            path += "\\" + filename;
            utils.writeTempUsers(path,users);
        }
        catch(IOException e){
            return false;
        }
        return true;
    }


    /**
     * Save users in temp
     * @param dataset
     * @param users an Array of users to save
     * @return true -> correctly save , false -> not save
     */
    public boolean saveUsers(String dataset, User[] users){
        return saveUsers(dataset,Utils.USERS,users);
    }


    /**
     * Save known users in temp
     * @param dataset
     * @param users an Array of users to save
     * @return true -> correctly save , false -> not save
     */
    public boolean saveKnownUsers(String dataset, User[] users){
        return saveUsers(dataset,Utils.KNOWN_USERS,users);
    }


    /**
     * Save unknown users in temp
     * @param dataset
     * @param users an Array of users to save
     * @return true -> correctly save , false -> not save
     */
    public boolean saveUnknownUsers(String dataset, User[] users){
        return saveUsers(dataset,Utils.UNKNOWN_USERS,users);
    }


    /**
     *
     * @param dataset
     * @return The path to a dataset
     */
    private String getPath(String dataset){
        return  Utils.PATH + "\\" + dataset + "\\" ;
    }



    /**
     * return the datasets of the system
     * @return An array with the datasets
     */
    public String[] getDatasets(){

        File[] directories = new File(Utils.PATH).listFiles(File::isDirectory);

        if (directories == null)
            return new String[0];

        //String[] datasets = new String[directories.length];

        List<String> datasets = new ArrayList<>();
        int i = 0;


        for (File f : directories) {
            if(utils.existDataset(f.getPath())){
                datasets.add(f.getName());
            }
            ++i;
        }

        return datasets.toArray(new String[0]);
    }


    /**
     * Load the recommendations of a dataset
     * @param dataset Dataset to load
     * @param useTemp if use temp files
     * @param users
     * @return the values if can load or null if not
     */
    public Map<String,Recommendation> loadRecommendations(String dataset, boolean useTemp, Map<String,User> users){
        String path = getPath(dataset);
        if(useTemp) path += "\\" + Utils.TEMP;
        path += "\\" + Utils.RECOMMENDATIONS;

        Map<String,Recommendation> recommendations = null;

        try {
            recommendations = utils.readRecommendations(path, users);
        }
        catch(Exception e){
        }
        return recommendations;
    }

    /**
     * Write the recommendations
     * @param dataset Dataset to write
     * @param useTemp if use temp files
     * @param recommendations data to write
     * @return true if its write correctly, false if not
     */
    public boolean writeRecommendations(String dataset, boolean useTemp, Recommendation[] recommendations){
        String path = getPath(dataset);

        if(useTemp) path += "\\" + Utils.TEMP;

        path += "\\" + Utils.RECOMMENDATIONS;

        boolean written = true;
        try {
            utils.writeRecommendations(path, recommendations);
        }
        catch(Exception e){
            written = false;
        }
        return written;
    }


    /**
     * Append a new recommendation to the file
     * @param dataset Dataset to write
     * @param useTemp if use temp files
     * @param recommendation data to write
     * @return true if its write correctly, false if not
     */
    public boolean writeNewRecommendations(String dataset, boolean useTemp, Recommendation recommendation){
        String path = getPath(dataset);
        if(useTemp) path += "\\" + Utils.TEMP;
        utils.createDir(path);
        path += "\\" + Utils.RECOMMENDATIONS;

        boolean written = true;
        try {
            utils.writeNewRecommendation(path, recommendation);
        }
        catch (Exception e){
            written = false;
        }
        return written;
    }

}
