package in.tweeter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "people")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Column(unique=true)
    private String username;
}