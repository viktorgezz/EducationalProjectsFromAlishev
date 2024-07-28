package ru.viktorgezz.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.springproject.dao.BookDAO;
import ru.viktorgezz.springproject.dao.PeopleDAO;
import ru.viktorgezz.springproject.model.Book;
import ru.viktorgezz.springproject.model.People;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class BookController {

    private final BookDAO bookDAO;
    private final PeopleDAO peopleDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PeopleDAO peopleDAO) {
        this.bookDAO = bookDAO;
        this.peopleDAO = peopleDAO;
    }

    @GetMapping("/books")
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "/book/index";
    }

    @GetMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/book/new";
    }

    @PostMapping("/books/create")
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/book/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("people") People people) {
        model.addAttribute("book", bookDAO.show(id));

        Optional<People> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owvner", bookOwner.get());
        else
            model.addAttribute("peoples", peopleDAO.index());

        return "/book/show";
    }

    @PatchMapping("/books/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/books/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") People selectedPerson) {
        bookDAO.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/books/add/people")
    public String addBookPeople(@ModelAttribute("people") People people) {
        return "/book/index";
    }

    @PatchMapping("/books/free/people/{id}")
    public String freeBook(@PathVariable("id") int id) {
        bookDAO.assignStatus(id, null);
        return "redirect:/books";
    }

    @PatchMapping("/books/busy/people/{id}")
    public String busyBook(@PathVariable("id") int id,
                           @ModelAttribute("people") People bookPeople) {

        bookDAO.assignStatus(id, bookPeople.getId());
        return "redirect:/books";
    }


    @DeleteMapping("/books/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "/book/edit";
    }

    @PatchMapping("/book/edit/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "/book/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }
}