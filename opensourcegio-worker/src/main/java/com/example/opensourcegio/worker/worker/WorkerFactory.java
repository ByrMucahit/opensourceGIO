package com.example.opensourcegio.worker.worker;

import org.springframework.stereotype.Component;

@Component
public class WorkerFactory {

    public Worker create(WorkerType type) {
        switch (type) {
            case IMPORT_ISSUES:
                return new ImportIssuesWorker();
            case CHALLENGE_ISSUES:
                return new ChallengeIssuesWorker();
            case TRACK_CHALLENGES:
                return new TrackChallengesWorker();
            default:
                throw new IllegalArgumentException(String.format("Invalid worker type: %s", type));
        }
    }
}
