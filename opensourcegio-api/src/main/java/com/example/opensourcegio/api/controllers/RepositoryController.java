package com.example.opensourcegio.api.controllers;

import com.example.opensourcegio.api.services.RepositoryService;
import com.example.opensourcegio.api.controllers.requests.CreateRepositoryRequest;
import com.example.opensourcegio.api.controllers.resources.RepositoryResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService aService;

    public RepositoryController(RepositoryService repositoryService) {
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
