package io.zipcoder.crudapp.controller;

import io.zipcoder.crudapp.model.Person;
import io.zipcoder.crudapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController
{
    PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository)
    {
    }

    @PostMapping(value = "/people")
    public ResponseEntity<?> createPerson(Person p)
    {
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getPersonList()
    {
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<?> getPerson(@PathVariable Long id)
    {
        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id, @RequestBody Person p)
    {
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id)
    {
        personRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
