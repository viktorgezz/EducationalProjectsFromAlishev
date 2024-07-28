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
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, date_creation) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getDateCreation());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id},
                new BookMapper()).stream().findAny().orElse(null);
    }

    public List<Book> showBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE people_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public People showPeople(int id) {
        return jdbcTemplate.query("SELECT * FROM book " +
                        "INNER JOIN people on book.people_id=people.people_id WHERE book.book_id=?",
                new Object[]{id}, new PeopleMapper()).stream().findAny().orElse(null);
    }


    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, date_creation=? WHERE book_id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getDateCreation(), id);
    }

    // Join'им таблицы Book и Person и получаем человека, которому принадлежит книга с указанным id
    public Optional<People> getBookOwner(int id) {
        // Выбираем все колонки таблицы Person из объединенной таблицы
        return jdbcTemplate.query("SELECT People.* FROM Book JOIN People ON Book.people_id = People.people_id " +
                        "WHERE Book.book_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(People.class))
                .stream().findAny();
    }

    public void assignStatus(int id, Integer statusId) {
        jdbcTemplate.update("UPDATE Book SET people_id=? WHERE book_id=?",
                statusId, id);
    }

    // Освбождает книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET people_id=NULL WHERE book_id=?", id);
    }

    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    public void assign(int id, People selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET people_id=? WHERE book_id=?", selectedPerson.getId(), id);
    }

}
