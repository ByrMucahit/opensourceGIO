package com.example.opensourcegio.services;

import com.example.opensourcegio.config.GithubProperties;
import com.example.opensourcegio.services.models.GithubAccessTokenRequest;
import com.example.opensourcegio.services.models.GithubAccessTokenResponse;
import com.example.opensourcegio.services.models.GithubIssueResponse;
import com.example.opensourcegio.services.models.GithubPullsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class GithubClient {

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    public GithubClient(RestTemplate restTemplate, GithubProperties githubProperties) {
        this.restTemplate = restTemplate;
        this.githubProperties = githubProperties;
    }

    public String getAccessToken(String code) {
        GithubAccessTokenRequest accessTokenRequest = GithubAccessTokenRequest.builder()
                .clientId(this.githubProperties.getClientId())
                .clientSecret(this.githubProperties.getClientSecret())
                .code(code).build();
        HttpEntity<GithubAccessTokenRequest> request = new HttpEntity<>(accessTokenRequest);
        ResponseEntity<GithubAccessTokenResponse> excahnge =
                this.restTemplate.exchange(this.githubProperties.getAccessTokenUrl(), HttpMethod.POST, request, GithubAccessTokenResponse.class);

        return Objects.requireNonNull(excahnge.getBody()).getAccessToken();
    }

    public String getAuthorize() {
        return String.format("%s?client_id=%s", githubProperties.getAuthorizeUrl(), githubProperties.getClientId());
    }

    public GithubIssueResponse[] listIssues(String owner, String repository, LocalDate since) {
        String issuesUrl = String.format("%s/repos/%s/%s/issues?pulls=false?since=%s",
                this.githubProperties.getApiUrl(), owner, repository, since.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + this.githubProperties.getToken());
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<GithubIssueResponse[]> exchange =
                this.restTemplate.exchange(issuesUrl, HttpMethod.GET, request, GithubIssueResponse[].class);

        return exchange.getBody();
    }

    public GithubPullsResponse[] listPullRequest(String owner, String repository) {
        String pullRequestsUrl = String.format("%s/repos/%s/%s/pulls?state=closed",
                this.githubProperties.getApiUrl(), owner, repository);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + this.githubProperties.getToken());
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<GithubPullsResponse[]> exchange =
                this.restTemplate.exchange(pullRequestsUrl, HttpMethod.GET, request, GithubPullsResponse[].class);

        return exchange.getBody();
    }
}
