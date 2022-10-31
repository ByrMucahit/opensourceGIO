package com.example.opensourcegio.worker.clients.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class GithubPullsResponse {

    private String state;

    private User user;

    private String body;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String login;
    }
}
