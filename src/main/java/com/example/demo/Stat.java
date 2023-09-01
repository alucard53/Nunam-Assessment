package com.example.demo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "statistics")
@Entity
public class Stat {
    @EmbeddedId
    public StatId id;

    public Double distance;

    public Long timeIdle;

    public Long timeRunning;

    public Stat() {
        this.id = new StatId();
    }

    public String toString() {
        return Integer.toString(this.id.vNumber) + " " + this.id.date + " " + Double.toString(this.distance) + " " + Long.toString(this.timeIdle) + " " + Long.toString(this.timeRunning);
    }
}
