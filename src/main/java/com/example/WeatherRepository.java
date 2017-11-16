package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Transactional
    void deleteByDone(boolean done);
}
