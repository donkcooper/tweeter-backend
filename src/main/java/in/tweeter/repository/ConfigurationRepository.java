package in.tweeter.repository;

import in.tweeter.repository.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
}