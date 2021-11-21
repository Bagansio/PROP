package com.recommender.recommenderapp.Data.Controllers;

import com.recommender.recommenderapp.Data.Utils.CSVReader;
import com.recommender.recommenderapp.Data.Utils.Datasets;
import com.recommender.recommenderapp.Data.Utils.UserCSVUtils;
import com.recommender.recommenderapp.Domain.DataControllers.ICtrlCSVUser;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class CtrlCSVUsers implements ICtrlCSVUser {

    private Map<String, User> loadUserFile(String filename, Map<String, Item> items, Datasets dataset){
        Map<String, User> users = new HashMap<>();
        try {
            CSVReader reader = new CSVReader();
            List<List<String>> allData = reader.readFile(filename,dataset.toString());
            UserCSVUtils csvUtils = new UserCSVUtils(dataset);
            ListIterator<List<String>> iterator = allData.listIterator(1);
            while (iterator.hasNext()) {
                List<String> userData = iterator.next();
                String userId = userData.get(csvUtils.getPositionUserId());
                if (!users.containsKey(userId)) {
                    users.put(userId, new User(userId));
                }
                User currentUser = users.get(userId);
                currentUser.addItem(items.get(userData.get(csvUtils.getPositionItemId())));
                currentUser.rateItem(userData.get(csvUtils.getPositionItemId()), Double.parseDouble(userData.get(csvUtils.getPositionRating())));
            }
        }
        catch (Exception ex) {
            ex.getStackTrace();
        }
        return users;
    }

    public Map<String, User> loadUserRatings(Map<String, Item> items, Datasets dataset){
        return loadUserFile("ratings.db",items, dataset);
    }

    public Map<String, User> loadUserKnownRatings(Map<String, Item> items, Datasets dataset){
        return loadUserFile("ratings.test.known",items,dataset);
    }

    public Map<String, User> loadUserUnknownRatings(Map<String, Item> items, Datasets dataset){
        return loadUserFile("ratings.test.unknown",items,dataset);
    }
}
