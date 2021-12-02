package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.CSVReader;
import com.recommender.recommenderapp.Data.Utils.StaticFiles;
import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.io.File;
import java.io.IOException;

public class CtrlData {

    public void writeKnownRates(String dataset, String[] data) throws IOException{
        String path = getPath(dataset) + Utils.TEMP + "\\" + Utils.KNOWN_USERS;
        StaticFiles sf = new StaticFiles();

        sf.writeFile(path, data);
    }


    private String getPath(String dataset){
        return  Utils.PATH + "\\" ;
    }

    public String[][] readItems(String dataset){
        String path = getPath(dataset) + Utils.ITEMS;
        CSVReader reader = new CSVReader();
        return reader.readFile(path);
    }

    /**
     *
     * @return
     * @throws DirectoryDoesNotExist
     */
    public String[] getDatasets() throws DirectoryDoesNotExist {

        File[] directories = new File(Utils.PATH).listFiles(File::isDirectory);

        if (directories == null)
            throw new DirectoryDoesNotExist("DIRECTORY '" + Utils.PATH + "' DOESN'T EXIST", pathHead);

        String[] datasets = new String[directories.length];

        int i = 0;

        for (File f : directories) {
            datasets[i] = f.getName();
            ++i;
        }

        return datasets;
    }

}
