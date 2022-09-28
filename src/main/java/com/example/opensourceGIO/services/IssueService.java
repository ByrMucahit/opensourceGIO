package com.example.opensourceGIO.services;

import com.example.opensourceGIO.models.Issue;
import com.example.opensourceGIO.repositories.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    @Transactional
    public void saveAll(List<Issue> issues) {
        this.issueRepository.saveAll(issues);
    }
}
