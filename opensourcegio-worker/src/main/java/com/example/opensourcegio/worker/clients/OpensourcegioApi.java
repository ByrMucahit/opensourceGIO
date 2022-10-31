package com.example.opensourcegio.worker.clients;

import com.example.opensourcegio.worker.clients.response.Repository;
import com.example.opensourcegio.worker.config.ApplicationProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class OpensourcegioApi {

    private final RestTemplate restTemplate;

    private final ApplicationProperty applicationProperty;

    public Repository[] getRepositories() {
        ResponseEntity<Repository[]> repositoriesResponse = this.restTemplate.getForEntity(this.applicationProperty.getOpensourceApi().getApiUrl(), Repository[].class);

        return repositoriesResponse.getBody();
    }
}
