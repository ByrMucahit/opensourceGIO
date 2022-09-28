package com.example.opensourceGIO.repositories;

import com.example.opensourceGIO.models.Issue;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {
}
