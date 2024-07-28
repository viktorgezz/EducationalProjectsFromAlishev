package ru.viktorgezz.springproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.viktorgezz.springproject.dao.PeopleDAO;
import ru.viktorgezz.springproject.model.People;

@Component
public class PeopleValidator implements Validator {

    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return People.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        People person = (People) o;

        if (peopleDAO.show(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}
