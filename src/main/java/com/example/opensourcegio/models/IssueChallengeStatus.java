package com.example.opensourcegio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum IssueChallengeStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    COMPLETED("COMPLETED");

    public static List<IssueChallengeStatus> ongoingStatuses() {
        return Arrays.stream(IssueChallengeStatus.values()).filter(
                status -> PENDING.equals(status) || ACCEPTED.equals(status)).collect(Collectors.toList());
    }
    private String value;
}
