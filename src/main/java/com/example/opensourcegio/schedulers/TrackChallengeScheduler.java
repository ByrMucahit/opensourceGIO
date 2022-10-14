package com.example.opensourcegio.schedulers;

import com.example.opensourcegio.client.OneSignalClient;
import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.IssueChallenge;
import com.example.opensourcegio.models.IssueChallengeStatus;
import com.example.opensourcegio.models.Repository;
import com.example.opensourcegio.services.GithubClient;
import com.example.opensourcegio.services.IssueChallengeService;
import com.example.opensourcegio.services.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@AllArgsConstructor
public class TrackChallengeScheduler {

    private final IssueChallengeService issueChallengeService;

    private final GithubClient githubClient;

    @Scheduled(fixedDelayString = "${application.challenge-tracking-frequency}")
    public void challengeIssueScheduler() {
        log.info("Track Challenge scheduler started");
        this.issueChallengeService.list().stream().filter(issueChallenge -> IssueChallengeStatus.ACCEPTED.
                        equals(issueChallenge.getStatus()))
                .forEach(issueChallenge -> {
                    Repository repository = issueChallenge.getIssue().getRepository();
                    Arrays.stream(this.githubClient.listPullRequest(repository.getOrganization()
                            , repository.getRepository()))
                            .filter(pull ->
                                    "ByrMucahit".equals(pull.getUser().getLogin()) && pull.getBody().contains(String.format("Fixes #%d", issueChallenge.getIssue().getGithubIssueNumber()))
                                            && "closed".equals(pull.getState()))
                            .findFirst()
                            .ifPresent(pullResponse ->
                                    this.issueChallengeService.updateStatus(issueChallenge.getId(), IssueChallengeStatus.COMPLETED)
                            );
                });
        log.info("Challenge issue scheduler finished.");
    }
}
