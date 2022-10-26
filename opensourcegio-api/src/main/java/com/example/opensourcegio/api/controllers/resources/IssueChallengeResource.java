package com.example.opensourcegio.api.controllers.resources;

import com.example.opensourcegio.api.models.IssueChallenge;
import com.example.opensourcegio.api.models.IssueChallengeStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueChallengeResource {
    private Integer id;
    private Integer issueId;
    private Integer repositoryId;
    private String repositoryTitle;
    private String issueTitle;
    private Date creationDate;
    private IssueChallengeStatus status;

    public static IssueChallengeResource createFor(IssueChallenge issueChallenge) {
        return IssueChallengeResource.builder().
                id(issueChallenge.getId()).
                issueId(issueChallenge.getIssue().getId()).
                repositoryId(issueChallenge.getIssue().getRepository().getId()).
                issueTitle(issueChallenge.getIssue().getTitle()).
                status(issueChallenge.getStatus()).
                creationDate(issueChallenge.getCreatedAt()).
                repositoryTitle(issueChallenge.getIssue().getRepository().getRepository()).build();
    }

    public static List<IssueChallengeResource> createFor(List<IssueChallenge> issueChallenges) {
        return issueChallenges.stream().map(IssueChallengeResource::createFor).collect(Collectors.toList());
    }
}
