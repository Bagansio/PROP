package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.DataUtils;
import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlData;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;
import javafx.scene.chart.PieChart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            utils.writeTempUsers(path, filename,users);
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


    private String getPath(String dataset){
        return  Utils.PATH + "\\" + dataset + "\\" ;
    }



    /**
     *
     * @return
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

}
