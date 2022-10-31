package com.example.opensourcegio.worker;

import com.example.opensourcegio.worker.config.ApplicationProperty;
import com.example.opensourcegio.worker.worker.Worker;
import com.example.opensourcegio.worker.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    private final WorkerFactory workerFactory;
    private final ApplicationProperty applicationProperty;

    @Override
    public void run(String... args) throws Exception {
        Worker worker = this.workerFactory.create(this.applicationProperty.getWorkerType());
        worker.run();
    }
}
