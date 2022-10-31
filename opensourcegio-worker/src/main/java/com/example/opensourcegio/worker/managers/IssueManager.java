package com.example.opensourcegio.worker.managers;

import com.example.opensourcegio.worker.clients.GithubClient;
import com.example.opensourcegio.worker.clients.OpensourcegioApi;
import com.example.opensourcegio.worker.clients.response.GithubIssueResponse;
import com.example.opensourcegio.worker.clients.response.Repository;
import lombok.AllArgsConstructor;
import org.eclipse.egit.github.core.Issue;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class IssueManager {

    private final OpensourcegioApi opensourcegioApi;

    private final GithubClient githubClient;

    public void importIssues() {
        Repository[] repos = this.opensourcegioApi.getRepositories();

    }
}
