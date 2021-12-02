package com.recommender.recommenderapp.Data.Utils;

import java.io.File;

public class FilesUtils {

    public void createDir(String path){
        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

}
