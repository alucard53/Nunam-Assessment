package com.example.demo.Data;

import com.example.demo.GPS.DistanceHelpers;
import com.example.demo.Stat.Stat;
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

    private DataRepo dataRepo;
    //Automatic injection of data repository by spring framework
    @Autowired
    public DataService(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }


    //Function to generate random sample data
    public void genNew() {
        Random rand = new Random();

        Data data = new Data();

        Date date = Date.valueOf(LocalDate.now());

        //Adds data from current day to 30 days in future
        //Change the limit of the loop to change the number of days for which
        //data is generated
        for (int d = 0; d <= 30; d++) {
            date = Date.valueOf(
                    date
                    .toLocalDate()
                    .plusDays(d)
            );
            //Loop to generate data for each vehicle
            for (int i = 1; i <= 4; i++) {

                Double[] coord = {19.0760, 72.8777};
                data.id.date = date;
                data.id.time = Time.valueOf(LocalTime.of(9, 30, 0));
                data.location = coord;
                data.id.vNumber = i;

                //Generate 10 minutes for each vehicle
                //Change limit of loop to generate more data per day
                for (int j = 1; j < 10; j++) {
                    data.load = 50.0 + rand.nextDouble() * 100;
                    data.speed = rand.nextDouble() * 100;

                    data.id.time = Time.valueOf(data.id.time
                            .toLocalTime()
                            .plusMinutes(1));

                    if (rand.nextBoolean()) {
                        data.vState = 1;
                        coord = DistanceHelpers.nextCoord(coord);
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

                dist += DistanceHelpers.getDist(
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
