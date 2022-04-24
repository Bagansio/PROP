package stubs;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.HashMap;
import java.util.Map;

public class StubsCollaborativeFiltering {

    public Map<String, User> getStubUsers() {
        Map<String, User> users = new HashMap<>();

        //Initialization User Known
        User userKnown = new User("known");
        for (Integer i = 1; i <= 500; ++i) {
            userKnown.rateItem(i.toString(), 5.0);
        }

        //Initialization User Unknown
        User userUnknown = new User("unknown");
        for (Integer i = 1; i <= 499; ++i) {
            userUnknown.rateItem(i.toString(), 5.0);
        }

        users.put("known", userKnown);
        users.put("unknown", userUnknown);
        return users;
    }

    public User getStubKnownUser1() {
        User userKnown = new User("known");
        for (Integer i = 1; i <= 500; ++i) {
            userKnown.rateItem(i.toString(), 5.0);
        }
        return userKnown;
    }

    public User getStubKnownUser2() {
        User userKnown = new User("unknown");
        for (Integer i = 1; i <= 500; ++i) {
            userKnown.rateItem(i.toString(), 5.0);
        }
        return userKnown;
    }

    public User getStubUnknownUser() {
        User userUnknown = new User("unknown");
        for (Integer i = 1; i <= 499; ++i) {
            userUnknown.rateItem(i.toString(), 5.0);
        }
        return userUnknown;
    }

    public Map<String, Item> getStubItems() {
        Map<String, Item> items = new HashMap<>();

        for (Integer i = 1; i <= 500; ++i) {
            Item item = new Item();
            item.setId(i.toString());
            items.put(i.toString(), item);
        }

        return items;
    }

}