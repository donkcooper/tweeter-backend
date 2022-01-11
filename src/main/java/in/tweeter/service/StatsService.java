package in.tweeter.service;

import in.tweeter.repository.StatsRepository;
import in.tweeter.repository.entity.StatsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private StatsRepository statsRepository;

    @Autowired
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void saveStats(StatsEntity entity) {
        statsRepository.save(entity);
    }
}