package in.tweeter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

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