package com.example.opensourceGIO.managers;

import com.example.opensourceGIO.models.Issue;
import com.example.opensourceGIO.models.Repository;
import com.example.opensourceGIO.services.GithubClient;
import com.example.opensourceGIO.services.IssueService;
import com.example.opensourceGIO.services.RepositoryService;
import com.example.opensourceGIO.services.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryManager {

    private final RepositoryService repositoryService;

    private final IssueService issueService;

    private final GithubClient githubClient;

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        LocalDate sinceYesterday =
                LocalDate.ofInstant(Instant.now().minus(1, ChronoUnit.DAYS), ZoneId.systemDefault());

        GithubIssueResponse[] githubIssueResponses = this.githubClient.listIssues
                (repository.getOrganization(), repository.getRepository(), sinceYesterday);

        if (githubIssueResponses.length > 0) {
            for (GithubIssueResponse githubIssueResponse : githubIssueResponses) {
                List<Issue> issues = Arrays.stream(githubIssueResponses).map(
                        githubIssue ->
                                Issue.builder().title(githubIssue.getTitle())
                                        .body(githubIssue.getBody()).build()).collect(Collectors.toList());
                this.issueService.saveAll(issues);
            }
        }
    }
}