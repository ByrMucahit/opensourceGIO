package com.example.opensourcegio.repositories;

import com.example.opensourcegio.models.IssueChallenge;
import com.example.opensourcegio.models.IssueChallengeStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IssueChallengeRepository extends PagingAndSortingRepository<IssueChallenge, Integer> {
    Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> statuses);

    @Override
    List<IssueChallenge> findAll();
}
