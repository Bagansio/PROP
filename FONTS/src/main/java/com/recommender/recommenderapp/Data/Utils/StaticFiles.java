package com.recommender.recommenderapp.Data.Utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StaticFiles {

    public static void main(String args[]){
        for(String f : getDatasets("DOCS\\datasets\\"))
            System.out.println(f);
    }




     public static String[] getDatasets(String path){
        File[] directories = new File(path).listFiles(File::isDirectory);


        String[] datasets = new String[directories.length];

        int i = 0;

        for(File f : directories) {
            datasets[i] = f.getName();
            ++i;
        }

        return datasets;
    }
}
