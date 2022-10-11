package com.example.opensourcegio.controllers;

import com.example.opensourcegio.controllers.requests.UpdateChallengeStatusRequest;
import com.example.opensourcegio.models.IssueChallenge;
import com.example.opensourcegio.services.IssueChallengeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class IssueChallengeController {

    private final IssueChallengeService issueChallengeService;

    public IssueChallengeController(IssueChallengeService issueChallengeService) {
        this.issueChallengeService = issueChallengeService;
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id") Integer id, @RequestBody UpdateChallengeStatusRequest request) {
        this.issueChallengeService.updateStatus(id,request.getStatus());
    }

    @GetMapping
    public List<IssueChallenge> list () {
        return this.issueChallengeService.list();
    }
}
