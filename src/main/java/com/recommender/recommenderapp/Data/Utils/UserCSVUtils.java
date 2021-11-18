package com.recommender.recommenderapp.Data.Utils;


import java.util.HashMap;
import java.util.Map;

public class UserCSVUtils {

    private Map<String,Integer> positions;


    public UserCSVUtils(Datasets dataset){
        if(dataset.equals(Datasets.movies)){
            positions = new HashMap<>();
            positions.put("userId",0);
            positions.put("itemId",1);
            positions.put("rating",2);
        }
        else{
            positions = new HashMap<>();
            positions.put("userId",2);
            positions.put("itemId",0);
            positions.put("rating",1);
        }
    }

    public Integer getPositionUserId(){
        return positions.get("userId");
    }

    public Integer getPositionRating(){
        return positions.get("rating");
    }

    public Integer getPositionItemId(){
        return  positions.get("itemId");
    }
}
