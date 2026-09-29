package org.idubinov.secondSecurityApp.services;

import org.hibernate.internal.build.AllowPrintStacktrace;
import org.idubinov.secondSecurityApp.models.Person;
import org.idubinov.secondSecurityApp.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findUserByUsername(String username){
        return peopleRepository.findByUsername(username);
    }
}
