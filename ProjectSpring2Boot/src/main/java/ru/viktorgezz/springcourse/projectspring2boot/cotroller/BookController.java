package ru.viktorgezz.springcourse.projectspring2boot.cotroller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.springcourse.projectspring2boot.model.Book;
import ru.viktorgezz.springcourse.projectspring2boot.model.People;
import ru.viktorgezz.springcourse.projectspring2boot.service.BookService;
import ru.viktorgezz.springcourse.projectspring2boot.service.PeopleService;


import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "-1", required = false) int page,
                        @RequestParam(name = "itemsPerPage", defaultValue = "-1", required = false) int itemsPerPage,
                        @RequestParam(name = "sort_by_year", defaultValue = "false", required = false) boolean sortByYear) {
        model.addAttribute("books", bookService.findAll(page, itemsPerPage, sortByYear));
        return "/books/index";
    }

    @GetMapping("/book/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/book/new";
    }

    @PostMapping("/book/create")
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/books/book/new";
        }

        bookService.save(book);
        return "redirect:/books/index";
    }

    @GetMapping("/book/show/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("people") People  people) {
        model.addAttribute("book", bookService.findOne(id));

        Optional<People> bookOwner = Optional.ofNullable(bookService.getOwnerBook(id));

        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("peoples", peopleService.findAll());
        }

        return "/books/book/show";
    }

    @PatchMapping("/book/assign/{id}")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") People person) {
        bookService.assign(id, person);
        return "redirect:/books/book/show/" + id;
    }

    @PatchMapping("/book/release/{id}")
    public String release(@PathVariable("id") int id, @ModelAttribute("person") People person) {
        bookService.release(id, person);
        return "redirect:/books/book/show/" + id;
    }


    @DeleteMapping("/book/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books/index";
    }

    @GetMapping("/book/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "/books/book/edit";
    }

    @PatchMapping("/book/edit/patch/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/books/book/edit";
        }

        bookService.update(id, book);
        return "redirect:/books/index";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(name = "searchByTitle", value = "searchByTitle", required = false) String searchByTitle) {
        if (searchByTitle != null) model.addAttribute("book", bookService.findFirstByTitleStartingWith(searchByTitle));
        return "/books/search";
    }
}
