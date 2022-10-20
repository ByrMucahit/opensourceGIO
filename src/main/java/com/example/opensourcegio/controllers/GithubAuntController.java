package com.example.opensourcegio.controllers;

import com.example.opensourcegio.services.GithubClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth/github")
@AllArgsConstructor
public class GithubAuntController {

    private final GithubClient githubClient;

    @GetMapping("/authorize")
    public void authorize (HttpServletResponse response) {
        response.setHeader("Location",this.githubClient.getAuthorize());
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
    }
}
