package in.tweeter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class People {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Column(unique=true)
    private String username;
}