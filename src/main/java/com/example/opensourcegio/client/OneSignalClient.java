package com.example.opensourcegio.client;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import com.example.opensourcegio.config.ApplicationProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class OneSignalClient {

    private final ApplicationProperties applicationProperties;

    public OneSignalClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void sendNotification(Integer challengeId, String issueTitle) {
        NotificationRequest request = new NotificationRequest();
        request.setTemplateId(applicationProperties.getOneSignalProperties().getNewChallengeTemplateId());
        OneSignal.createNotification(this.applicationProperties.getOneSignalProperties().getApiAuthKey(), request);

        request.setAppId(this.applicationProperties.getOneSignalProperties().getAppId());
        request.setAnyWeb(true);
        request.setContents(new HashMap<>() {{
            put("en", String.format("Would you like to solve the following issue ?"));
        }});

        Button acceptButton = new Button();
        acceptButton.setId("accept");
        acceptButton.setText("Accept");
        acceptButton.setUrl(String.format("http://localhost:4200/challenge/%d/accept", challengeId));
        Button rejectButton = new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
        rejectButton.setUrl(String.format("http://localhost:4200/challenge/%d/accept/reject", challengeId));

        request.setIncludedSegments(new ArrayList<>(){{add("Subscribed Users");}});
        OneSignal.createNotification(this.applicationProperties.getOneSignalProperties().getApiAuthKey(), request);
    }
}
