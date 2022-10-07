package com.example.opensourcegio.repositories;

import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {
    List<Issue> findByRepository(Repository repository);

    @Query(value = "select * from issue where id not in (select issue_id from issue_challenge) order by rand() limit 1", nativeQuery = true)
    Optional<Issue> findRandomIssue();
}
