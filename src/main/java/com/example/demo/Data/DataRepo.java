package com.example.demo.Data;

import com.example.demo.Data.Data;
import com.example.demo.Data.DataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

//Persistence Layer
public interface DataRepo extends JpaRepository<Data, DataId> {

    //Query to get all locations of a particular vehicle on a particular day
    @Query("SELECT d.location FROM Data d WHERE d.id.vNumber = ?1 AND d.id. date = ?2 AND d.vState = 1")
    public List<Double[]> findRunning(Integer vNumber, Date date);

    //Query to count number of running state occurrences of a vehicle in a day
    @Query("SELECT COUNT(d) FROM Data d WHERE d.id.vNumber = ?1 AND d.id.date = ?2 AND d.vState = 1")
    public long findRunningMins(Integer vNumber, Date date);

    //Query to count number of idle state occurrences of a vehicle in a day
    @Query("SELECT COUNT(d) FROM Data d WHERE d.id.vNumber = ?1 AND d.id.date = ?2 AND d.vState = 0")
    public long findIdleMins(Integer vNumber, Date date);
}
