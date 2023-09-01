package com.example.demo.GPS;

import java.util.Random;

public class DistanceHelpers {

    private static final double earth = 6371000.0;

    //Get random GPS location with a radius of the current GPS location of a vehicle
    //Used to generate sample data
    public static Double[] nextCoord(Double[] coord) {
        Random rand = new Random();

        //get random distance between 100m and 500m
        double dist = 100 + (500-100) * rand.nextDouble();

        double deltaLat =  (dist / earth * (180 / Math.PI));
        double deltaLong =  (dist / (earth * Math.cos(Math.toRadians(coord[0]))) * (180 / Math.PI));

        Double[] newCoord = new Double[2];

        newCoord[0] = coord[0] + deltaLat;
        newCoord[1] = coord[1] + deltaLong;

        return newCoord;
    }


    public static Double getDist(Double[] coord1, Double[] coord2) {
        double lat1Rad = Math.toRadians(coord1[0]);
        double lon1Rad = Math.toRadians(coord1[1]);
        double lat2Rad = Math.toRadians(coord2[0]);
        double lon2Rad = Math.toRadians(coord2[1]);

        // Haversine formula
        double dlat = lat2Rad - lat1Rad;
        double dlon = lon2Rad - lon1Rad;
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earth * c;
    }
}
