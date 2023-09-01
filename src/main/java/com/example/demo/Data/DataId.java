package com.example.demo.Data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Embeddable
public class DataId implements Serializable {
    public int vNumber;

    @JoinColumn(name = "v_number")
    public Date date;

    @JoinColumn(name = "v_number")
    public Time time;
}
