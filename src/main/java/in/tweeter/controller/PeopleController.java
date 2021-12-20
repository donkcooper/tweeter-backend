package in.tweeter.controller;

import in.tweeter.repository.entity.People;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/people")
public class PeopleController {

    @PutMapping
    public ResponseEntity<?> addPerson(@RequestBody People people) {
       return new ResponseEntity(HttpStatus.CREATED);
    }
}
