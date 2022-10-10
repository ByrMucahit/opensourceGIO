package com.example.opensourcegio.controllers.requests;

import com.example.opensourcegio.models.IssueChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeStatusRequest {

    private IssueChallengeStatus issueChallengeStatus;
}
