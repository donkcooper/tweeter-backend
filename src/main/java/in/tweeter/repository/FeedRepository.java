package in.tweeter.repository;

import in.tweeter.repository.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<FeedEntity, Long> {
    List<FeedEntity> findFeedByPeopleId(long id);
}