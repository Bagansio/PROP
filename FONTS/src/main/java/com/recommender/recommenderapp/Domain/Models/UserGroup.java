package com.recommender.recommenderapp.Domain.Models;

import java.util.List;

public class UserGroup {
    private int id;
    List<User> users;

    public UserGroup(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
