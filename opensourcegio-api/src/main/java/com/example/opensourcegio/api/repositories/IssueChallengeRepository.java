package com.example.opensourcegio.api.repositories;

import com.example.opensourcegio.api.models.IssueChallenge;
import com.example.opensourcegio.api.models.IssueChallengeStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IssueChallengeRepository extends PagingAndSortingRepository<IssueChallenge, Integer> {
    Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> issueChallengeStatus);

    List<IssueChallenge> findAll();
}
