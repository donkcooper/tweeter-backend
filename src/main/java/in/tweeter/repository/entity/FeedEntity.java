package in.tweeter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
@Table(name = "feed")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private PeopleEntity people;
    private String message;
    private String location;
    private long timestamp;
}