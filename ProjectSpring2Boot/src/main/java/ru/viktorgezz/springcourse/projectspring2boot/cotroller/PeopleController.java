package ru.viktorgezz.springcourse.projectspring2boot.cotroller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.viktorgezz.springcourse.projectspring2boot.model.People;
import ru.viktorgezz.springcourse.projectspring2boot.service.PeopleService;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "/people/index";
    }

    @GetMapping("/person/new")
    public String newPerson(@ModelAttribute("person") People people) {
        return "/people/person/new";
    }

    @PostMapping("/person/create")
    public String createPerson(@ModelAttribute ("person") @Valid People people,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/people/person/new";
        }
        peopleService.save(people);
        return "redirect:/people/index";
    }

    @GetMapping("/person/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));
        return "/people/person/show";
    }

    @DeleteMapping("/person/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people/index";
    }

    @GetMapping("/person/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "/people/person/edit";
    }

    @PatchMapping("/person/edit/patch/{id}")
    public String update(@ModelAttribute("person") @Valid People people, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/people/person/edit";
        }

        peopleService.update(id, people);
        return "redirect:/people/index";
    }
}
