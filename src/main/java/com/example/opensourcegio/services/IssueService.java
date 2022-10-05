package com.example.opensourcegio.services;

import com.example.opensourcegio.models.Issue;
import com.example.opensourcegio.models.Repository;
import com.example.opensourcegio.repositories.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    private final RepositoryService repositoryService;

    @Transactional
    public void saveAll(List<Issue> issues) {
        this.issueRepository.saveAll(issues);
    }

    public List<Issue> list(Integer repsitoryId) {
        Repository repository = this.repositoryService.findById(repsitoryId);
        return this.issueRepository.findByRepository(repository);
    }
}
