package com.example.opensourcegio.services;

import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.IssueChallenge;
import com.example.opensourcegio.models.IssueChallengeStatus;
import com.example.opensourcegio.repositories.IssueChallengeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssueChallengeService {
    private final IssueChallengeRepository issueChallengeRepository;

    @Transactional
    public IssueChallenge create(Issue issue) {
        IssueChallenge challenge = IssueChallenge.builder().issue(issue).issueChallengeStatus(
                IssueChallengeStatus.PENDING).build();

        return this.issueChallengeRepository.save(challenge);
    }

    public Boolean hasOngoingChallenge() {
        return this.issueChallengeRepository.findByStatusIn(IssueChallengeStatus.ongoingStatuses()).isPresent();
    }

    public void updateStatus(Integer id, IssueChallengeStatus status) {
        IssueChallenge issueChallenge = this.issueChallengeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Issue Challenge" + id + "not found"));

        issueChallenge.setIssueChallengeStatus(status);
        this.issueChallengeRepository.save(issueChallenge);

    }

    public List<IssueChallenge> list() {
        return this.issueChallengeRepository.findAll();
    }
}
