package com.example.opensourcegio.worker.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "github")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubProperties {
    private String apiUrl;
    private String token;
}
