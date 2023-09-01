package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepo extends JpaRepository<Stat, StatId> {
}
