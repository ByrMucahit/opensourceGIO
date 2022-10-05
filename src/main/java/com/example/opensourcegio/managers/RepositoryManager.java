package com.example.opensourcegio.managers;

import com.example.opensourcegio.config.ApplicationProperties;
import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.Repository;
import com.example.opensourcegio.services.GithubClient;
import com.example.opensourcegio.services.IssueService;
import com.example.opensourcegio.services.RepositoryService;
import com.example.opensourcegio.services.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
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

    private final ApplicationProperties applicationProperties;

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        int schedulerFrequencyInMinutes = applicationProperties.getImportFrequency() / 60000;
        LocalDate sinceYesterday =
                LocalDate.ofInstant(Instant.now().minus(schedulerFrequencyInMinutes, ChronoUnit.MINUTES), ZoneOffset.UTC);

        GithubIssueResponse[] githubIssueResponses = this.githubClient.listIssues
                (repository.getOrganization(), repository.getRepository(), sinceYesterday);

        if (githubIssueResponses.length > 0) {
            for (GithubIssueResponse githubIssueResponse : githubIssueResponses) {
                List<Issue> issues = Arrays.stream(githubIssueResponses).map(
                        githubIssue ->
                                Issue.builder().repository(repository).githubIssueId(githubIssue.getId()).title(githubIssue.getTitle())
                                        .body(githubIssue.getBody()).build()).collect(Collectors.toList());
                this.issueService.saveAll(issues);
            }
        }
    }
}