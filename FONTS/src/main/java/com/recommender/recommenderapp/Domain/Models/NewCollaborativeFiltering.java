package com.recommender.recommenderapp.Domain.Models;

import com.recommender.recommenderapp.Domain.Controllers.CtrlItemList;
import com.recommender.recommenderapp.Domain.Controllers.CtrlUsers;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

/**
 * @author Artur Farriols
 */
public class NewCollaborativeFiltering extends Algorithm {

    private Map<String, User> centroids;
    private Map<String, UserGroup> clusters;
    private Map<String, User> users;
    private Map<String, Item> items;
    private Integer K;

    /**
     *
     * @param itemMap -> Map - key (itemId) value (item whose identifier corresponds with the key)
     * @param userMap -> Map - key (userId) value (user whose identifier corresponds with the key)
     */
    public void preprocessingData(Map<String, Item> itemMap, Map<String, User> userMap) {
        initializeDataStructures(itemMap, userMap);

        boolean change = true;

        while (change) {
            int changeCounter = 0;
            int userCounter = 0;

            for (String userId : users.keySet()) {
                User currentUser = users.get(userId);
                Double min = Double.MAX_VALUE;

                String minCentroidId = "";
                for (String centroidId : centroids.keySet()) {

                    Double distance = calculateEuclideanDistance(currentUser.getRatings(), centroids.get(centroidId).getRatings());
                    if (distance < min) {
                        min = distance;
                        minCentroidId = centroidId;
                    }
                }

                String previousCentroidId = inWhichCluster(userId);
                if (!minCentroidId.equals(previousCentroidId)) {
                    clusters.get(minCentroidId).addUser(currentUser);
                    if (!previousCentroidId.equals("none")) {
                        clusters.get(previousCentroidId).removeUser(userId);
                    }
                    ++changeCounter;
                }

                ++userCounter;
            }

            if (changeCounter == 0) change = false;
            else recalculateCentroids();
        }
    }

    /**
     * @param user -> user that contains all the Known ratings
     * @param unknownItems -> Map - key (itemId) value (unknown item whose identifier corresponds with the key)
     * @param Q -> number of items that must be recommended
     * @return -> a map of Q recommended itemsId (key) sorted by the ratings (value) calculated by the algorithm
     */
    public Map<String, Double> query(User user, Map<String, Item> unknownItems, int Q) {
        Map<String, Double> recommendedItems = new HashMap<String, Double>();

        Map<String, Double> knownRatings = user.getRatings();

        Double min = Double.MAX_VALUE;
        String userIsInCluster = "none";
        for (String centroidId : centroids.keySet()) {
            Double distance = calculateEuclideanDistance(knownRatings, centroids.get(centroidId).getRatings());

            if (distance < min) {
                min = distance;
                userIsInCluster = centroidId;
            }
        }

        for (String unknownItem : unknownItems.keySet()) {
            Map<String, User> subgroupOfUsers = getSubgroupOfUsers(unknownItem, userIsInCluster);

            Double rating = calculateRating(knownRatings, subgroupOfUsers, unknownItem, user);
            recommendedItems.put(unknownItem, rating);
        }

        searchNullValues(recommendedItems);

        return limitRecommendedItems(recommendedItems, Q);
    }

    /**
     * initializes all the data structures involved in the execution of the algorithm
     */
    private void initializeDataStructures(Map<String, Item> itemMap, Map<String, User> userMap) {
        Integer number = 3;
        initializeK(number);
        initializeItems(itemMap);
        initializeUsers(userMap);
        //getItems();
        //initializeValues4();
        initializeClusters();
    }

    /**
     *
     * @param number -> K value
     */
    private void initializeK(Integer number) {
        K = number;
    }

    /**
     * @param itemsId -> set of all the item identifiers
     * @param id      -> identifier of the centroid
     * @return a centroid
     */
    private User createCentroid(Set<String> itemsId, String id) {
        User centroid = new User(id);

        Double rate;
        for (String itemId : itemsId) {
            rate = generateRandomRating();
            centroid.rateItem(itemId, rate);
        }
        return centroid;
    }

