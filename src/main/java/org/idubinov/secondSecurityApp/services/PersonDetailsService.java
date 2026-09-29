package org.idubinov.secondSecurityApp.services;

import org.idubinov.secondSecurityApp.models.Person;
import org.idubinov.secondSecurityApp.repository.PeopleRepository;
import org.idubinov.secondSecurityApp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = peopleRepository.findByUsername(username);

        if (optionalPerson.isEmpty()){
            throw new UsernameNotFoundException("User not found!±");
        }

        return new PersonDetails(optionalPerson.get());
    }
}
