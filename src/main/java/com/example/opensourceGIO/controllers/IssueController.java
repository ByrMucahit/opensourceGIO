package com.example.opensourceGIO.controllers;

import com.example.opensourceGIO.controllers.requests.CreateRepositoryRequest;
import com.example.opensourceGIO.controllers.resources.RepositoryResource;
import com.example.opensourceGIO.services.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final RepositoryService aService;

    public IssueController(RepositoryService repositoryService) {
        this.aService = repositoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRepositoryRequest createRepositoryRequest) {
        this.aService.create(createRepositoryRequest.getOrganization(), createRepositoryRequest.getRepository());
    }

    @GetMapping
    public List<RepositoryResource> list() {
        return RepositoryResource.createFor(this.aService.list());
    }
}
