package in.tweeter.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationEntity {
    @Id
    @GeneratedValue
    private long id;
    private String key;
    private String action;
    private String createdBy;
    private long timestamp;
}