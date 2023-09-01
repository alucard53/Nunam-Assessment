package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService {
    private StatRepo statRepo;

    @Autowired
    public StatService(StatRepo statRepo) {
        this.statRepo = statRepo;
    }

    public List<Stat> getAll() {
        return statRepo.findAll();
    }

    public void addCurrDay(List<Stat> stats) {
        statRepo.saveAll(stats);
    }
}