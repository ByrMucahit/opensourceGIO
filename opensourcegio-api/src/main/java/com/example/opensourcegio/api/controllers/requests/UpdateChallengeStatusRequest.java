package com.example.opensourcegio.api.controllers.requests;

import com.example.opensourcegio.api.models.IssueChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeStatusRequest {

    private IssueChallengeStatus status;
}
