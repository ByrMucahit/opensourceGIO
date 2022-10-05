package com.example.opensourcegio.controllers;

import com.example.opensourcegio.controllers.resources.IssueResource;
import com.example.opensourcegio.services.IssueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueResource> list(@RequestParam("repository_id") Integer repositoryId) {
        return IssueResource.createFor(this.issueService.list(repositoryId));
    }
}
