package ru.viktorgezz.springcourse.projectspring2boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.viktorgezz.springcourse.projectspring2boot.model.Book;




@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findFirstByTitleStartingWith(String searchByTitle);
}
