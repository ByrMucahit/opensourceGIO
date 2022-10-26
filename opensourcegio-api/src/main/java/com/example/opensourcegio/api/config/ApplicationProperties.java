package com.example.opensourcegio.api.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationProperties {
    private Integer importFrequency;
    private String token;

    private Integer challengeFrequency;

    private OneSignalProperties oneSignalProperties;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class OneSignalProperties {

        private String apiAuthKey;
        private String newChallengeTemplateId;

        private String appId;
    }
}
