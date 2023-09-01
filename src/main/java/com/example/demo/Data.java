package com.example.demo;

import jakarta.persistence.*;
import org.springframework.data.geo.Point;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

@Table //for table in Postgres
@Entity // for hibernate
public class Data {

    @EmbeddedId
    public DataId id;

    public Integer vState;

    public Double speed;

    public  Double[] location;

    public Double load;
    public Date date;

    public Data() {
        this.id = new DataId();
    }

    @Override
    public String toString() {
        return Integer.toString(id.vNumber) + " " + Integer.toString(vState) + " " + Double.toString(speed) + " " + Arrays.toString(location) + " " + id.time + " " + Double.toString(load) + " " + date;
    }
}
