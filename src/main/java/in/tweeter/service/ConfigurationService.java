package in.tweeter.service;

import in.tweeter.repository.ConfigurationRepository;
import in.tweeter.repository.entity.ConfigurationEntity;
import in.tweeter.repository.entity.FeedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void processEvents(List<FeedEntity> feedEntities) {
        List<ConfigurationEntity> configEntities = configRepository.findAll();
        for (FeedEntity entity: feedEntities) {
            String message = entity.getMessage();
            System.out.println("Config Key -> " + findConfig(message, configEntities).getId());
        }
    }

    private ConfigurationEntity findConfig(String message, List<ConfigurationEntity> configEntities) {
        return configEntities.stream()
                .filter(entity -> message.contains(entity.getKey()))
                .findAny().orElse(null);
    }
}
