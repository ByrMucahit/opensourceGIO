package com.example.opensourcegio.controllers;

import com.example.opensourcegio.controllers.requests.GetGithubAccessTokenRequest;
import com.example.opensourcegio.services.GithubClient;
import com.example.opensourcegio.services.models.GithubAccessTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @PostMapping("/access_token")
    public GithubAccessTokenResponse getAccessToken(@RequestBody GetGithubAccessTokenRequest githubAccessTokenRequest) {
        return GithubAccessTokenResponse
                .builder()
                .accessToken( this.githubClient.getAccessToken(githubAccessTokenRequest.getCode())).build();
    }
}
