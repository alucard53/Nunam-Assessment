package com.example.demo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;

import java.sql.Date;

@Embeddable
public class StatId {
    public int vNumber;

    @JoinColumn(name = "v_number")
    public Date date;
}
