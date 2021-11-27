package com.recommender.recommenderapp.Domain.Models;

/**
 * @author Melanie Scatena
 */
public class Recommendation {
    private String id;
    private int score;

    /**
     *
     * @return the score of the recommendation
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score -> punctuation that will be assigned by the user to its recommendation
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return the identifier of the recommendation
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id -> identifier of the recommendation
     */
    public void setId(String id) {
        this.id = id;
    }
}
