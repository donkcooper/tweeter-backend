package in.tweeter.service;

import in.tweeter.repository.FeedRepository;
import in.tweeter.repository.entity.FeedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {
    private FeedRepository feedRepository;

    @Autowired
    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    public void saveFeed(FeedEntity entity) {
        feedRepository.save(entity);
    }

    public List<FeedEntity> getAllFeeds() {
        return feedRepository.findAll();
    }

    public int getFeedCountByPeople(long id) {
        return feedRepository.findFeedByPeopleId(id).size();
    }
}