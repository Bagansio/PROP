package stubs;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StubsContentBasedFiltering {
    public User getUnknownUser() {
        User unknown = new User("unknown");

        //Known Items
        Item knownItem = new Item();
        knownItem.setId("known");
        knownItem.setTitle("Title");
        knownItem.setType(ItemTypes.movie);

        Map<String, Integer> m1 = new HashMap<>();
        m1.put("revenue", 100);
        knownItem.setIntAttributes(m1);

        Map<String, Double> m2 = new HashMap<>();
        m2.put("popularity", 1.0);
        knownItem.setDoubleAttributes(m2);

        Map<String, String> m3 = new HashMap<>();
        m3.put("releaseDate", "21/11/21");
        knownItem.setStringAttributes(m3);

        Map<String, Set<String>> m4 = new HashMap<>();
        Set<String> s1 = new HashSet<>();
        s1.add("terror"); s1.add("thriller");
        m4.put("genres", s1);
        knownItem.setSetAttributes(m4);

        unknown.rateItem("known", 5.0);

        return unknown;
    }
    public Map<String, Item> getUnknownItems() {
        //Unknown Items
        Item unknownItem = new Item();
        unknownItem.setId("unknown");
        unknownItem.setTitle("Title");
        unknownItem.setType(ItemTypes.movie);

        Map<String, Integer> m1 = new HashMap<>();
        m1.put("revenue", 100);
        unknownItem.setIntAttributes(m1);

        Map<String, Double> m2 = new HashMap<>();
        m2.put("popularity", 1.0);
        unknownItem.setDoubleAttributes(m2);

        Map<String, String> m3 = new HashMap<>();
        m3.put("releaseDate", "21/11/21");
        unknownItem.setStringAttributes(m3);

        Map<String, Set<String>> m4 = new HashMap<>();
        Set<String> s1 = new HashSet<>();
        s1.add("terror"); s1.add("thriller");
        m4.put("genres", s1);
        unknownItem.setSetAttributes(m4);

        Map<String, Item> m = new HashMap<>();
        m.put("unknown", unknownItem);

        return m;
    }
}