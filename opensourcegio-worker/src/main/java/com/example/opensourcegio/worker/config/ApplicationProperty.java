package com.example.opensourcegio.worker.config;

import com.example.opensourcegio.worker.worker.WorkerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "opensource")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationProperty {

    private WorkerType workerType;

    private OpensourceApi opensourceApi;

    @AllArgsConstructor
    @Data
    @Builder
    public static class OpensourceApi {
        private String apiUrl;
    }
}
