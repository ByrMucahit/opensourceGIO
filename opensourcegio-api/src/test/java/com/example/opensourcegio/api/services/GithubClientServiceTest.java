package com.example.opensourcegio.api.services;

import com.example.opensourcegio.api.services.models.GithubIssueResponse;
import com.example.opensourcegio.api.config.GithubProperties;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.BDDAssertions.then;

@ContextConfiguration(initializers = GithubClientServiceTest.Initializer.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GithubClient.class, RestTemplate.class, GithubProperties.class})
@EnableConfigurationProperties(value = GithubProperties.class)
public class GithubClientServiceTest {
    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private GithubClient gitHubClient;

    @Test
    public void it_should_list_issues() {
        //given
        wireMockServer.stubFor(get(urlPathEqualTo("/repos/octocat/Hello-World/issues"))
                .withQueryParam("since", equalTo("2021-06-01"))
                .withHeader("Authorization", equalTo("token sssshhhhh"))
                .willReturn(aResponse().withBodyFile("github/issues.json")
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).withStatus(200)));

        //when
        GithubIssueResponse[] response = this.gitHubClient.listIssues
                ("octocat", "Hello-World", LocalDate.parse("2021-06-01"));

        //Then
        then(response).isNotNull();
        then(response.length).isEqualTo(30);
        GithubIssueResponse githubIssueResponse = response[1];
        then(githubIssueResponse.getTitle()).isEqualTo("Hello, world!");

    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort());
            wireMockServer.start();

            applicationContext.getBeanFactory().registerSingleton("wireMockServer", wireMockServer);

            applicationContext.addApplicationListener(applicationEvent -> {
                if(applicationEvent instanceof ContextClosedEvent) {
                    wireMockServer.stop();
                }
            });
            TestPropertyValues.of(
                    "github.api-url=" + wireMockServer.baseUrl(),
                    "github.token=sssshhhhh"
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
