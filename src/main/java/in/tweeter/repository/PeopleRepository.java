package in.tweeter.repository;

import in.tweeter.repository.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity, Long> {
    PeopleEntity findPeopleByUsername(String username);
}
