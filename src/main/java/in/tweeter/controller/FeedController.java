package in.tweeter.controller;

import in.tweeter.controller.dto.FeedRequest;
import in.tweeter.exception.EmptyCheckException;
import in.tweeter.repository.entity.FeedEntity;
import in.tweeter.repository.entity.StatsEntity;
import in.tweeter.service.FeedService;
import in.tweeter.service.PeopleService;
import in.tweeter.service.StatsService;
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
    private final FeedService feedService;
    private final PeopleService peopleService;
    private final StatsService statsService;

    @Autowired
    public FeedController(FeedService feedService, PeopleService peopleService, StatsService statsService) {
        this.feedService = feedService;
        this.peopleService = peopleService;
        this.statsService = statsService;
    }

    @PostMapping(path = "/addTweet",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addFeed(@Valid @RequestBody FeedRequest request) {
        if (request.getMessage().equals("") || request.getLocation().equals("")) {
            throw new EmptyCheckException("Empty Tweet or Empty Location");
        }
        int number = feedService.getFeedCountByPeople(request.getPeopleId()) + 1;
        StatsEntity statsEntity = StatsEntity.builder()
                .location(request.getLocation())
                .number(number)
                .updatedTimestamp(Instant.now().getEpochSecond())
                .build();
        statsService.saveStats(statsEntity);

        FeedEntity entity = FeedEntity.builder()
                .message(request.getMessage())
                .stats(statsEntity)
                .type("feed")
                .people(peopleService.findPeopleById(request.getPeopleId()))
                .timestamp(Instant.now().getEpochSecond())
                .build();
        feedService.saveFeed(entity);
        return new ResponseEntity(number, HttpStatus.CREATED);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getFeeds() {
        return new ResponseEntity(feedService.getAllFeeds(), HttpStatus.OK);
    }

    @PutMapping(path = "/{feedId}/stats/like",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateFeed(@PathVariable("feedId") long feedId) {
        FeedEntity feed = feedService.getFeedById(feedId);
        StatsEntity stats = feed.getStats();
        stats.setLikes(stats.getLikes() + 1);
        statsService.saveStats(stats);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}