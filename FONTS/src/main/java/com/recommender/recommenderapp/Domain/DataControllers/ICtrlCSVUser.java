package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Data.Utils.Datasets;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;

public interface ICtrlCSVUser {

    /**
     *
     * @param items
     * @param dataset
     * @return Map (key, UserId) (value, User iteself) - users of ratings.db file
     */
    public Map<String, User> loadUserRatings(Map<String, Item> items, Datasets dataset);

    /**
     *
     * @param items
     * @param dataset
     * @return Map (key, UserId) (value, User iteself) - users of ratings.test.known
     */
    public Map<String, User> loadUserKnownRatings(Map<String, Item> items, Datasets dataset);

    /**
     *
     * @param items
     * @param dataset
     * @return Map (key, UserId) (value, User iteself) - users of ratings.test.unknown
     */
    public Map<String, User> loadUserUnknownRatings(Map<String, Item> items, Datasets dataset);
}
