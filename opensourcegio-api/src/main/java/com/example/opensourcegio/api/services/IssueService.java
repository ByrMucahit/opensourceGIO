package com.example.opensourcegio.api.services;

import com.example.opensourcegio.api.models.Issue;
import com.example.opensourcegio.api.repositories.IssueRepository;
import com.example.opensourcegio.api.models.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    private final RepositoryService repositoryService;

    public Issue findRandomIssue() {
        return this.issueRepository.findRandomIssue().orElseThrow(() -> new EntityNotFoundException("No issue found"));
    }

    public List<Issue> list(Integer repsitoryId) {
        Repository repository = this.repositoryService.findById(repsitoryId);
        return this.issueRepository.findByRepository(repository);
    }

    @Transactional
    public void saveAll(List<Issue> issues) {
        issues.forEach(issue -> {
            if(this.issueRepository.findByGithubIssueId(issue.getGithubIssueId()).isEmpty()){
                this.issueRepository.save(issue);
            }
        });
    }
}
