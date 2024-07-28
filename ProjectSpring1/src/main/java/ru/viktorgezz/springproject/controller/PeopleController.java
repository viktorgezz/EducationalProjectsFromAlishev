package ru.viktorgezz.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.springproject.dao.BookDAO;
import ru.viktorgezz.springproject.dao.PeopleDAO;
import ru.viktorgezz.springproject.model.People;
import ru.viktorgezz.springproject.util.PeopleValidator;

import javax.validation.Valid;

@Controller
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class PeopleController {

    private final PeopleDAO peopleDAO;
    private final BookDAO bookDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, BookDAO bookDAO, PeopleValidator peopleValidator) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("/peoples")
    public String index(Model model) {
        model.addAttribute("peoples", peopleDAO.index());
        return "/people/index";
    }

    @GetMapping("/peoples/new")
    public String newPeople(@ModelAttribute("people") People people) {
        return "/people/new";
    }

    @PostMapping("/peoples/create")
    public String create(@ModelAttribute("people") @Valid People people,
                         BindingResult bindingResult) {
//        peopleValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/people/new";
        }

        peopleDAO.save(people);
        return "redirect:/peoples";
    }

    @GetMapping("/peoples/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("people", peopleDAO.show(id));
        model.addAttribute("books", bookDAO.showBooks(id));
        return "/people/show";
    }

    @DeleteMapping("/peoples/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleDAO.delete(id);
        return "redirect:/peoples";
    }

    @GetMapping("/peoples/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("people", peopleDAO.show(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("people") @Valid People people,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "/people/edit";
        }
        peopleDAO.update(id, people);
        return "redirect:/peoples";

    }
}
