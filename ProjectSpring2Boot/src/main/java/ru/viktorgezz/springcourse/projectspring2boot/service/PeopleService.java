package ru.viktorgezz.springcourse.projectspring2boot.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.springcourse.projectspring2boot.model.Book;
import ru.viktorgezz.springcourse.projectspring2boot.model.People;
import ru.viktorgezz.springcourse.projectspring2boot.repo.PeopleRepository;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // только для чтения
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public People findOne(int id) {
        Optional<People> foundPeople = peopleRepository.findById(id);
        return foundPeople.orElse(null);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<People> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());

            List<Book> books = person.get().getBooks();
            books.forEach(this::isExpired);

            return books;
        }
        return Collections.emptyList();
    }

    private void isExpired(Book book) {
        LocalDate dateTake = book.getDateTake();

        long daysBetween = 0;
        if (dateTake != null) {
            daysBetween = Math.abs(ChronoUnit.DAYS.between(dateTake, LocalDate.now()));
        }

        book.setExpired(daysBetween >= 5);
    }

    @Transactional
    public void save(People people) {
        peopleRepository.save(people);
    }

    @Transactional
    public void update(int id, People updatedPeople) {
        updatedPeople.setPeopleId(id);
        peopleRepository.save(updatedPeople);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
