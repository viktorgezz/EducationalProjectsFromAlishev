package ru.viktorgezz.springproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.viktorgezz.springproject.model.Book;
import ru.viktorgezz.springproject.model.People;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDAO {

    // подключение к БД в config
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<People> index () {
        return jdbcTemplate.query("SELECT * FROM People", new PeopleMapper());
    }

    //create
    public void save(People people) {
         jdbcTemplate.update("INSERT INTO People(name, date_birth) VALUES (?, ?)",
            people.getName(), people.getDateBirth());
    }

    // show
    public People show(int id) {
        return jdbcTemplate.query("SELECT * FROM People WHERE people_id=?", new Object[]{id},
                new PeopleMapper()).stream().findAny().orElse(null);
    }

    public Optional<People> show(String name) {
        return jdbcTemplate.query("SELECT * FROM People WHERE name=?", new Object[]{name},
                new PeopleMapper()).stream().findAny();
    }

    //show
    public void delete(int id) {
         jdbcTemplate.update("DELETE FROM People WHERE people_id=?", id);
    }

    //edit
    public void update(int id, People updatePeople) {
        jdbcTemplate.update("UPDATE People SET name=?, date_birth=? WHERE people_id=?",
                updatePeople.getName(), updatePeople.getDateBirth(), id);
    }

    // Для валидации уникальности ФИО
    public Optional<People> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(People.class)).stream().findAny();
    }

    // Здесь Join не нужен. И так уже получили человека с помощью отдельного метода
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }


}