    /**
     * creates a cluster of users that will be assigned to a specific centroid
     */
    private void initializeClusters() {
        centroids = new HashMap<>();
        clusters = new HashMap<>();
        //super.kmeans = 1;
        for (Integer i = 1; i <= K; ++i) {
            String centroidId = "centroid" + i.toString();
            User centroid = createCentroid(items.keySet(), centroidId);
            centroids.put(centroidId, centroid);
            clusters.put(centroidId, new UserGroup(i.toString()));
        }
    }

    /**
     * initializes the users data structure
     */
    private void initializeUsers(Map<String, User> userMap) {
        //CtrlUsers ctrlUsers = new CtrlUsers();
        //users = ctrlUsers.getUsers();
        users = userMap;
    }

    /**
     * initializes the items data structure
     */
    private void initializeItems(Map<String, Item> itemMap) {
        //CtrlItemList ctrlItemList = new CtrlItemList();

        //items = ctrlItemList.getItemList();
        items = itemMap;
    }

    /**
     * @return a randomly generated rating that will be assigned to a centroid
     */
    private double generateRandomRating() {
        Random r = new Random();
        Integer random = r.nextInt(5) + 1;
        Integer randomDecimal = r.nextInt(2);
        Double rand;
        if (randomDecimal == 1 && random != 5) {
            rand = random.doubleValue() + 0.5;
        } else rand = random.doubleValue();

        return rand;
    }

    /**
     * @param userRatings     -> Map - key(userId) value(ratings)
     * @param centroidRatings -> Map - key(centroidId) value(ratings)
     * @return ->the euclidean distance between the user and the centroid
     */
    private double calculateEuclideanDistance(Map<String, Double> userRatings, Map<String, Double> centroidRatings) {
        Double distance = 0.0;
        Double value;

        for (Map.Entry<String, Double> entry : userRatings.entrySet()) {
            value = entry.getValue() - centroidRatings.get(entry.getKey());
            distance += value * value;
        }

        return sqrt(distance);
    }

    /**
     * @param userId -> identifier of a user
     * @return the identifier of the group that the user identified by the parameter belongs to
     */
    public String inWhichCluster(String userId) {
        for (Map.Entry<String, UserGroup> group : clusters.entrySet()) {
            if (group.getValue().containsUser(userId)) {
                return group.getKey();
            }
        }
        return "none";
    }

    /**
     * recalculates the ratings of the centroids
     */
    private void recalculateCentroids() {
        Map<String, Double> newRatings;


        for (String centroidId : clusters.keySet()) {
            Map<String, Map<Double, Integer>> ratings = new HashMap<>();
            User currentCentroid = centroids.get(centroidId);
            Map<String, User> currentUsers = clusters.get(centroidId).getUsers();
            newRatings = currentCentroid.getRatings();

            for (String userId : currentUsers.keySet()) {
                Map<String, Double> ratingsUser = currentUsers.get(userId).getRatings();

                for (String itemId : ratingsUser.keySet()) {
                    Double ratingUser = ratingsUser.get(itemId);

                    if (ratings.containsKey(itemId)) {
                        Map<Double, Integer> aux = ratings.get(itemId);
                        Map.Entry<Double, Integer> entry;
                        entry = aux.entrySet().iterator().next();
                        Double rate = entry.getKey() + ratingUser;
                        Integer total = entry.getValue() + 1;
                        ratings.put(itemId, aux);
                    } else {
                        Map<Double, Integer> value = new HashMap<>();
                        value.put(ratingUser, 1);
                        ratings.put(itemId, value);
                    }
                }
            }

            for (String itemId : ratings.keySet()) {
                Double rating = ratings.get(itemId).entrySet().iterator().next().getKey();
                Integer total = ratings.get(itemId).entrySet().iterator().next().getValue();
                newRatings.put(itemId, rating / total.doubleValue());
            }
            centroids.get(centroidId).setRatings(newRatings);
        }
    }

