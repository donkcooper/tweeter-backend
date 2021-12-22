package in.tweeter.controller;

import in.tweeter.controller.dto.LoginRequest;
import in.tweeter.controller.dto.PeopleRequest;
import in.tweeter.exception.EmptyCheckException;
import in.tweeter.exception.InvalidLoginException;
import in.tweeter.exception.UsernameExistsException;
import in.tweeter.repository.entity.PeopleEntity;
import in.tweeter.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addPerson(@Valid @RequestBody PeopleRequest request) {
        if (request.getName().equals("")) {
            throw new EmptyCheckException("Empty Name");
        }
        PeopleEntity peopleEntity = PeopleEntity.builder()
                .name(request.getName())
                .username(request.getUsername())
                .build();
        peopleService.savePeople(peopleEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<Boolean> validateUserName(@PathVariable("username") String username) {
        if (Objects.nonNull(peopleService.findPeopleByUsername(username))) {
            throw new UsernameExistsException(username + " with User already exists.");
        }
        return new ResponseEntity(username, HttpStatus.OK);
    }

    @PostMapping(path = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> doLogin(@Valid @RequestBody LoginRequest request) {
        if (request.getUsername().equals("")) {
            throw new EmptyCheckException("Empty Username for Login");
        }
        PeopleEntity entity = peopleService.findPeopleByUsername(request.getUsername());
        if(Objects.isNull(entity)) {
            throw new InvalidLoginException("Incorrect Username");
        }
        return new ResponseEntity(entity, HttpStatus.OK);
    }
}