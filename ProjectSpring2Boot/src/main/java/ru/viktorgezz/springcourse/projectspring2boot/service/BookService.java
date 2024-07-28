package ru.viktorgezz.springcourse.projectspring2boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.viktorgezz.springcourse.projectspring2boot.model.Book;
import ru.viktorgezz.springcourse.projectspring2boot.model.People;
import ru.viktorgezz.springcourse.projectspring2boot.repo.BookRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(int page, int itemsPerPage, boolean isSort) {
        if (page == -1 && itemsPerPage == -1 && !isSort) {
            return bookRepository.findAll();
        } else if (page == -1 && itemsPerPage == -1 && isSort) {
            return bookRepository.findAll(Sort.by("dateCreation"));
        } else if (!isSort) {
            return bookRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
        }
        return bookRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("dateCreation"))).getContent();
    } // Лучше разделить на два метода. Первый ищет всё с сортировкой и без, а второй использует пагинацию

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    public Book findFirstByTitleStartingWith(String searchByTitle) {
        return bookRepository.findFirstByTitleStartingWith(searchByTitle);
    }

    public People getOwnerBook(int bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElse(null).getOwner();
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setBookId(id);
        bookRepository.save(updateBook);
    }

    @Transactional
    public void delete(int bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void assign(int bookId, People selectedPerson) {
        bookRepository.findById(bookId).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    selectedPerson.getBooks().add(book);

                    LocalDateTime currentTime = LocalDateTime.now();
                    LocalDate formattedDate = LocalDate.parse(currentTime.format(formatter));
                    book.setDateTake(formattedDate);
                }
        );
    }

    @Transactional
    public void release(int bookId, People selectedPerson) {
        bookRepository.findById(bookId).ifPresent(
                book -> {
                    selectedPerson.getBooks().remove(book);
                    book.setOwner(null);
                    book.setExpired(false);
                    book.setDateTake(null);
                }
        );
    }
}
