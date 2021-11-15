package com.recommender.recommenderapp.Domain.DataControllers;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.Map;

public interface ICtrlCSVUser {

    public Map<String, User> loadUserRatings(Map<String, Item> items);
}
