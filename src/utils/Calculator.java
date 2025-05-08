package utils;

import entity.*;

public class Calculator {
    public static double calculateCost(Resource resource, DateTimeRange range) {
        return resource.getCostPerHour() * range.getDurationInHours();
    }
}
