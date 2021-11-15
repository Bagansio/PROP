package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.CSVReader;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVUser;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class CtrlCSVUsers implements ICtrlCSVUser {

    private Map<String, User> loadUserFile(String filename,Map<String, Item> items){
        Map<String, User> users = new HashMap<>();
        try {
            CSVReader reader = new CSVReader();
            List<List<String>> allData = reader.readFile(filename);

            ListIterator<List<String>> iterator = allData.listIterator(1);
            while (iterator.hasNext()) {
                List<String> userData = iterator.next();
                String userId = userData.get(0);
                if (!users.containsKey(userId)) {
                    users.put(userId, new User(userId));
                }
                User currentUser = users.get(userId);
                currentUser.addItem(items.get(userData.get(1)));
                currentUser.rateItem(userData.get(1), Double.parseDouble(userData.get(2)));
            }
        }
        catch (Exception ex) {
        System.out.println(ex.toString());
        }
        return users;
    }

    public Map<String, User> loadUserRatings(Map<String, Item> items){
        return loadUserFile("ratings.db",items);
    }

    public Map<String, User> loadUserKnownRatings(Map<String, Item> items){
        return loadUserFile("ratings.test.known",items);
    }

    public Map<String, User> loadUserUnknownRatings(Map<String, Item> items){
        return loadUserFile("ratings.test.unknown",items);
    }
}
