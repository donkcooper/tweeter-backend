package in.tweeter.controller;

import in.tweeter.controller.dto.FeedRequest;
import in.tweeter.exception.EmptyCheckException;
import in.tweeter.repository.entity.FeedEntity;
import in.tweeter.service.FeedService;
import in.tweeter.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/feed")
public class FeedController {
    private FeedService feedService;
    private PeopleService peopleService;

    @Autowired
    public FeedController(FeedService feedService, PeopleService peopleService) {
        this.feedService = feedService;
        this.peopleService = peopleService;
    }

    @PostMapping(path="/addTweet",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addFeed(@Valid @RequestBody FeedRequest request) {
        if(request.getMessage().equals("") || request.getLocation().equals("")) {
            throw new EmptyCheckException("Empty Tweet or Empty Location");
        }
        FeedEntity entity = FeedEntity.builder()
                .message(request.getMessage())
                .location(request.getLocation())
                .people(peopleService.findPeopleById(request.getPeopleId()))
                .timestamp(Instant.now().getEpochSecond())
                .build();
        feedService.saveFeed(entity);
        return new ResponseEntity(feedService.getFeedCountByPeople(request.getPeopleId()), HttpStatus.CREATED);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getFeeds() {
        return new ResponseEntity(feedService.getAllFeeds(), HttpStatus.OK);
    }
}