package fakes;

import com.recommender.recommenderapp.Domain.Models.Algorithm;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.User;

import java.util.HashMap;
import java.util.Map;

public class FakeAlgorithm extends Algorithm {

    public void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap) {}

    public Map<String, Double> query(User user, Map<String, Item> unknownItems, int Q) {
        Map<String, Double> result = new HashMap<>();
        result.put("In The Mood For Love", 5.0);
        return result;
    }

    public static Double discountedCumulativeGain(Map<String, Double> recommendedItems, User unknown) {
        return 1.0;
    }

}
