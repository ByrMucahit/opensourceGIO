package com.example.opensourcegio.controllers.resources;

import com.example.opensourcegio.models.IssueChallenge;
import com.example.opensourcegio.models.IssueChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubAccessTokenResponse {

    private String accessToken;
}
