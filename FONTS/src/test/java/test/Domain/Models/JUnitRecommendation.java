package test.Domain.Models;

import org.junit.runner.JUnitCore;

public class JUnitRecommendation {
    public static void main(String[] args) {
        JUnitCore.main(new DriverRecommendationUnitary().getClass().getName());
    }
}
