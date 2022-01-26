package in.tweeter.service;

import in.tweeter.controller.dto.EventProcessResponse;
import in.tweeter.repository.ConfigurationRepository;
import in.tweeter.repository.entity.ConfigurationEntity;
import in.tweeter.repository.entity.FeedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {
    private ConfigurationRepository configRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configRepository) {
        this.configRepository = configRepository;
    }

    public void saveConfig(ConfigurationEntity entity) {
        configRepository.save(entity);
    }

    public List<EventProcessResponse> processEvents(List<FeedEntity> feedEntities) {
        List<EventProcessResponse> eventProcess = new ArrayList<>();
        List<ConfigurationEntity> configEntities = configRepository.findAll();
        for (ConfigurationEntity entity: configEntities) {
            List<FeedEntity> feedsMatched = filterFeeds(entity.getKey(), feedEntities);
            eventProcess.add(EventProcessResponse.builder()
                            .action(entity.getAction())
                            .feeds(feedsMatched)
                            .key(entity.getKey())
                            .build());
        }
        return eventProcess;
    }

    private List<FeedEntity> filterFeeds(String key, List<FeedEntity> feedEntities) {
        return feedEntities.stream()
                .filter(entity -> entity.getMessage().contains(key))
                .collect(Collectors.toList());
    }
}