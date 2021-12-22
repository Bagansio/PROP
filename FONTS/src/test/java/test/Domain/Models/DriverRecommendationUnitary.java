package test.Domain.Models;
import com.recommender.recommenderapp.Domain.Models.Algorithm;
import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Models.Recommendation;
import com.recommender.recommenderapp.Domain.Models.User;
import com.recommender.recommenderapp.Domain.Utils.AlgorithmTypes;
import com.recommender.recommenderapp.Domain.Utils.PrecisionTypes;
import fakes.FakeAlgorithm;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class DriverRecommendationUnitary {
    Algorithm algorithm;
    Recommendation r;
    User known;
    User unknown;
    String precision;
    String algorithmType;
    int score;
    int Q;
    Map<String, Double> result;
    Map<String, Item> unknownItems;

    @Before
    public void setUp() {
        algorithm = new FakeAlgorithm();
        known = new User("known");
        unknown = new User("unknown");
        precision = "imprecise";
        algorithmType = "CollaborativeFiltering";
        r = new Recommendation("recommendation", algorithmType);
        score = 10;
        Q = 1;
        result = new HashMap<>();
        result.put("In The Mood For Love", 5.0);
        unknownItems = new HashMap<>();
        unknownItems.put("In The Mood For Love", new Item());
    }

    @Test
    public void executeGetAlgorithmType() {
        assertEquals("Should display CollaborativeFiltering", "CollaborativeFiltering", r.getAlgorithmType());
    }

    @Test
    public void executeSetAlgorithmType() {
        r.setAlgorithmType("ContentBasedFiltering");
        assertEquals("Should display ContentBasedFiltering", "ContentBasedFiltering", r.getAlgorithmType());
    }

    @Test
    public void executeSetScore() {
        r.setScore(score);
        assertEquals("Should display 10", 10, r.getScore());
    }

    @Test
    public void executeGetScore() {
        r.setScore(score);
        assertEquals("Should display 10", 10, r.getScore());
    }

    @Test
    public void executeSetUser() {
        r.setUser(unknown);
        assertEquals("Should display unknown user", unknown, r.getUser());
    }

    @Test
    public void executeGetUser() {
        r.setUser(unknown);
        assertEquals("Should display unknown user", unknown, r.getUser());
    }

    @Test
    public void executeSetId() {
        r.setId("Recommendation37");
        assertEquals("Should display Recommendation37", "Recommendation37", r.getId());
    }

    @Test
    public void executeGetId() {
        r.setId("Recommendation37");
        assertEquals("Should display Recommendation37", "Recommendation37", r.getId());
    }

    @Test
    public void executeSetItemMap() {
        r.setItemMap(unknownItems);
        assertEquals("Should display [In The Mood For Love, 5.0]", unknownItems, r.getItemMap());
    }

    @Test
    public void executeGetItemMap() {
        r.setItemMap(unknownItems);
        assertEquals("Should display [In The Mood For Love, 5.0]", unknownItems, r.getItemMap());
    }

    @Test
    public void executeSetPrecisionType() {
        r.setPrecisionType(precision);
        assertEquals("Should display imprecise", precision, r.getPrecisionType());
    }

    @Test
    public void executeGetPrecisionType() {
        r.setPrecisionType(precision);
        assertEquals("Should display imprecise", precision, r.getPrecisionType());
    }

    @Test
    public void executeQuery() {
        r.setPrecisionType(precision);
        r.executeQuery(unknownItems, algorithm, Q);
        assertEquals("Should display [In The Mood For Love, 5.0]", result, r.getRecommendedItems());
    }

    @Test
    public void executeSetRecommendedItems() {
        r.setRecommendedItems(result);
        assertEquals("Should display [In The Mood For Love, 5.0]", result, r.getRecommendedItems());
    }

    @Test
    public void executeGetRecommendedItems () {
        r.setPrecisionType(precision);
        r.executeQuery(unknownItems, algorithm, Q);
        assertEquals("Should display [In The Mood For Love, 5.0]", result, r.getRecommendedItems());
    }


}
