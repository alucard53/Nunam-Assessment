package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.sql.Time;

@Embeddable
public class DataId implements Serializable {
    public int vNumber;

    @JoinColumn(name = "v_number")
    public Time time;
}
