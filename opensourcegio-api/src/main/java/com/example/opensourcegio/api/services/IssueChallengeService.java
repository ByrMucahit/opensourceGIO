package com.example.opensourcegio.api.services;

import com.example.opensourcegio.api.models.Issue;
import com.example.opensourcegio.api.models.IssueChallenge;
import com.example.opensourcegio.api.models.IssueChallengeStatus;
import com.example.opensourcegio.api.repositories.IssueChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueChallengeService {
    private final IssueChallengeRepository issueChallengeRepository;

    @Transactional
    public IssueChallenge create(Issue issue) {
        IssueChallenge challenge = IssueChallenge.builder().issue(issue).status(
                IssueChallengeStatus.PENDING).build();

        return this.issueChallengeRepository.save(challenge);
    }

    public Boolean hasOngoingChallenge() {
        return this.issueChallengeRepository.findByStatusIn(IssueChallengeStatus.ongoingStatuses()).isPresent();
    }

    public void updateStatus(Integer id, IssueChallengeStatus status) {
        IssueChallenge issueChallenge = this.issueChallengeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Issue Challenge" + id + "not found"));

        issueChallenge.setStatus(status);
        this.issueChallengeRepository.save(issueChallenge);

    }

    public List<IssueChallenge> list() {
        return this.issueChallengeRepository.findAll();
    }
}