    /**
     * @param itemId     -> identifies an unknown item
     * @param centroidId -> idenetifies a centroid and a cluster of users
     *      * @return a map of users (value) and their userIds (key) that are contained in a given cluster and
     *      * have rated the unknown item
     */
    private Map<String, User> getSubgroupOfUsers(String itemId, String centroidId) {
        Map<String, User> userSubgroup = new HashMap<String, User>();
        Map<String, User> userCluster = clusters.get(centroidId).getUsers();

        for (String userId : userCluster.keySet()) {
            if (userRatedItem(userId, itemId)) {
                userSubgroup.put(userId, userCluster.get(userId));
            }
        }

        return userSubgroup;
    }

    /**
     * @param userId -> identifier of a user
     * @param ItemId -> identifier of an unknown item
     * @return a boolean value that indicates if the user has rated the unknown item
     */
    private boolean userRatedItem(String userId, String ItemId) {
        return users.get(userId).getRatings().containsKey(ItemId);
    }

    /**
     * @param knownRatings    -> Map - key (itemId) value (known rating)
     * @param subgroupOfUsers -> Map - key (userId) value (users that rated the unknown item)
     * @param unknownItem     -> item which rating has to be determined by the algorithm
     * @param unknown         -> user that the algorithm is recommending products for
     * @return the estimated rating that the user will give to the unknown item
     */
    private double calculateRating(Map<String, Double> knownRatings, Map<String, User> subgroupOfUsers, String unknownItem,
                                   User unknown) {

        Map<String, Double> differenceBetweenItems = new HashMap<String, Double>();//contains the difference
        // between a given known item rating and the unknown one
        int itemCounter = 0;

        for (String itemId : knownRatings.keySet()) {
            Double difference = 0.0;
            for (String userId : subgroupOfUsers.keySet()) {
                if (userRatedItem(userId, itemId)) {
                    Double unknownItemRating = subgroupOfUsers.get(userId).getRatings().get(unknownItem);
                    Double knownItemRating = subgroupOfUsers.get(userId).getRatings().get(itemId);
                    difference += unknownItemRating - knownItemRating;
                    difference += knownRatings.get(itemId);
                    ++itemCounter;
                }
            }
            differenceBetweenItems.put(itemId, difference);
        }
        return rate(differenceBetweenItems, itemCounter);
    }

    /**
     * @param differenceBetweenItems -> Map - key (itemId) value (necessary data to calculate the rating)
     * @param counter                -> indicates the amount of ratings taken into account
     * @return -> the predicted rating
     */
    private Double rate(Map<String, Double> differenceBetweenItems, int counter) {
        Double sum = 0.0;

        for (Map.Entry<String, Double> entry : differenceBetweenItems.entrySet()) {
            sum += entry.getValue();
        }

        return sum / counter;
    }

    /**
     * @param recommendedItems -> Map - key (itemId) value (rating)
     * @param Q                -> amount of items that have to be recommended
     * @return an ordered map of the recommended items
     */
    private Map<String, Double> limitRecommendedItems(Map<String, Double> recommendedItems, int Q) {
        Map<String, Double> result = new LinkedHashMap<>();

        recommendedItems = recommendedItems.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Iterator<Map.Entry<String, Double>> it = recommendedItems.entrySet().iterator();

        int i = 0;
        while (i < Q && it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
            ++i;
        }

        return result;
    }

    /**
     * @param recommendedItems -> Map - key (itemId) value (rating)
     */
    private void searchNullValues(Map<String, Double> recommendedItems) {
        for (String itemId : recommendedItems.keySet()) {
            if (recommendedItems.get(itemId).isNaN()) {
                recommendedItems.put(itemId, 0.0);
            }
        }
    }
}
