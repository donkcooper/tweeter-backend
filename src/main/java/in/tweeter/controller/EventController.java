package in.tweeter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.tweeter.controller.dto.ConfigRequest;
import in.tweeter.controller.dto.EventProcessResponse;
import in.tweeter.repository.entity.ConfigurationEntity;
import in.tweeter.repository.entity.FeedEntity;
import in.tweeter.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/events")
public class EventController {
    private final ConfigurationService configService;

    @Autowired
    public EventController(ConfigurationService configService) {
        this.configService = configService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<EventProcessResponse>> processEvents(@RequestParam("process") Boolean process) {
        List<EventProcessResponse> response = new ArrayList<>();
        String feedUrl = "http://ec2-13-126-172-135.ap-south-1.compute.amazonaws.com:8080/feed";
        if (process) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(feedUrl, Object[].class);
            Object[] objects = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();
            List<FeedEntity> feedEntities = Arrays.stream(objects)
                    .map(object -> mapper.convertValue(object, FeedEntity.class))
                    .collect(Collectors.toList());
            configService.processEvents(feedEntities);
            response = configService.processEvents(feedEntities);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/addConfig",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveConfiguration(@Valid @RequestBody ConfigRequest request) {
        ConfigurationEntity entity = ConfigurationEntity.builder()
                .createdBy("SYSTEM")
                .action(request.getAction())
                .key(request.getKey())
                .timestamp(Instant.now().getEpochSecond())
                .build();
        configService.saveConfig(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}