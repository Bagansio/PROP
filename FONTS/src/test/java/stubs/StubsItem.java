package stubs;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Utils.ItemTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StubsItem {

    public String getStubItemId() {
        String itemId = "0";
        return itemId;
    }

    public String getStubTitle() {
        String title = "ItemTitle";
        return title;
    }

    public ItemTypes getStubItemTypes() {
        ItemTypes type = ItemTypes.movie;
        return type;
    }

    public Map<String, String> getStubStringAttributes() {
        Map<String, String> stringAttributes = new HashMap<>();
        stringAttributes.put("originalLanguage", "English");
        stringAttributes.put("releaseDate", "21/11/21");
        return stringAttributes;
    }

    public Map<String, Integer> getStubIntAttributes() {
        Map<String, Integer> intAttributes = new HashMap<>();
        intAttributes.put("budget", 100);
        intAttributes.put("runtime", 120);
        return intAttributes;
    }

    public Map<String, Double> getStubDoubleAttributes() {
        Map<String, Double> doubleAttributes = new HashMap<>();
        doubleAttributes.put("popularity", 1.0);
        doubleAttributes.put("vote_average", 2.5);
        return doubleAttributes;
    }

    public Map<String, Set<String>> getStubSetAttributes() {
        Map<String, Set<String>> setAttributes = new HashMap<>();
        Set<String> aux1 = new HashSet<>();
        aux1.add("terror"); aux1.add("thriller");
        setAttributes.put("genres", aux1);
        Set<String> aux2 = new HashSet<>();
        aux2.add("English"); aux2.add("French");
        setAttributes.put("spokenLanguages", aux2);
        return setAttributes;
    }





}
