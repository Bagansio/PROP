package com.recommender.recommenderapp.Data.Utils;

import java.util.HashMap;
import java.util.Map;


public class ItemsCSVUtils {

    private Map<Integer,String[]> attributesPositions;


    public ItemsCSVUtils(Datasets dataset){
        if(dataset.equals(Datasets.movies)){
            chargeMovies();
        }
        else chargeSeries();
    }


    private void chargeMovies(){
        attributesPositions = new HashMap<>();
        attributesPositions.put(0, new String[]{"adult", "Boolean"});
        attributesPositions.put(2, new String[]{"budget", "Integer"});
        attributesPositions.put(3, new String[]{"genres", "Set"});
        attributesPositions.put(5, new String[]{"id", "String"});
        attributesPositions.put(6, new String[]{"imbdId", "String"});
        attributesPositions.put(7, new String[]{"originalLanguage", "String"});
        attributesPositions.put(10, new String[]{"popularity", "Double"});
        attributesPositions.put(12, new String[]{"productionCompanies", "Set"});
        attributesPositions.put(13, new String[]{"productionCountries", "Set"});
        attributesPositions.put(14, new String[]{"releaseDate", "String"});
        attributesPositions.put(15, new String[]{"revenue", "Integer"});
        attributesPositions.put(16, new String[]{"runtime", "Integer"});
        attributesPositions.put(17, new String[]{"spokenLanguages", "Set"});
        attributesPositions.put(20, new String[]{"title", "String"});
        attributesPositions.put(21, new String[]{"video", "Boolean"});
        attributesPositions.put(22, new String[]{"vote_average", "Double"});
        attributesPositions.put(23, new String[]{"vote_count", "Double"});
        attributesPositions.put(24, new String[]{"keywords", "Set"});
    }

    private void chargeSeries(){
        attributesPositions = new HashMap<>();
        attributesPositions.put(0,new String[]{"id","String"});
        attributesPositions.put(1,new String[]{"title","String"});
        attributesPositions.put(2,new String[]{"synopsis","String"});
        attributesPositions.put(3,new String[]{"genres","Set"});
        attributesPositions.put(4,new String[]{"releaseDate","String"});
        //attributesPositions.put(5,new String[]{"episodes","Integer"});
        attributesPositions.put(6,new String[]{"members","Integer"});
        attributesPositions.put(7,new String[]{"popularity","Double"});
        //attributesPositions.put(8,new String[]{"ranked","Integer"});
        attributesPositions.put(9,new String[]{"score","Double"});
    }

    public boolean relevantAttribute(Integer index){
        return attributesPositions.containsKey(index);
    }

    public String[] getAttributeInfo(Integer index){
        return attributesPositions.get(index);
    }
}
