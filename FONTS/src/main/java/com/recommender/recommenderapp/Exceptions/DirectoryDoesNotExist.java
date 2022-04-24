package com.recommender.recommenderapp.Exceptions;


/**
 * @author Alex
 */
public class DirectoryDoesNotExist extends Exception{

    private String path;

    public DirectoryDoesNotExist(String errorMessage, String path){
        super(errorMessage);
        this.path  = path;
    }

    public String getPath() {
        return path;
    }
}
