package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Utils.Utils;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.util.Map;


/**
 * @author Alex
 */
public interface ICtrlData {

    public boolean existTemp(String dataset);

    public Map<String, Item> loadItems(String dataset);

    public Map<String, User> loadKnownUsers(String dataset, Map<String,Item> items);

    public Map<String,User> loadUnknownUsers(String dataset, Map<String,Item> items);

    public Map<String,User> loadUsers(String dataset, Map<String,Item> items);

    public Map<String,Recommendation> loadRecommendations(String dataset, Map<String,User> users);

    public boolean writeNewRecommendations(String dataset, Recommendation recommendation);

    public boolean saveUsers(String dataset, User[] users);

    public boolean saveKnownUsers(String dataset, User[] users);

    public boolean saveUnknownUsers(String dataset, User[] users);

    public boolean writeRecommendations(String dataset, Recommendation[] recommendations);

    public String[] getDatasets();

    public String tempDatasetToNormal(String dataset);

    public String getTempDataset(String dataset);
}
