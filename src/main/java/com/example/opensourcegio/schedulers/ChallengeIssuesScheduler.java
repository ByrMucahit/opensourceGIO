package com.example.opensourcegio.schedulers;

import com.example.opensourcegio.client.OneSignalClient;
import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.IssueChallenge;
import com.example.opensourcegio.services.IssueChallengeService;
import com.example.opensourcegio.services.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ChallengeIssuesScheduler {

    private final IssueService issueService;
    private final IssueChallengeService issueChallengeService;

    private final OneSignalClient oneSignalClient;

    @Scheduled(fixedDelayString = "${application.challenge-frequency}")
    public void challengeIssueScheduler() {
        log.info("Challenge issue scheduler started");
        if(this.issueChallengeService.hasOngoingChallenge()) {
            log.warn("There is already an going challenge, skipping...");
            return;
        }
        Issue randomIssue = this.issueService.findRandomIssue();
        log.info("Found a random issue {}", randomIssue.getGithubIssueId());
        IssueChallenge issueChallenge = issueChallengeService.create(randomIssue);
        oneSignalClient.sendNotification(issueChallenge.getId(), randomIssue.getTitle());
        log.info("Challenge issue scheduler finished.");
    }
}
