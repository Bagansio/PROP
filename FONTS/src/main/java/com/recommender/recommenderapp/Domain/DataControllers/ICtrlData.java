package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Exceptions.DirectoryDoesNotExist;

import java.util.Map;

public interface ICtrlData {

    public boolean existTemp(String dataset);

    public Map<String, Item> loadItems(String dataset);

    public Map<String, User> loadKnownUsers(String dataset, boolean useTemp, Map<String,Item> items);

    public Map<String,User> loadUnknownUsers(String dataset, boolean useTemp, Map<String,Item> items);

    public Map<String,User> loadUsers(String dataset, boolean useTemp, Map<String,Item> items);

    public boolean saveUsers(String dataset, User[] users);

    public boolean saveKnownUsers(String dataset, User[] users);

    public boolean saveUnknownUsers(String dataset, User[] users);

    public String[] getDatasets() throws DirectoryDoesNotExist;
}
