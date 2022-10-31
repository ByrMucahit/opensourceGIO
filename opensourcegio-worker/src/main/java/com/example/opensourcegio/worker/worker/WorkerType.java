package com.example.opensourcegio.worker.worker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WorkerType {

    IMPORT_ISSUES("IMPORT_ISSUES"),
    CHALLENGE_ISSUES("CHALLENGE_ISSUES"),
    TRACK_CHALLENGES("TRACK_CHALLENGES");

    private String value;
}
