package com.recommender.recommenderapp.Data.Utils;


import java.util.HashMap;
import java.util.Map;


/**
 * @author Alex
 */
public class UserCSVUtils {

    private Map<String,Integer> positions;


    /**
     *
     * @param dataset
     * charge the position by a dataset
     */
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

    /**
     *
     * @return the position of UserId
     */
    public Integer getPositionUserId(){
        return positions.get("userId");
    }


    /**
     *
     * @return the position of Rating
     */
    public Integer getPositionRating(){
        return positions.get("rating");
    }


    /**
     *
     * @return the position of ItemId
     */
    public Integer getPositionItemId(){
        return  positions.get("itemId");
    }
}
