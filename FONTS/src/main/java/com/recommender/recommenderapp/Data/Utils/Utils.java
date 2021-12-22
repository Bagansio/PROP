package com.recommender.recommenderapp.Data.Utils;

import java.io.File;

public class Utils {
    public static final String PATH = System.getProperty("user.dir") + File.separator + "DATA" + File.separator + "datasets" + File.separator;
    public static final String ITEMS = "items.csv";
    public static final String USERS = "ratings.db.csv";
    public static final String KNOWN_USERS = "ratings.test.known.csv";
    public static final String UNKNOWN_USERS = "ratings.test.unknown.csv";
    public static final String TEMP = "temp";
    public static final String RECOMMENDATIONS = "recommendations.csv";

    public static final String LINE_BREAK = System.getProperty("line.separator");




    public Utils() {
    }

}
