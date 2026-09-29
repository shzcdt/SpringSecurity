package org.idubinov.secondSecurityApp.util;

import org.idubinov.secondSecurityApp.models.Person;
import org.idubinov.secondSecurityApp.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findUserByUsername(person.getUsername()).isPresent()){
            errors.rejectValue("username", "", "Person with this name is already taken");
        }
    }
}
