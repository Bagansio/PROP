package com.recommender.recommenderapp.Domain.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Artur Farriols
 */
public class UserGroup {
    private String id;
    private Map<String, User> users;

    /**
     *
     * @param id -> contains the identifier of the new UserGroup
     */
    public UserGroup(String id) {
        this.id = id;
        users = new HashMap<String, User>();
    }

    /**
     *
     * @return a string that contains the id of the UserGroup
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id -> identifier of the UserGroup
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return a map that contains the users belonging to the UserGroup
     */
    public Map<String, User> getUsers() {
        return users;
    }

    /**
     *
     * @param users -> users that will be added to the UserGroup
     */
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    /**
     *
     * @param userId -> identifier of the user
     * @return a boolean indicating whether or not the UserGroup contains the specified user
     */
    public boolean containsUser(String userId){
        return users.containsKey(userId);
    }

    /**
     *
     * @param newUser -> user that needs to be added to the UserGroup
     */
    public void addUser(User newUser){
        users.put(newUser.getId(),newUser);
    }

    /**
     *
     * @param userId -> user that need to be removed from the UserGroup
     */
    public void removeUser(String userId){
        users.remove(userId);
    }
}
