package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.DataUtils;
import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CtrlData {




    public boolean existTemp(String dataset){
        String path = getPath(dataset) + "\\" + Utils.TEMP;
        return new DataUtils().existTemp(path);
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


    public Map<String, Item> getItems(String dataset){
        String path = getPath(dataset) + Utils.ITEMS;
        DataUtils dataUtils = new DataUtils();
        return dataUtils.getItems(path);
    }


    private Map<String, User> getUsers(String dataset, String filename, Map<String,Item> items){
        String path = getPath(dataset) + "\\" + filename;

        DataUtils dataUtils = new DataUtils();
        return dataUtils.getUsers(path,items);
    }


    public Map<String,User> getKnownUsers(String dataset, boolean useTemp, Map<String,Item> items){
        String filename = pathTemp(Utils.KNOWN_USERS, useTemp);

        return getUsers(dataset,filename,items);
    }

    public Map<String,User> getUnknownUsers(String dataset, boolean useTemp, Map<String,Item> items){
        String filename = pathTemp(Utils.UNKNOWN_USERS, useTemp);

        return getUsers(dataset,filename,items);
    }

    public Map<String,User> getUsers(String dataset, boolean useTemp, Map<String,Item> items){
        String filename = pathTemp(Utils.USERS, useTemp);

        return getUsers(dataset,filename,items);
    }



    public void writeKnownRates(String dataset, String[] data) throws IOException{
        String path = getPath(dataset) + Utils.TEMP + "\\" + Utils.KNOWN_USERS;
        DataUtils dataUtils = new DataUtils();

        dataUtils.writeFile(path, data);
    }


    private String getPath(String dataset){
        return  Utils.PATH + "\\" + dataset + "\\" ;
    }



    /**
     *
     * @return
     * @throws DirectoryDoesNotExist
     */
    public String[] getDatasets() throws DirectoryDoesNotExist {

        File[] directories = new File(Utils.PATH).listFiles(File::isDirectory);

        if (directories == null)
            throw new DirectoryDoesNotExist("DIRECTORY '" + Utils.PATH + "' DOESN'T EXIST", Utils.PATH);

        String[] datasets = new String[directories.length];

        int i = 0;

        for (File f : directories) {
            datasets[i] = f.getName();
            ++i;
        }

        return datasets;
    }

}
