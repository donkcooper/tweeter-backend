package in.tweeter.repository;

import in.tweeter.repository.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<FeedEntity, Long> {
    List<FeedEntity> findFeedByPeopleId(long id);
}