package com.example.opensourcegio.worker.managers;

import com.example.opensourcegio.worker.clients.GithubClient;
import com.example.opensourcegio.worker.clients.OpensourcegioApi;
import com.example.opensourcegio.worker.clients.response.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IssueManager {

    private final OpensourcegioApi opensourcegioApi;

    private final GithubClient githubClient;

    public void importIssues() {
        Repository[] repos = this.opensourcegioApi.getRepositories();

    }
}
