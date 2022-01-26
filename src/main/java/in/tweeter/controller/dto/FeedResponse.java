package in.tweeter.controller.dto;

import in.tweeter.repository.entity.FeedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class FeedResponse {
    private List<FeedEntity> feedEntities;

    public FeedResponse() {
        feedEntities = new ArrayList<>();
    }
}