package com.example.opensourcegio.worker.config;

import com.example.opensourcegio.worker.service.WorkerType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
@Data
public class ApplicationProperty {

    private WorkerType workerType;
}
