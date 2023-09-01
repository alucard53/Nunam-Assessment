package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Business layer
@Service
public class DataService {

    private static final double earth = 6371000.0;
    private DataRepo dataRepo;
    //Automatic injection of data repository by spring framework
    @Autowired
    public DataService(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    //Get random GPS location with a radius of the current GPS location of a vehicle
    //Used to generate sample data
    private static Double[] nextCoord(Double[] coord) {
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

    //Function to generate random sample data
    public void genNew() {
        Random rand = new Random();

        Data data = new Data();

        Date date = Date.valueOf(LocalDate.now());

        for (int d = 0; d <= 30; d++) {
            date = Date.valueOf(
                    date
                    .toLocalDate()
                    .plusDays(d)
            );
            for (int i = 1; i <= 4; i++) {

                Double[] coord = {19.0760, 72.8777};
                data.date = date;
                data.id.time = Time.valueOf(LocalTime.of(9, 30, 0));
                data.location = coord;
                data.id.vNumber = i;

                for (int j = 1; j < 10; j++) {
                    data.load = 50.0 + rand.nextDouble() * 100;
                    data.speed = rand.nextDouble() * 100;

                    data.id.time = Time.valueOf(data.id.time
                            .toLocalTime()
                            .plusMinutes(1));

                    if (rand.nextBoolean()) {
                        data.vState = 1;
                        coord = nextCoord(coord);
                        data.location = coord;
                    } else {
                        data.vState = 0;
                        data.speed = 0.0;
                    }
                    dataRepo.save(data);
                }
            }
        }
    }

    //Get distance between two GPS coordinates using the Haversine formula
    public Double getDist(Double[] coord1, Double[] coord2) {
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

    //Generate statistics from the data gathered in one day
    public List<Stat> getStat() {

        Date currDate = Date.valueOf(LocalDate.now());

        //List of stats to be collected and sent to StatService to insert into DB
        List<Stat> stats = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {

            //Calculate one row per vehicle per day
            Stat stat = new Stat();
            stat.id.vNumber = i;
            stat.id.date = currDate;
            stat.timeIdle = dataRepo.findIdleMins(i, currDate);
            stat.timeRunning = dataRepo.findRunningMins(i, currDate);

            List<Double[]> currDayRunning = dataRepo.findRunning(i, currDate);
            Double dist = 0.0;

            for (int j = 0; j < currDayRunning.size() - 1; j++) {

                dist += getDist(
                        currDayRunning.get(j),
                        currDayRunning.get(j+1)
                );
            }

            stat.distance = dist/1000;

            System.out.println(stat);
            stats.add(stat);
        }
        return stats;
    }
}
