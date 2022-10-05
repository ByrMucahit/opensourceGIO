package com.example.opensourcegio.repositories;

import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {
    List<Issue> findByRepository(Repository repository);
}
