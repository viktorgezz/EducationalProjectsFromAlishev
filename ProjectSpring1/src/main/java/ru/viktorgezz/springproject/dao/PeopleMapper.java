package ru.viktorgezz.springproject.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.viktorgezz.springproject.model.People;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleMapper implements RowMapper<People> {

    @Override
    public People mapRow(ResultSet resultSet, int i) throws SQLException {
        People people = new People();

        people.setId(resultSet.getInt("people_id"));
        people.setName(resultSet.getString("name"));
        people.setDateBirth(resultSet.getInt("date_birth"));

        return people;
    }

}
