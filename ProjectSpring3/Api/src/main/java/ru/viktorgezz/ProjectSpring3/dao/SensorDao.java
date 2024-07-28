package ru.viktorgezz.ProjectSpring3.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SensorDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SensorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllName() {
        return jdbcTemplate.query("SELECT name FROM Sensor", new BeanPropertyRowMapper<>(String.class)); //??
    }
}
