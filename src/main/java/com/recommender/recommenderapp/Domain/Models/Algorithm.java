package com.recommender.recommenderapp.Domain.Models;

import  java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.List;
import java.util.Set;

public abstract class Algorithm {

    protected int kmeans;

    protected int knn;

    //Constructor

    Algorithm() {
        this.kmeans = 10;
        this.knn = 10;
    }

    //getters

    public int getKmeans() {
        return kmeans;
    }

    public int getKnn() {
        return knn;
    }

    //Algorithm execution

    public abstract void preprocessingData();

    public abstract void executeAlgorithm();

}
